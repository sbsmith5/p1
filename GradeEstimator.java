
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2016 
//PROJECT:          p1
//FILE:             GradeEstimator.java
//
//Author1: (Michael Osmian,Osmian@wisc.edu,osmian,001)
//Author2: (name2,email2,netID2,lecture number2)
//Author3: (Aleysha Becker, ambecker5@wisc.edu, ambecker5, lecture 1)
//
//---------------- OTHER ASSISTANCE CREDITS 
//Persons: N/A
//
//Online sources: N/A
////////////////////////////80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (This class creates a GradeEstimator instance and reads from a given text
 * file. The values it reads in are grade information which is then used by this
 * class to create an accurate grade estimate report.)
 * 
 * 
 * 
 * @author Michael Osmian
 */
public class GradeEstimator {

	// The ScoreList which implements our ScoreListADT
	private ScoreList scores = new ScoreList();
	// private String[] boundaries;
	private String[] letterGrades;
	// Initial array of category thresholds of type string
	private String[] boundaries;
	// The array of thresholds taken from the initial array called boundaries
	private double[] thresholds;
	// The array of category names
	private String[] categories;
	// The array of category weights of type string
	private String[] categoryWeights;
	// The array of category weights of type double
	private double[] weights;

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
	 * @param (String
	 *            gradeInfo) (The name of the file which is passed into the main
	 *            method through the command line)
	 * @return (GradeEstimator g1) (The instance of GradeEstimator)
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {

		// Creates instance of the GradeEstimator class called g1
		GradeEstimator g1 = new GradeEstimator();

		// If the file name which was passed in does not end in .txt
		if (!gradeInfo.endsWith(".txt")) {
			throw new GradeFileFormatException();
		}

		// Creates a list of files within the current directory
		File folder = new File(".");
		boolean foundFile = false;

		File gradeInfoFile = null;

		// Iterates through the current directory to find one that matches the
		// user's input for a filename
		for (File file : folder.listFiles()) {

			if (file.getName().equals(gradeInfo)) {

				// When a match is found, it sets the found file to the one that
				// matches the name
				gradeInfoFile = file;

				foundFile = true;

				// This variable will continously change as the scanner reads
				// every line
				String temp;
				Scanner scnr;

				// Scanner is created that reads from a given file
				scnr = new Scanner(gradeInfoFile);
				if ( (scnr.hasNextDouble() || scnr.hasNextInt())) {
					scnr.close();
					throw new GradeFileFormatException();
				}

				// This string is set to the trimmed version of the scanner's
				// next line
				temp = g1.trimLines(scnr.nextLine());
				if (! (scnr.hasNextDouble() || scnr.hasNextInt())) {
					scnr.close();
					throw new GradeFileFormatException();
				}
				// Splits this temp line into an array of strings, in this case
				// the letters.
				g1.letterGrades = temp.split(" ");
				temp = g1.trimLines(scnr.nextLine());

				// Creates an array of strings that contain the thresholds for
				// each category
				g1.boundaries = temp.split(" ");

				// Sets the double array to the length of the array of strings
				// containing boundaries
				g1.thresholds = new double[g1.boundaries.length];

				// This loop assigns the double value of each index in the array
				// of boundaries to that of thresholds
				for (int i = 0; i < g1.boundaries.length; i++) {
					g1.thresholds[i] = Double.valueOf(g1.boundaries[i]);
				}
				// temp now eauals the third line of the file which contains
				// categories and sets it to an array of strings
				temp = g1.trimLines(scnr.nextLine());
				g1.categories = temp.split(" ");
				// temp now eauls the array of category weights but it is of
				// type string
				temp = g1.trimLines(scnr.nextLine());
				g1.categoryWeights = temp.split(" ");
				g1.weights = new double[g1.categoryWeights.length];
				// iterates through the array of strings of the weights and
				// assigns the value to a new array of doubles
				for (int i = 0; i < g1.categoryWeights.length; i++) {
					g1.weights[i] = Double.valueOf(g1.categoryWeights[i]);
				}

				String n;
				double pE;
				double pP;
				String[] remLines;

				// Loops through the remainder of the file that contains all of
				// the score information, and adds these new score instances to
				// the list of scores
				while (scnr.hasNextLine()) {
					temp = g1.trimLines(scnr.nextLine());
					remLines = temp.split(" ");

					n = remLines[0];
					pE = Double.valueOf(remLines[1]);
					pP = Double.valueOf(remLines[2]);
					g1.scores.add(new Score(n, pE, pP));
				}

				scnr.close();

				break;
			} else {
				foundFile = false;
			}
		}

		if (foundFile == false) {
			throw new FileNotFoundException();
		}

		return g1;

	}

	/**
	 * This method takes a string input which is the scanner's current line and
	 * edits it to get rid of potential characters and extra spaces.
	 * 
	 * 
	 * @param (String
	 *            Line) (The line being passed in)
	 * @return (String Line) (The trimmed and edited line)
	 */
	private String trimLines(String Line) {
		if (Line.contains("#")) {
			Line = Line.substring(0, Line.indexOf('#'));
		}
		Line = Line.trim();

		return Line;

	}

	/**
	 * This method constructs a String to display the weighted percentage and
	 * letter grade estimates based on the input from the grade info file.
	 * 
	 * @return (String estimateReport) (The String which includes the entire
	 *         estimateReport)
	 */
	public String getEstimateReport() {
		// This loops through the list of categories and within each category it
		// iterates through the scores and displays each Score's information
		for (int i = 0; i < categories.length; i++) {
			ScoreIterator itr = new ScoreIterator(scores, categories[i].substring(0, 1));
			while (itr.hasNext()) {
				Score temp = itr.next();
				System.out.print(temp.getName() + "  " + String.format("%7.2f", temp.getPercent()) + "\n");
			}
		}
		String estimateReport = "Grade Estimate is based on " + scores.size() + " scores \n";
		// The entire average of every score
		double entireAverage = 0.0;

		// This loops through the categories again and edits the estimatereport
		// string along with it. The iterator pulls the percentages from every
		// score and totals them all up so we can use this information within
		// the loop and create the grade estimate report
		for (int i = 0; i < categories.length; i++) {
			double averageScore = 0.0;
			int scoreCount = 0;
			ScoreIterator itr = new ScoreIterator(scores, categories[i].substring(0, 1));
			while (itr.hasNext()) {
				averageScore += itr.next().getPercent();
				scoreCount++;
			}
			double average = averageScore / scoreCount;

			entireAverage += (average * (weights[i]) / 100);

			estimateReport = estimateReport + String.format("%7.2f", (((average * (weights[i])) / 100))) + "% = "
					+ String.format("%7.2f", average) + "% of " + String.format("%2.0f", weights[i]) + "% for";
			estimateReport = estimateReport + " " + categories[i] + "\n";
		}
		estimateReport = estimateReport + "--------------------------------\n";
		estimateReport = estimateReport + String.format("%7.2f", entireAverage) + "% weighted percent\n";
		
		boolean getLetter = false;
		while (!getLetter) {
			for (int j = 0; j < thresholds.length; j++) {
				if(entireAverage >= thresholds[j]) {
					estimateReport = estimateReport + "Letter Grade Estimate: " + letterGrades[j];
					getLetter = true;
					break;
				}
			}
			if (!getLetter) {
				estimateReport += "Letter Grade Estimate: unable to estimate letter grade for " + entireAverage;
			}
			break;
		}

		return estimateReport;

	}

	/**
	 * This is the main method of the class, it calls two methods which read
	 * from a file and create a grade estimate report from it as well.
	 * 
	 * @param (String[]
	 *            args) (The file input)
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
		}
		try {
			String fileName = args[0];

			System.out.println(createGradeEstimatorFromFile(fileName).getEstimateReport());

		} catch (GradeFileFormatException e) {
			System.out.println("GradeFileFormatException");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}

}