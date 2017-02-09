
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

public class GradeEstimator {

	private ScoreList scores = new ScoreList();
	private ArrayList<String> letters = new ArrayList<String>();
	private ArrayList<Double> thresholds = new ArrayList<Double>();
	private ArrayList<String> assignmentNames = new ArrayList<String>();
	private ArrayList<Double> assignmentWeight = new ArrayList<Double>();

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
		}

		// Testing with the config file

		args[0] = Config.GRADE_INFO_FILE_FORMAT_EXAMPLE;

		try {

			createGradeEstimatorFromFile(args[0]);
			// getEstimateReport();

		} catch (GradeFileFormatException e) {
			System.out.println("GradeFileFormatException ");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} finally {

		}

	}

	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {

		GradeEstimator g1 = new GradeEstimator();
		Scanner scnr;
		scnr = new Scanner(gradeInfo);

		String storedLine;

		// for (int i = 0; i < 4; i++) {

		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.letters.add(storedLine.substring(0, storedLine.indexOf(' ')));

				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.letters.add(storedLine.substring(0));
				storedLine = "";
			}
		} while (storedLine.length() > 0);

		for (int i = 0; i < g1.letters.size(); i++) {
			System.out.print(g1.letters.get(i));
		}
		System.out.println();

		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.thresholds.add((Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')))));
				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.thresholds.add(Double.parseDouble(storedLine.substring(0)));
				storedLine = "";
			}
		} while (storedLine.length() > 0);

		for (int i = 0; i < g1.thresholds.size(); i++) {
			System.out.print(g1.thresholds.get(i));
		}
		System.out.println();

		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.assignmentNames.add(storedLine.substring(0, storedLine.indexOf(' ')));

				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.assignmentNames.add(storedLine.substring(0));
				storedLine = "";
			}
		} while (storedLine.length() > 0);

		for (int i = 0; i < g1.assignmentNames.size(); i++) {
			System.out.print(g1.assignmentNames.get(i));
		}
		System.out.println();

		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.assignmentWeight.add((Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')))));

				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.assignmentWeight.add(Double.parseDouble(storedLine.substring(0)));
				storedLine = "";
			}
		} while (storedLine.length() > 0);

		for (int i = 0; i < g1.assignmentWeight.size(); i++) {
			System.out.print(g1.assignmentWeight.get(i));

		}
		System.out.println();

		while (scnr.hasNextLine()) {
			storedLine = scnr.nextLine();
			String n = storedLine.substring(0, storedLine.indexOf(' '));
			storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
			double pE = Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')));
			storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
			double pP = Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')));
			storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
			Score a = new Score(n, pE, pP);
			g1.scores.add(a);
		}
		
		System.out.println(g1.scores.get(0).getPercent());

		return g1;

	}


	
	
	
	public String getEstimateReport() {
		return null;

	}

}
