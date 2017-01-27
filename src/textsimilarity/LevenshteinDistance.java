package textsimilarity;
public class LevenshteinDistance {

	public static void main(String[] args) {
		String[] data = { " ", " ", "Vladimir_Levenshtein[1965]", "Vladimir_Levenshtein[1965]",
				"The Examples String", "The Example String", "kitten", "sitting", "saturday", "sunday"};
		for (int i = 0; i < data.length; i += 2)
			System.out.println(
					"distance(" + data[i] + ", " + data[i + 1] + ") = " + Levenshteindistance(data[i], data[i + 1]));
	}

	public static int Levenshteindistance(String S1, String S2) {
		S1 = S1.toLowerCase();
		S2 = S2.toLowerCase();
		
		int[] costs = new int[S2.length() + 1];
		for (int j = 0; j < costs.length; j++)
			costs[j] = j;
		for (int i = 1; i <= S1.length(); i++) {
			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= S2.length(); j++) {
				int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
						S1.charAt(i - 1) == S2.charAt(j - 1) ? nw : nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[S2.length()];
	}

	public int valuePhrase(String s1, String s2) {
		int valuePhrase = Levenshteindistance(s1, s2);
		return valuePhrase;

	}

	public int valueWords(String s1, String s2) {
		String[] wordsS1 = new String[5];
		String[] wordsS2 = new String[5];
		wordsS1 = SplitMultiDelims(s1, " _ -");
		wordsS2 = SplitMultiDelims(s2, " _ -");
		int valueWords = 0;
		int thisD = 0;
		int wordBest = 0;
		int wordsTotal = 0;
		for (int word1 = 0; word1 < wordsS1.length; word1++) {

			wordBest = s2.length();
			for (int word2 = 0; word2 < wordsS2.length; word2++) {
				thisD = Levenshteindistance(wordsS1[word1], wordsS2[word2]);
				if (thisD < wordBest)
					wordBest = thisD;
				if (thisD == 0)
					break;
			}
			if (thisD == 0)
				wordsTotal = foundbest(wordsTotal, wordBest);
		}
		valueWords = wordsTotal;
		return valueWords;

	}

	private int foundbest(int wordsTotal, int wordBest) {
		return wordsTotal + wordBest;
	}

	private String[] SplitMultiDelims(String s1, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
