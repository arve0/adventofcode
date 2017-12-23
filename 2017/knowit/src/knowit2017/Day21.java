package knowit2017;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Day21
 */
public class Day21 {
	Map<String, Person> people = new HashMap<>();
	Set<Person> friends = new HashSet<>();
	Set<Person> foes = new HashSet<>();

	public static void main(String[] args) {
		new Day21();
	}

	Day21() {
		for (String row : Utils.readFile("input21.txt")) {
			// vennskap Emiel Janikke
			// fiendskap Auer Maxwell
			String[] fields = row.split(" ");
			boolean friendship = fields[0].equals("vennskap");
			String a = fields[1];
			String b = fields[2];

			Person pa;
			if (people.containsKey(a)) {
				pa = people.get(a);
			} else {
				pa = new Person(a);
			}
			Person pb;
			if (people.containsKey(b)) {
				pb = people.get(b);
			} else {
				pb = new Person(b);
			}

			if (friendship) {
				pa.friends.add(b);
				pb.friends.add(a);
			} else {
				pa.foes.add(b);
				pb.foes.add(a);
			}

			people.put(a, pa);
			people.put(b, pb);
		}

		addFriend(people.get("Asgeir"), true);
		addFriend(people.get("Beate"), false);

		System.out.print(friends.size() + " ");
		System.out.print(foes.size() + " ");
		System.out.println(people.size() - friends.size() - foes.size());
	}

	void addFriend(Person p, boolean friend) {
		if (friends.contains(p) || foes.contains(p)) {
			return;
		}

		if (friend) {
			friends.add(p);
		} else {
			foes.add(p);
		}

		for (String name : p.friends) {
			addFriend(people.get(name), friend);
		}
		for (String name : p.foes) {
			addFriend(people.get(name), !friend);
		}
	}
}

class Person implements Comparable<Person> {
	String name;
	Set<String> friends = new HashSet<>();
	Set<String> foes = new HashSet<>();

	Person(String name) {
		this.name = name;
	}

	public int compareTo(Person p) {
		return name.compareTo(p.name);
	}

	public String toString() {
		return name;
	}
}
