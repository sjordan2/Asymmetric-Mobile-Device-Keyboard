package edu.umbc.sjordan2;

import java.util.ArrayList;
import java.util.List;

public class CandidateUtils {
	
	// This list stores all Candidates retrieved from the train() function. It serves as a
	// means for traversals through the entire collection of Candidates in the program. 
	public static List<Candidate> candidatesList = new ArrayList<Candidate>();
	
	// This list stores all candidate words. A word is in the same index position as the
	// candidate object it represents in the main candidate list. For now, its only purpose
	// is to support the message dialog brought up upon clicking "Show Current Candidates".
	public static List<String> candidateWords = new ArrayList<String>();
	
	// This list stores all candidate confidences. A confidence is in the same index position
	// as the candidate object it represents in the main list. For now, its only purpose
	// is to support the message dialog brought up upon clicking "Show Current Candidates".
	public static List<Integer> candidateConfidences = new ArrayList<Integer>();
	
	// Given a word, this function returns the Candidate object associated with that word.
	// This function ignores capitalization.
	public Candidate getCandidate(String word) {
		Candidate foundCandidate = null;
		for(Candidate c : candidatesList) {
			if(c.getWord().equalsIgnoreCase(word)) {
				foundCandidate = c;
				break;
			} else {
				continue;
			}
		}
		return foundCandidate;
	}

}
