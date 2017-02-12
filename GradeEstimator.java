
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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Write a succinct description of this class here. You should avoid wordiness
 * and redundancy. If necessary, additional paragraphs should be preceded by
 * <p>
 * 
 * , the html tag for a new paragraph.)
 * 
 * <p>
 * Bugs: (a list of bugs and other problems)
 * 
 * @author Sid Smith
 */
public class GradeEstimator {

	private ScoreList scores = new ScoreList();
	// private String[] boundaries;
	private ScoreIterator[] categoryIterators;
	private String[] letterGrades;
	private String[] boundaries;
	private double[] thresholds;
	private String[] categories;
	private String[] categoryWeights;
	private double[] weights;

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
	 * @param (parameter
	 *            name) (Describe the first parameter here)
	 * @param (parameter
	 *            name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {

		GradeEstimator g1 = new GradeEstimator();

		if (!gradeInfo.endsWith(".txt")) {
			throw new GradeFileFormatException();
		}

		File folder = new File(".");
		boolean foundFile = false;

		File gradeInfoFile = null;
		for (File file : folder.listFiles()) {

			if (file.getName().equals(gradeInfo)) {

				gradeInfoFile = file;

				foundFile = true;
				String temp;
				Scanner scnr;
				scnr = new Scanner(gradeInfoFile);

				// The first four lines of the file are assigned to indiviudual
				// arrays

				temp = g1.trimLines(scnr.nextLine());
//				if(scnr.hasNextInt() || scnr.hasNextDouble()){
//					throw new GradeFileFormatException();
//				}
				g1.letterGrades = temp.split(" ");
				temp = g1.trimLines(scnr.nextLine());

				g1.boundaries = temp.split(" ");
				g1.thresholds = new double[g1.boundaries.length];

				for (int i = 0; i < g1.boundaries.length; i++) {

					g1.thresholds[i] = Double.valueOf(g1.boundaries[i]);

				}
				
				temp = g1.trimLines(scnr.nextLine());
				g1.categories = temp.split(" ");
				temp = g1.trimLines(scnr.nextLine());
				g1.categoryWeights = temp.split(" ");
				g1.weights = new double[g1.categoryWeights.length];
				for (int i = 0; i < g1.categoryWeights.length; i++) {
					g1.weights[i] = Double.valueOf(g1.categoryWeights[i]);
				}

				String n;
				double pE;
				double pP;
				String[] remLines;

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
	 * PRECONDITIONS: (i.e. the incoming list is assumed to be non-null)
	 * 
	 * POSTCONDITIONS: (i.e. the incoming list has been reordered)
	 * 
	 * @param (parameter
	 *            name) (Describe the first parameter here)
	 * @param (parameter
	 *            name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
	public String getEstimateReport() {
		for(int i=0;i<categories.length;i++){
			ScoreIterator itr = new ScoreIterator(scores, categories[i].substring(0, 1));
			while(itr.hasNext()){
				Score temp = itr.next();
				System.out.print(temp.getName() + "  " + String.format("%7.2f", temp.getPercent()) + "\n");
			}
		}
		String estimateReport = "Grade Estimate is based on " + scores.size() + " scores \n";
		
		double entireAverage = 0.0;
		for(int i=0;i<categories.length;i++){
			double averageScore = 0.0;
			int scoreCount = 0;
			ScoreIterator itr = new ScoreIterator(scores, categories[i].substring(0, 1));
			while(itr.hasNext()){
				averageScore += itr.next().getPercent();
				scoreCount++;
			}
			double average = averageScore/scoreCount;
			
			entireAverage+=(average*(weights[i])/100);
		
			estimateReport = estimateReport	+ String.format("%7.2f",(((average*(weights[i]))/100)) ) + "% = "+ String.format("%7.2f", average)
			+ "% of "+ String.format("%2.0f", weights[i])+"% for";
			estimateReport = estimateReport +" " + categories[i] + "\n";
		}
		estimateReport = estimateReport + "--------------------------------\n";
		estimateReport = estimateReport + String.format("%7.2f",entireAverage) + "% weighted percent\n";
		for(int j=0;j<thresholds.length;j++){
			if(entireAverage>=thresholds[j]){
				estimateReport = estimateReport + "Letter Grade Estimate: " + letterGrades[j];
				break;
			}
		}
		
		
		 return estimateReport;
		 
		}
//		

	

//	private ScoreIterator[] getIterators() {
//		categoryIterators = new ScoreIterator[categories.length];
//		String temp;
//
//		for (int k = 0; k < categories.length; k++) {
//			temp = categories[k];
//			categoryIterators[k] = new ScoreIterator(scores, temp);
//		}
//
//		return categoryIterators;
//
//	}

	
//	 private double getCategoryScore(String category) {
//	
//	 double score = 0;
//	 int count = 0;
//	
//	 ScoreIterator itr = new ScoreIterator(scores);
//	
//	 while (itr.hasNext()) {
//	 Score temp;
//	
//	 temp = itr.next();
//	 System.out.println(temp.getPercent());
//	 if (temp.getCategory().equals(category)) {
//		
//	 score += temp.getPercent();
//	 count++;
//	
//	 }
//	 }
//	
//	 return score / count;
//	
//	 }

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
		}
		try {
			String fileName = args[0];

			System.out.println(createGradeEstimatorFromFile(fileName).getEstimateReport());

			// System.out.print(g1.getEstimateReport());
		} catch (GradeFileFormatException e) {
			System.out.println("Please enter a file name ending in .txt.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

		;

	}

}