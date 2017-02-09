/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2016 
//PROJECT:          p1
//FILE:             GradeEstimator.java
//
//Authors: Sid Smith | sbsmith5@wisc.edu | sbsmith5 | 001
//Author1: (Michael Osmian,Osmian@wisc.edu,osmian,001)
//Author2: (name2,email2,netID2,lecture number2)
//
//---------------- OTHER ASSISTANCE CREDITS 
//Persons: Identify persons by name, relationship to you, and email. 
//Describe in detail the the ideas and help they provided. 
//
//Online sources: avoid web searches to solve your problems, but if you do 
//search, be sure to include Web URLs and description of 
//of any information you find. 
////////////////////////////80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * (Write a succinct description of this class here. You should avoid wordiness
 * and redundancy. If necessary, additional paragraphs should be preceded by
 * <p>
 * , the html tag for a new paragraph.)
 * 
 * <p>
 * Bugs: (a list of bugs and other problems)
 * 
 * @author Sid Smith
 */
public class GradeEstimator {

	private ScoreList scores;

	// private static String letterGrades;
	// private static String threshHolds;
	// private static String categoryNames;
	// private static String categoryWeights;

	/**
	 * This method creates a new instance of the GradeEstimator class, using the
	 * given filename gradeInfo. It throws an exception if the given file does
	 * not exist or is not properly formatted. If the file is properly
	 * formatted, this method stores the scores contained in the input file into
	 * a single ScoreList. That ScoreList, along with the other information from
	 * the file, should be stored in private fields of the new GradeEstimator
	 * instance. The method returns a reference to the newly constructed
	 * instance.
	 * 
	 * PRECONDITIONS: (i.e. the incoming list is assumed to be non-null)
	 * 
	 * POSTCONDITIONS: (i.e. the incoming list has been reordered)
	 * 
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {

		if (!gradeInfo.contains(".txt")) {
			throw new GradeFileFormatException();
		}

		File folder = new File(".");
		boolean foundFile = false;

		File gradeInfoFile = null;
		for (File file : folder.listFiles()) {
			if (file.getName() == gradeInfo) {
				gradeInfoFile = file;
				foundFile = true;

				Scanner scnr;
				scnr = new Scanner(gradeInfoFile);

				// letterGrades = scnr.nextLine();
				// threshHolds = scnr.nextLine();
				// categoryNames = scnr.nextLine();
				// categoryWeights = scnr.nextLine();
				//
				//
				//
				//
				//

				scnr.close();

			}
		}
		if (foundFile == false)
			throw new FileNotFoundException();

		//
		// String test = "";
		// // scanner object for the reading through the file
		// Scanner scnr;
		// // the name in the line of the file
		// String name = "";
		// // the address in the line of file
		// String address = "";
		//
		//
		// scnr = new Scanner(gradeInfoFile);
		//
		// test = scnr.nextLine();
		//
		//
		// scnr.close();

		return null;

	}

	/**
	 * This method constructs a String to display the weighted percentage and
	 * letter grade estimates based on the input from the grade info file.
	 * 
	 * PRECONDITIONS: (i.e. the incoming list is assumed to be non-null)
	 * 
	 * POSTCONDITIONS: (i.e. the incoming list has been reordered)
	 * 
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
	public String getEstimateReport() {
		return null;
		// This method constructs a String to display the weighted
		// percentage and letter grade estimates based on the input
		// from the grade info file. The expected format, along with several
		// input/output pair examples, is shown below. Brackets [ ] indicate
		// a piece of information that your program will need to fill in.

		// Do not print anything within this method. Return the String
		// that you construct and let the main() method handle the printing.

		// Note: If several assignments within a category have different
		// maximum scores, they are still worth the same amount, so you will
		// need to make some adjustments when calculating the correct average
		// score. It may help to consider that we are asking for the average
		// score for each category as a percent, or equivalently as a score out
		// of 100 points.

	}

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
		}
		try {
			String fileName = args[0];
			GradeEstimator g1 = GradeEstimator
					.createGradeEstimatorFromFile(fileName);
		} catch (GradeFileFormatException e) {
			System.out.println("Please enter a file name ending in .txt.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}

}