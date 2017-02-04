/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p0
// FILE:             ScoreList.java
//
// Authors: Aleysha Becker
// Author1: (Aleysha Becker, ambecker5@wisc.edu, ambecker5, lecture 1)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: NA
// 
// Online sources: NA
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * ScoreList is an instantiable class implementing the ScoreListADT. It is intended to store a list of Scores
 * and has methods to handle adding, removing, and getting scores from the list, as well as a method to expand
 * the list if it isn't large enough.
 *
 * <p>Bugs: NA
 *
 * @author Aleysha Becker
 */
public class ScoreList implements ScoreListADT{
	
	private int numItems; // the number of scores that has been added to the list
	private Score[] scores = new Score[10]; //an array of the sores in ScoreList
	
	/**
	 * The size method returns how many items have been added to the list
	 * 
	 * @return an int value corresponding to how many scores have been added to the list
	 */
	public int size() {
		return numItems;
	}

	/**
	 * the add method takes a new Score object and adds it to the end of the score list. it throws a new
	 * IllegalArgumentException if the score to be added is null
	 * 
	 * @param s, a Score object to be added to the end of the list
	 * @return void
	 */
	public void add(Score s) throws IllegalArgumentException {
		if (s == null) throw new IllegalArgumentException();
		scores[numItems] = s;
		numItems++;
		if (numItems == scores.length) {
			expand();
		}
	}
	
	/**
	 * This method is called when an item is being added to the score list and there isn't room for it; it doubles
	 * the size of the array holding the scores
	 * 
	 * @return void
	 */
	public void expand(){
		// a new, larger array that will have all the scores copied to it, and then it will be reassigned to scores
		Score[] a = new Score[scores.length * 2];
		for (int i  = 0; i < numItems; i++) {
			a[i] = scores[i];
		}
		scores = a;
	}
	
	/**
	 * the remove method takes an index parameter and removes it from the list by shifting all other elements
	 * in the list over by one index. the removed score is then returned. it throws an IndexOutOfBoundsException if i
	 * isn't between 0 and scores.length - 1
	 *
	 * @param i, the index of the score that will be removed
	 * @return the score at position i in the score list
	 */
	public Score remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > scores.length - 1) 
			throw new IndexOutOfBoundsException();
		Score score = scores[i]; //the score that will be returned at the end
		while (i < numItems - 1) {
			scores[i] = scores[i+1]; //shift all the scores in the list over by one index
			i++;
		}
		scores[numItems - 1] = null; // make the last item null (otherwise there would be a duplicate at the end)
		numItems--; 
		return score;
	}
	
	/**
	 * this is a getter method used to retrieve a score at index i in the list
	 *
	 * @param i, the index of the score to be retrieved
	 * @return the score at position i in the list
	 */
	public Score get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > scores.length - 1)
			throw new IndexOutOfBoundsException();
		return scores[i];
	}
}
