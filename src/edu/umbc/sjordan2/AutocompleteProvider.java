package edu.umbc.sjordan2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class AutocompleteProvider {
	
	// This algorithm, given a text passage, splits it up by spaces into single words,
	// which are then used as the basis to create a new Candidate object for each word. 
	// If a candidate already exists for that word, then the confidence of that 
	// candidate is increased by 1.
	public void train(String passage) {
		String[] splitWords = StringUtils.split(passage, " ");
		for(String s : splitWords) {
			CandidateUtils cUtils = new CandidateUtils();
			if(cUtils.getCandidate(s) == null) {
				new Candidate(s);
			} else {
				cUtils.getCandidate(s).incrementConfidence();
			}
		}
	}
	
	// Given a fragment of an input word, this function returns a list of candidate 
	// words that contain that fragment at the beginning of the candidate word. This 
	// list of candidate words is sorted by order of order of the confidence values 
	// of the candidates in the list (bigger confidence candidates first).
	public List<Candidate> getWords(String fragment) {
		List<Candidate> validWords = new ArrayList<Candidate>();
		for(Candidate c : CandidateUtils.candidatesList) {
			if(c.getWord().length() >= fragment.length()) {
				if(StringUtils.containsIgnoreCase(c.getWord().substring(0, fragment.length()), fragment)) {
					validWords.add(c);
				} else {
					continue;
				}
			}
		}
		Collections.sort(validWords, new Comparator<Candidate>() {
		    @Override
		    public int compare(Candidate c1, Candidate c2) {
		        return c2.getConfidence().compareTo(c1.getConfidence());
		    }
		});
		return validWords;
	}

}
