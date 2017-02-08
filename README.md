# HMM-with-State-3
Assignment 3 of ICS 475 </br>
Generate the list of all paths with non-zero probabilities that could have generated the sequence CTTCATGTGAAAGCAGACGTAAGTCA, as well as the corresponding probability(log). Print the best path.

Implement the Hidden Markov Model (HMM) from S. R. Eddy (2004) and add one state “state 3” that should follow state I. Transition probabilities for state I should be 0.9 to stay on I and 0.1 to move to state 3. Emission probabilities for state 3 should be A = 0.01, C = 0, G = 0.99 and T = 0. Transitions probability for state 3 should be 1.0 to move to End state.

### Instructions
$ javac HMMPaths.java </br>
$ java HMMPaths

### Example of program's output
Path:  EEEEEEEEEEEEEEEEEEEEEE5IIIEnd</br>
  -41.7133978419</br>
Path:  EEEEEEEEEEEEEEEEEEEEE5IIIIEnd</br>
  -45.5741275529</br>
Path:  EEEEEEEEEEEEEEEEEEEE5IIIIIEnd</br>
 -45.1041239237</br>
Path:  EEEEEEEEEEEEEEEEEE5IIIIIIIEnd</br>
  -41.219677686</br>
Path:  EEEEEEEEEEEEEEEE5IIIIIIIIIEnd</br>
  -45.9966981289</br>
Path:  EEEEEEEEEEEEEEE5IIIIIIIIIIEnd</br>
  -42.5822555205</br>
Path:  EEEEEEEEEEEEEE5IIIIIIIIIIIEnd</br>
 -46.4429852316</br>
Path:  EEEEEEEEEEEE5IIIIIIIIIIIIIEnd</br>
  -43.944833355</br>
Path:  EEEEEEEEEEE5IIIIIIIIIIIIIIEnd</br>
  -47.8055630661</br>
Path:  EEEEEEEEEE5IIIIIIIIIIIIIIIEnd</br>
  -47.3355594368</br>
Path:  EEEEEEEEE5IIIIIIIIIIIIIIIIEnd</br>
  -46.8655558076</br>
Path:  EEEEEEEE5IIIIIIIIIIIIIIIIIEnd</br>
  -43.4511131992</br>
Path:  EEEEEE5IIIIIIIIIIIIIIIIIIIEnd</br>
  -43.8974003018</br>
Path:  EEEE5IIIIIIIIIIIIIIIIIIIIIEnd</br>
  -47.2881263836</br>
Best :  (-41.21967768602254, 'EEEEEEEEEEEEEEEEEE5IIIIIIIEnd')</br>
