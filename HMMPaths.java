import java.util.ArrayList;

/*
 * Evelyn Pirnia
 * Homework 3: Hidden Markov Model (HMM) Paths
 *
 * Prompt:
 * You will have to implement this HMM from S. R. Eddy (2004) and add one state
 * “state 3” that should follow state I.  Transition probabilities for state I
 * should be 0.9 to stay on I and 0.1 to move to state 3. Emission probabilities
 * for state 3 should be A = 0.01, C = 0, G = 0.99 and T = 0. Transitions
 * probability for state 3 should be 1.0 to move to End state.
 *
 * Your program should generate the list of all paths with non-zero probabilities
 * that could have generate the sequence CTTCATGTGAAAGCAGACGTAAGTCA, as well as
 * the corresponding probability(log). Also you need to print the best path.
 *
 * Hint:
 * You should start with the original HMM presented in the paper and verify
 * the result based on the 6 non-zero probabilities state path examples found in
 * the paper Figure 1. After add “state 3” to complete the assignment.
 */
public class HMMPaths {

	static String bestPath;
	static double highestProb = -5000.00;

	/* values for prob */
	static double entronA = 0.25;
	static double entronC = 0.25;
	static double entronG = 0.25;
	static double entronT = 0.25;
	static double fiveA = 0.05;
	static double fiveC = 0.00;
	static double fiveG = 0.95;
	static double fiveT = 0.00;
	static double intronA = 0.4;
	static double intronC = 0.1;
	static double intronG = 0.1;
	static double intronT = 0.4;
	static double threeA = 0.01;
	static double threeC = 0.00;
	static double threeG = 0.99;
	static double threeT = 0.00;

	public static void main(String[] args) {
		/* provided sequence */
		String given = "CTTCATGTGAAAGCAGACGTAAGTCA";

		ArrayList<Integer> spliceIndex = new ArrayList<Integer>();
		ArrayList<String> paths = new ArrayList<String>();

		/* by only pursuing the paths with G or A, we automatically exclude all paths that equal 0 */
		for(int i = 0; i < given.length(); i++) {
			if((given.toCharArray()[i] == 'G') || (given.toCharArray()[i] == 'A'))
				spliceIndex.add(i);
		}

		/* generate list of all paths */
		for(int i = 0; i < spliceIndex.size()-1; i++) { // five index
			for(int j = (i+1); j < spliceIndex.size(); j++) { // three index
				String tempPath = getPath(given.length(), spliceIndex.get(i), spliceIndex.get(j));
				if(validPath(tempPath, given.length(), spliceIndex.get(i), spliceIndex.get(j), given.length())) {
					double tempProb = getProbability(given.toCharArray(), given.length(), spliceIndex.get(i), spliceIndex.get(j));
					/* remove zero probabilities */
					if(tempProb != 0) {
						System.out.println("Path: " + tempPath + '\n' + "      " + Math.log(tempProb) + '\n'); /* printing path and probability*/
						paths.add(tempPath + '\n' + Math.log(tempProb));
						if(Math.log(tempProb) > highestProb) {
							highestProb = Math.log(tempProb);
							bestPath = tempPath;
						}
					}
				}
			}
		}

		/* select best path */
		System.out.println("Best: (" + highestProb + ", " + bestPath + ")");
	}

	private static boolean validPath(String tempPath, int gLength, Integer five, Integer three, int givenLength) {
		if(((three - five) <= 1)) {
			return false;
		} else if(five == gLength) {
			return false;
		} else if(tempPath.length() != (givenLength+3)) { // account for "End" in sequence
			return false;
		}else {
			return true;
		}
	}

	private static double getProbability(char[] given, int givenLength, Integer fiveIndex, int threeIndex) {
		double p = 1.00; /* start */
		for(int i = 0; i < (threeIndex+1); i++) { /* after 3 we finish */
			if(i == 0) {
				p *= getEValue(given[i]);
			} else if(i > 0 && i < (fiveIndex)) {
				p *= 0.9; /* E stay */
				p *= getEValue(given[i]);
			} else if(i == fiveIndex) {
				p *= 0.1; /* E move */
				p *= get5Value(given[i]);
			} else if(i == (fiveIndex + 1)) {
				p *= 1.0; /* 5 move */
				p *= getIValue(given[i]);
			} else if(i > (fiveIndex + 1) && (i < threeIndex)) {
				p *= 0.9; /* I stay */
				p *= getIValue(given[i]);
			} else if(i == threeIndex) {
				p *= 0.1;
				p *= get3Value(given[i]);
			}
		}
		p *= 1.0; /* end prob value*/
		return p;
	}

	private static double get3Value(char c) {
		if(c == 'A') {
			return threeA;
		} else if(c == 'C') {
			return threeC;
		} else if(c == 'G') {
			return threeG;
		} else if(c == 'T') {
			return threeT;
		} else {
			System.out.println("3 Error");
			return -1;
		}
	}

	private static double getIValue(char c) {
		if(c == 'A') {
			return intronA;
		} else if(c == 'C') {
			return intronC;
		} else if(c == 'G') {
			return intronG;
		} else if(c == 'T') {
			return intronT;
		} else {
			System.out.println("I Error");
			return -1;
		}
	}

	private static double get5Value(char c) {
		if(c == 'A') {
			return fiveA;
		} else if(c == 'C') {
			return fiveC;
		} else if(c == 'G') {
			return fiveG;
		} else if(c == 'T') {
			return fiveT;
		} else {
			System.out.println("5 Error");
			return -1;
		}
	}

	private static double getEValue(char c) {
		if(c == 'A') {
			return entronA;
		} else if(c == 'C') {
			return entronC;
		} else if(c == 'G') {
			return entronG;
		} else if(c == 'T') {
			return entronT;
		} else {
			System.out.println("E Error");
			return -1;
		}
	}

	private static String getPath(int givenLength, Integer fiveIndex, int threeIndex) {
		char[] path = new char[threeIndex+1];
		for(int i = 0; i < (threeIndex+1); i++) { /* ends right after 3 */
			if(i < fiveIndex) {
				path[i] = 'E';
			} else if(i == fiveIndex) {
				path[i] = '5';
			} else if((i > fiveIndex) && (i < threeIndex)) {
				path[i] = 'I';
			} else if(i == threeIndex) {
				path[i] = '3';
			} else {
				System.out.println("Path Error");
			}
		}
		return (new String(path)).concat("End");
	}
}
