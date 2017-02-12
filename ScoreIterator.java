/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (P1)
// FILE:             (ScoreIterator.java)
//
// Authors: (Team17)
// Author1: (Michael Osmian,osmian@wisc.edu,001)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A
// 
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.NoSuchElementException;



/**
 * (This is the Score Iterator that implements ScoreIteratorADT.
 * It allows us to iterate through the ScoreList of Scores.)
 *
 * <p>Bugs: N/A
 *
 * @author (Michael Osmian)
 */
public class ScoreIterator implements ScoreIteratorADT<Score> {
// private list of scores that references the ScoreList without changing it
private ScoreList myList;
//keeps track of the current position within the list
private int currPos;

//the category being iterated through
private String category;
	
/**
 *This is the ScoreIterator constructor that creates a new instance of the ScoreIterator class taking parameters for
 *a ScoreList and a category
 *
 * @param (list) the ScoreList to be iterated through
 * @param (category) the ScoreIterator will only iterate through Scores with the same category as the given parameter
 * @return a new instance of the ScoreIterator with the given list and category parameters and currPos = 0
 */

public ScoreIterator(ScoreList list, String category){
	this.myList = list;
	this.currPos = 0;
	this.category=category;
}

/**
 * The next method returns the next Score in the score list with the given category
 *
 * @param NA
 * @return the next Score in the given category
 */

public Score next(){
	if(!hasNext()) throw new NoSuchElementException();
	
	while(!myList.get(currPos).getCategory().equals(category.substring(0,1))){
		currPos++;
		//if the current Score is the wrong category, increment the current position
	}
	//the result is the score at the iterator's position
	Score result = myList.get(currPos);
	//increments the iterator
	currPos++;
	
	// increment through to the next Score with the correct category
	while(currPos < myList.size()){
		if(myList.get(currPos).getCategory().equals(category.substring(0,1))){
			break;
		}
		currPos++;
	}
	return result;

}
	
/**
 * The hasNext method returns a boolean corresponding to whether or not there is a next Score in the Scorelist
 *
 * @param NA
 * @return true if there is a next Score in the ScoreList; false otherwise
 */

	public boolean hasNext(){
		return currPos < myList.size();
	}
	
}
