/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p0
// FILE:             GradeFileFormatException.java
//
// Author1: (Aleysha Becker, ambecker5@wisc.edu, ambecker5, lecture 1)
// Author2: (Michael Osmian,osmian@wisc.edu,001)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: NA
// 
// Online sources: NA
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
