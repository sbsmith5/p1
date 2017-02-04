/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p0
// FILE:             Score.java
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
 * This is an instantiable class used to create Score objects corresponding to a score for a single assignment. It has
 * multiple getter methods to access it's private fields, as well as a constructor .
 *
 * <p>Bugs: NA
 *
 * @author Aleysha Becker
 */
public class Score {
	
	private String name; //the name of the assignment
	private double ptsEarned; //the points earned on the assignment
	private double ptsPossible; //the max number of points that could be earned on the assignment
	
	/**
	 * This is the Score constructor that creates a new score object and assigns values to its private fields
	 * based on the passed parameters. It throws IllegalArgumentExceptions if the parameters don't fit the
	 * allowable values for its fields.
	 *
	 * @param assignmentName, the name of the assignment
	 * @param ptsEarned, the number of points earned on the assignment
	 * @param ptsPossible, the max possible number of points that could be earned on the assignment
	 * @return a new object of type Score
	 */
	public Score(String assignmentName, double ptsEarned, double ptsPossible) throws IllegalArgumentException {
		this.name = assignmentName;
		this.ptsEarned = ptsEarned;
		this.ptsPossible = ptsPossible;
		if (name == null) throw new IllegalArgumentException();
		if (ptsEarned < 0 || ptsPossible < 0) throw new IllegalArgumentException();
		if (ptsEarned > ptsPossible) throw new IllegalArgumentException();
	}
	
	/**
	 * This is a getter method used to access a score's private name field
	 *
	 * @return the String name of the assignment for the score
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This is a getter method used to access a score's private ptsEarned field
	 *
	 * @return a double corresponding to the number of points earned for the assignment
	 */
	public double getPoints() {
		return ptsEarned;
	}
	
	/**
	 * This is a getter method used to access the score's private ptsPossible field
	 *
	 * @return a double corresponding to the max number of points that could be earned for the assignment
	 */
	public double getMaxPossible() {
		return ptsPossible;
	}
	
	/**
	 * The getCategory method returns, as a string, the first character in the name of the assignment
	 *
	 * @return the first character in the assignment name, cast to a string
	 */
	public String getCategory() {
		return Character.toString(name.charAt(0));
	}
	
	/**
	 * This is a getter method used to calculate a percentage score using the ptsEarned and ptsPossible fields
	 *
	 * @return a double corresponding to the percentage of points earned out of points possible
	 */
	public double getPercent() {
		return (double) 100 * ptsEarned / ptsPossible;
	}
}
