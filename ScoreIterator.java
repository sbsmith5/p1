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
	
public ScoreIterator(ScoreList list){
	this.myList = list;
	this.currPos = 0;
	
}
public Score next(){
	if(!hasNext()) throw new NoSuchElementException();
	
	//the result is the score at the iterator's position
	Score result = myList.get(currPos);
	//increments the iterator
	currPos++;

	return result;
}
	
	public boolean hasNext(){
		return currPos < myList.size();
	}
	
}
