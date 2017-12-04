package adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day4 {

	public static void main(String[] args) {
		FileReader file = new FileReader("input4.txt");
		
		int sum = 0;
		for (String row : file.readLines()) {
			Room r = new Room(row);
			if (r.validChecksum()) {
				sum += r.id;
				String name = r.decryptName();
				if (name.contains("north") && name.contains("pole")) {
					System.out.println(name + r);
				}
			}
		}
		System.out.println(sum);
	}

}

class Room {
	String row;
	String name;
	int id;
	String checksum;
	boolean valid;
	Map<Character, Integer> chars = new TreeMap<Character, Integer>();
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	Room(String r) {
		row = r;
		parseChecksum();
		parseName();
		parseId();
	}
	
	private void parseChecksum() {
		int start = row.indexOf('[') + 1;
		int end = row.length() - 1;
		checksum = row.substring(start, end);
	}
	
	private void parseName() {
		int end = row.indexOf('[') - 4;
		name = row.substring(0, end);
	}
	
	private void parseId() {
		int start = row.indexOf('[') - 3;
		id = Integer.parseInt(row.substring(start, start + 3));
	}
	
	boolean validChecksum() {
		return new CheckSum(name).getCheckSum().equals(checksum);
	}
	
	public String toString() {
		return "name: " + name + " id: " + id + " checksum: " + checksum;
	}
	
	public String decryptName() {
		int shift = Math.floorMod(id, alphabet.length());
		String decrypted = "";
		for (char c : name.toCharArray()) {
			int i = alphabet.indexOf(c);
			i += shift;
			i = Math.floorMod(i, alphabet.length());
			decrypted += alphabet.substring(i, i + 1);
		}
		return decrypted;
	}
}

class CheckSum {
	private char[] chrs;
	private List<WeightedChar> weightedChars = new ArrayList<WeightedChar>();

	CheckSum(String s) {
		chrs = s.replace("-", "").toCharArray();
		Arrays.sort(chrs);
		
		for (int i = 0; i < chrs.length; i++) {
			char chr = chrs[i];
			int n = 1;
			while ((i + n) < chrs.length && chrs[i + n] == chr) {
				n++;
			}
			weightedChars.add(new WeightedChar(chr, n));
			i += n - 1;
		}
		
		Collections.sort(weightedChars);
	}
	
	public String getCheckSum() {
		return weightedChars
				.subList(0, 5)
				.stream()
				.map(c -> c.str)
				.collect(Collectors.joining(""));
	}
}

class WeightedChar implements Comparable<WeightedChar> {
	int weight;
	char chr;
	String str;

	WeightedChar(char c, int w) {
		chr = c;
		str = Character.toString(c);
		weight = w;
	}
	
	public String toString() {
		return str + ":" + weight;
	}
	
	public int compareTo(WeightedChar a) {
		if (weight > a.weight) {
			return -1;
		} else if (weight < a.weight) {
			return 1;
		}
		// weight equal
		// lower char value -> earlier in alphabet
		if (chr < a.chr) {
			return -1;
		} else if (chr > a.chr) {
			return 1;
		}
		return 0;
	}
}