
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String emptyString = "";
		if (str.length() == 1) {
			return emptyString;
		} else {
			return str.substring(1);
		}
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.length()==0) {
			return word2.length();
		} if (word2.length()==0){
			return word1.length();
		}
		char head1 = word1.charAt(0);
		char head2 = word2.charAt(0);
		String tail1 = tail(word1);
		String tail2 = tail(word2);

		if (head1 == head2) {
			return levenshtein(tail1,tail2);
		} else {
			int min = Math.min(levenshtein(tail1, word2), levenshtein(word1,tail2));
			int distance = 1 + Math.min(min, levenshtein(tail1, tail2));
			return distance;
		}
	
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary [i] = in.readString() ;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String minWord = ""
;		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary [i]) <= levenshtein(word, minWord)) {
				if (levenshtein(word, minWord) > threshold) {
					minWord = dictionary [i];
				}
				if (word.equals(dictionary [i])) {
					minWord = dictionary [i];
				}
					
			}
		}

		if (levenshtein(word, minWord) > threshold) {
			return word;
		}
			return minWord;
	}

}
