/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p1
// FILE:             GradeFileFormatException.java
//
// Authors: Michael Osmian, Aleysha Becker, Sidney Smith, Yuqi Wei, Vanessa Chavez,
// Roberto O'dogherty)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A
// 
// Online sources: N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is a customized checked exception, when the file format is not recognized.
 *
 * <p>Bugs: NA
 *
 * @author Michael Osmian
 */

public class GradeFileFormatException extends Exception{

/**
 * This is the general constructor with no parameter type.
 */
public GradeFileFormatException () {
	super();
}
/**
 *This is the exception constructor when passing in a message to display
 *to the user after it is handled. 
 *
 * @param msg, The string message to display to the user.
 */
public GradeFileFormatException(String msg){
	super(msg);
}
}
