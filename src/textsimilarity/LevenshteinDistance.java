package textsimilarity;

public class LevenshteinDistance {

	public static void main(String[] args) {
		String[] inputs = { " ", " ", "P3_Solutions[2016]", "P3_SOLUTIONS[2016]",
				"The Examples String", "The Example String", "kitten", "sitting", "saturday", "sunday"};
		for (int i = 0; i < inputs.length; i += 2)
			System.out.println(
					"distance(" + inputs[i] + ", " + inputs[i + 1] + ") = " + Levenshteindistance(inputs[i], inputs[i + 1]));
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



}
