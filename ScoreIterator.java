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

private String category;
	
public ScoreIterator(ScoreList list, String category){
	this.myList = list;
	this.currPos = 0;
	this.category=category;
}
public Score next(){

	if(!hasNext()) throw new NoSuchElementException();
	while(!myList.get(currPos).getCategory().equals(category.substring(0,1))){
		currPos++;
	}
	//the result is the score at the iterator's position
	Score result = myList.get(currPos);
	//increments the iterator
	currPos++;
	while(currPos < myList.size()){
		if(myList.get(currPos).getCategory().equals(category.substring(0,1))){
			break;
		}
		currPos++;
	}
	return result;

}
	
	public boolean hasNext(){
		return currPos < myList.size();
	}
	
}
