import java.util.Arrays;
import java.util.HashSet;

public class WordBreak {
	private static final String[] SET_VALUES =
			new String[] {"I", "have", "Jain", "Sumit", "am", "this", "dog"};
	private static HashSet<String> dict = new HashSet<String>(Arrays.asList(SET_VALUES));
	
	public static void wordBreak(String string, HashSet<String> hashSet) {
		if (find(string, hashSet, "")) {
			
		} else {
			System.out.println("can't break");
		}
	}
	
	private static boolean find(String string, HashSet<String> dict, String answer) {
		if (string.length() == 0) {
			System.out.println(answer);
			return true;
		}
		
		int index = 0;
		String word = "";
		while (index < string.length()) {
			word += string.charAt(index); // add one char at a time
			// check if word is present in dict
			if (dict.contains(word)) {
				// add word to the answer and make a recursive call
				if (find(string.substring(index + 1), dict, answer + word + " ")) {
					return true;
				} else {
					// backtrack
					index++;
				}
			} else {
				index++;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		wordBreak("IamSumit", dict); // I am Sumit
		wordBreak("ImSumit", dict); // can't break
	}
}
