package textsimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Cosine_Dice_Jaccard {
	static double Jaccard_Index = 0.0;
	static double Dice_Value = 0.0;
	static double Cosine_Value = 0.0;
	static double Avg_Value = 0.0;
	public static void main(String[] args) {
		String s1 = "customer_address";
		String s2 = "address_id";
		
		Dice_Value = compareStrings(s1,s2);
		Cosine_Value = cosineSimilarity(s1,s2);
		
		System.out.println("DIce - "+Dice_Value);
		System.out.println("Cosine - "+ Cosine_Value);
		System.out.println("Jaccard_Index - "+Jaccard_Index);
		Avg_Value = (Jaccard_Index+Cosine_Value+Dice_Value)/3;
		System.out.println("Avg_Value - "+ Avg_Value);

	}

	public static double compareStrings(String str1, String str2) {
		ArrayList<String> pairs1 = wordLetterPairs(str1.toUpperCase());
		ArrayList<String> pairs2 = wordLetterPairs(str2.toUpperCase());
		double intersection = 0;
		double union = pairs1.size() + pairs2.size();
		for (int i = 0; i < pairs1.size(); i++) {
			Object pair1 = pairs1.get(i);
			for (int j = 0; j < pairs2.size(); j++) {
				Object pair2 = pairs2.get(j);
				if (pair1.equals(pair2)) {
					intersection++;
					pairs2.remove(j);
					break;
				}
			}
		}
		Jaccard_Index = intersection/(union-intersection);
		
		return (2.0 * intersection) / union;
	}

	private static ArrayList<String> wordLetterPairs(String str) {

		ArrayList<String> allPairs = new ArrayList<String>();
		
		// Tokenize the string and put the tokens/words into an array
		String[] words = str.split("\\s");
		
		// For each word
		for (int w = 0; w < words.length; w++) {
			// Find the pairs of characters
			String[] pairsInWord = letterPairs(words[w]);
			for (int p = 0; p < pairsInWord.length; p++) {
				allPairs.add(pairsInWord[p]);
			}
		}
		return allPairs;
	}

	private static String[] letterPairs(String str) {
		int numPairs = str.length() - 1;
		String[] pairs = new String[numPairs];
		for (int i = 0; i < numPairs; i++) {
			pairs[i] = str.substring(i, i + 2);
		}
		return pairs;
	}
	
	public static double cosineSimilarity(String str1, String str2) {
        //Get vectors
        Map<String, Integer> a = getTermFrequencyMap(str1.toUpperCase().split("(?!^)"));
        Map<String, Integer> b = getTermFrequencyMap(str2.toUpperCase().split("(?!^)"));

        //Get unique words from both sequences
        HashSet<String> intersection = new HashSet<>(a.keySet());
        intersection.retainAll(b.keySet());
        //System.out.println(intersection);
        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

        //Calculate dot product
        for (String item : intersection) {
            dotProduct += a.get(item) * b.get(item);
        }

        //Calculate magnitude a
        for (String k : a.keySet()) {
            magnitudeA += Math.pow(a.get(k), 2);
        }

        //Calculate magnitude b
        for (String k : b.keySet()) {
            magnitudeB += Math.pow(b.get(k), 2);
        }

        //return cosine similarity
        return dotProduct / Math.sqrt(magnitudeA * magnitudeB);
    }
	
	public static Map<String, Integer> getTermFrequencyMap(String[] terms) {
        Map<String, Integer> termFrequencyMap = new HashMap<>();
        for (String term : terms) {
            Integer n = termFrequencyMap.get(term);
            n = (n == null) ? 1 : ++n;
            termFrequencyMap.put(term, n);
        }
        return termFrequencyMap;
    }

}
