/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          program1
// FILE:             ScoreIteratorADT
//
// Authors: 
// Author1: Aleysha Becker, ambecker5@wisc.edu, ambecker5, lecture 001
//
// ---------------- OTHER ASSISTANCE CREDITS 
// NA 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A generic iterator that can track the current position in a list (in use will be a Scorelist)
 *
 * @author Aleysha
 */
public interface ScoreIteratorADT<E> {
	
	/**
	 * Returns the next Score in a Scorelist
	 *
	 * @return the generic next item in a list (in use will be the next score in a Scorelist)
	 */
	E next();
	
	/**
	 * A method that returns a boolean indicating whether or not there is a next item in the list being iterated
	 *
	 * @return (description of the return value)
	 */
	boolean hasNext();
	

}
