import java.util.List;

/**
 * Day1
 */
public class Day1 {
	public static void main(String[] args) {
		String bigram = "snnoowwffllaakke";
		String bigramAnagram = "fnaewkfonklsawlo";

		String a = Utils.sortString(bigram);
		String b = Utils.sortString(bigramAnagram);
		System.out.println(a.equals(b));

		String lookingFor = Utils.sortString("aeteesasrsssstaesersrrsse");

		List<String> words = Utils.readFile("wordlist.txt");

		int n = 2;
		while (true) {
			for (String word : words) {
				String gram = new NGram(word).ngram(n);
				if (lookingFor.equals(gram)) {
					System.out.println(n + "-" + word);
					return;
				}
			}
			n++;
		}
	}

}

class NGram {
	String str;

	NGram(String s) {
		str = s;
	}

	public String ngram(int n) {
		String gram = "";

		for (int i = 0; i < str.length() - n + 1; i++) {
			gram += str.substring(i, i + n);
		}

		return Utils.sortString(gram);
	}
}
