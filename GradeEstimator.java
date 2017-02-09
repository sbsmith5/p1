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
 * , the html tag for a new paragraph.)
 * 
 * <p>
 * Bugs: (a list of bugs and other problems)
 * 
 * @author Sid Smith
 */
public class GradeEstimator {

	private ScoreList listOfScores;
	//private String[] boundaries;
	
	private String[] letterGrades;
	private double[] boundaries;
	private String[] categories;
	private double[] categoryWeights;

	private GradeEstimator(ScoreList listOfScores, String[] cutOffs){
		
		this.listOfScores = listOfScores;
		
		 letterGrades = cutOffs[0].split(" ");
		 
		 ////////////
		 this.boundaries= new double[cutOffs[1].split(" ").length];
		 
		 int m=0;
		 for(String i: cutOffs[1].split(" ")){
			 this.boundaries[m] = Double.parseDouble(i);
			 m++;
		 }
		 /////////////
		 
		 
		 ////////////
		 this.categories= new String[cutOffs[2].split(" ").length];
		 
		 int p=0;
		 for(String i: cutOffs[2].split(" ")){
			 this.boundaries[p] = i.charAt(0);
			 m++;
		 }
		 /////////////
		 
		 ////////////
		 categoryWeights= new double[cutOffs[3].split(" ").length];
		 
		 m=0;
		 for(String i: cutOffs[3].split(" ")){
			 this.boundaries[m] = Double.parseDouble(i);
			 m++;
		 }
		 /////////////
		
	}
	
	
	
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

		ArrayList<String> fileArray;
		ScoreList scores = new ScoreList();
		
		if (!gradeInfo.endsWith(".txt")) {
			throw new GradeFileFormatException();
		}

		File folder = new File(".");
		boolean foundFile = false;

		fileArray = new ArrayList<String>();
		
		String[] cutOffs = new String[3];
		
		File gradeInfoFile = null;
		for (File file : folder.listFiles()) {
			if (file.getName().equals(gradeInfo)) {
				gradeInfoFile = file;
				foundFile = true;

				Scanner scnr;
				scnr = new Scanner(gradeInfoFile);

				while(scnr.hasNextLine()){
					
					fileArray.add(scnr.nextLine());

				}

				scnr.close();
				
				
				
				for(int i=4; i < fileArray.size(); i++){
					
					cutOffs = fileArray.get(i).split(" ");
					scores.add(new Score(cutOffs[0], 
							(double) Double.parseDouble(cutOffs[1]), 
							(double) Double.parseDouble(cutOffs[2])));
				}
				
				break;
			}
		}
		if (!foundFile)
			throw new FileNotFoundException();
		
		return new GradeEstimator(scores, cutOffs);

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

		String toReturn = "";
		
		ScoreIterator itr = new ScoreIterator(listOfScores);
		
		while(itr.hasNext()){
			Score temp;
			temp = itr.next();
			toReturn += temp.getName() + "\t" + temp.getPercent() + "\n";
		}
		
		toReturn += "Grade estimate is based on " + listOfScores.size() + " scores.\n";
		
		double overallScore;
		double temp;
		double weightedTotal = 0;
		int counter = 0;
		
		for(int t = 0; t < categories.length; t++){
			
			temp = getCategoryScore(categories[t])/100;
			
			weightedTotal += temp;
			
			toReturn += (categoryWeights[t] * temp/100) + "% = " + 
			temp + "% * " +categoryWeights[t] +"% for " + categories[t] +"\n";
			counter++;
		}
		
		toReturn += "-------------------------------- \n\t";
		
		toReturn += weightedTotal/counter + "% weighted percent\n";
		
		toReturn += "Letter Grade Estimate: ";
		
		for(int a = 0; a < boundaries.length; a++){
			if(weightedTotal/counter > boundaries[a]){
				toReturn += letterGrades[a];
				break;
			}
		}
		
		return toReturn;
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
	
	private double getCategoryScore(String category){
		
		double score = 0;
		int count = 0;
		
		ScoreIterator itr = new ScoreIterator(listOfScores);
		
		while(itr.hasNext()){
			Score temp;
			
			temp = itr.next();
			
			if(temp.getCategory().equals(category)){
			
				score += temp.getPercent();
				count++;
				
			}
		}
		
		return score/count;
		
	}
	

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
		}
		try {
			String fileName = args[0];
			GradeEstimator g1 = GradeEstimator
					.createGradeEstimatorFromFile(fileName);
			System.out.print(g1.getEstimateReport());
		} catch (GradeFileFormatException e) {
			System.out.println("Please enter a file name ending in .txt.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		;

	}

}