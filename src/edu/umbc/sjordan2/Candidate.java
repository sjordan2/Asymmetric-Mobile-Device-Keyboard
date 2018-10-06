package edu.umbc.sjordan2;

public class Candidate {
	
	private String word;
	private Integer confidence;
	
	// This constructor, when called with a word as the only parameter, calls an alternate 
	// constructor with that word and the integer "1" as the default starting confidence.
	public Candidate(String word) {
		this(word, 1);
	}
	
	// This constructor, when called with a word and an integer as parameters, creates a new
	// instance of the "Candidate" data type, and sets its 'word' and 'confidence' properties.
	// Then, to allow for easy traversals later on, it adds the newly created Candidate to a 
	// static list located in the 'CandidateUtils' class.
	public Candidate(String word, Integer confidence) {
		this.word = word.toLowerCase();
		this.confidence = confidence;
		CandidateUtils.candidatesList.add(this);
		CandidateUtils.candidateWords.add(this.word);
		CandidateUtils.candidateConfidences.add(this.confidence);
	}
	
	// Returns the word associated with this Autocomplete Candidate
	public String getWord() {
		return word;
	}
	
	// Returns the confidence (how many times it occurs across all passages) 
	// associated with this Autocomplete Candidate
	public Integer getConfidence() {
		return confidence;
	}
	
	// When a new occurrence of a word is encountered, the confidence of the
	// associated Autocomplete Candidate is incremented by 1, making it more likely. Additionally,
	// the value of the candidate in the Candidate Confidence list is also incremented by 1.
	public void incrementConfidence() {
		confidence++;
		int index = CandidateUtils.candidatesList.indexOf(this);
		CandidateUtils.candidateConfidences.set(index, CandidateUtils.candidateConfidences.get(index) + 1);
	}
	
}
