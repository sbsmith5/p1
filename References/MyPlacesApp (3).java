//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Program 4: Places
// Files:            Place.java MyPlacesApp.java PlaceList.java
// Semester:         cs302 Fall 2016
//
// Author:           Michael Osmian IVChristian Colomb
// Email:            osmian@wisc.edu
// CS Login:         osmian-iv
// Lecturer's Name:  Gary Dahl
// Lab Section:      333
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:     Christian Colomb
// Partner Email:    ccolomb@wisc.edu
// Partner CS Login: ccolomb
// Lecturer's Name:  Gary Dahl
// Lab Section:      335
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    _X__ Write-up states that Pair Programming is allowed for this assignment.
//    _X__ We have both read the CS302 Pair Programming policy.
//    _X__ We have registered our team prior to the team registration deadline.
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * The MyPlacesApp class is responsible for managing all of the the user input and output
 * the places, and reading, writing, and displaying files, and catching errors in user
 * input and file management. If the user wants to add a place, this class prompts the
 * user for the appropriate values, creates a new place object, and adds the new place
 * to the list of places. If the user wants to read a file, this class displays the 
 * available files, takes input for the users choice, and reads the places from the
 * file, adding the places to the list. If the user chooses to write a file, this 
 * class displays the names of the files that already exist and creates text file with
 *  the places in the list given a name that the user inputs, assuming the file doesn't
 *  already exist.  The user can also remove places or have them displayed.
 *  
* @author Michael Osmian
 */

import java.io.File;
import java.io.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyPlacesApp {
	// this placelist object holds the places that should be in memory
	private static PlaceList list;

	public static void main(String[] args) {
		// instantiates the placelist
		list = new PlaceList();
		// Scanner object for taking input and displaying output
		Scanner scnr = new Scanner(System.in);
		// create a default variable for the menu choice
		String menuChoice = "p";
		// variable for continuing when a place with the same name is tried to
		// be added
		boolean weBoolin = false;
		// divide for formatting the outputs
		final String DIVIDER = "--------------------------";
		// variable for taking the user input
		String userInput;

		System.out.println();

		// loop until the user wants to quit
		do {

			// dispay the header
			System.out.println("My Places 2016");
			System.out.println(DIVIDER);

			if (list.size() == 0) {
				System.out.println("No places in memory.");
				System.out.println(DIVIDER);
				System.out.print("A)dd R)ead Q)uit: ");
			} else {
				// display the list if there any places in the list
				for (int i = 0; i < list.size(); i++) {
					System.out.println(i + 1 + ") " + list.get(i).getName());
				}
				System.out.println(DIVIDER);
				System.out.print("A)dd S)how D)elete R)ead W)rite Q)uit: ");
			}

			// format the input to lowercase for easier decision making
			userInput = scnr.nextLine();
			menuChoice = userInput.toLowerCase();

			if (menuChoice.length() == 1) {

				if (menuChoice.charAt(0) == 'q' || menuChoice.charAt(0) == 'Q') {
					System.out.println("Thank you for using My Places 2016!");

				} else if (menuChoice.charAt(0) == 'a') {
					
					//create a new place for adding to the list
					Place place = new Place(null, null);
					System.out.print("Enter the name: ");
					String name = scnr.nextLine();
					
					//check if the name is already in the list
					for (int i = 0; i < list.size(); i++) {
						if (name.equals(list.get(i).getName())) {
							System.out.println(name + " already in list.");

							weBoolin = true;
							continue;
						}
					}
					if (weBoolin) {
						weBoolin = false;
						continue;
					}
					
					//set the name and address for the place and add it to the list
					place.setName(name);
					System.out.print("Enter the address: ");
					place.setAddress(scnr.nextLine());
					System.out.println("Adding: " + place.getName());
					list.add(place);

				} else if (menuChoice.charAt(0) == 'd') {
					try {
						System.out.print("Enter number of place to Delete: ");

						// number of place to be deleted
						int deleteP = scnr.nextInt();
						
						scnr.nextLine();
						
						System.out.println("Deleting: " + list.get(deleteP - 1).getName());
						
						list.remove(deleteP - 1);
					} catch (IndexOutOfBoundsException outOfBounds) {
						System.out.println("Expected a number between 1 and " + list.size() + ".");
					}
				} else if (menuChoice.charAt(0) == 's') {
					try {
						System.out.print("Enter number of place to Show: ");

						// the number of the place to display
						int showPlace = scnr.nextInt();
						System.out.println(list.get(showPlace - 1).getName());
						System.out.println(list.get(showPlace - 1).getAddress());
						scnr.nextLine();
					} catch (IndexOutOfBoundsException outOfBounds) {
						System.out.println("Expected a number between 1 and " + list.size() + ".");
					}
				} else if (menuChoice.charAt(0) == 'r' || menuChoice.charAt(0) == 'w') {
					ArrayList<String> availFiles=displayFiles();
					
					if (menuChoice.charAt(0) == 'r') {
						System.out.print("\nEnter filename: ");
						String fileName = scnr.nextLine();
						System.out.println("Reading file: " + fileName);
						try {
							//add the places from the file to the list
							addPlacesFromFile(new File(fileName));
							
						} catch (FileNotFoundException except) {
							System.out.println("Unable to read from file: " + fileName);
						}catch (NoSuchElementException excpet){
							
						}

					} else if (menuChoice.charAt(0) == 'w') {
						
						System.out.print("\nEnter filename: ");
						String fileName = scnr.nextLine();
						System.out.println("Writing file: " + fileName);
						try {
							//create the file with the given name
							createFile(fileName);
						} catch (IOException except) {
							System.out.println("Unable to write to file: " + fileName);
						}

					}
				} else {
					System.out.println("Unrecognized choice: " + userInput);
				}

				if (menuChoice.charAt(0) != 'q') {
					System.out.print("Press Enter to continue.");
					scnr.nextLine();
					System.out.println();
				}

			} else {
				System.out.println("Unrecognized choice: " + userInput);
			}

		} while (menuChoice.charAt(0) != 'q');
	}
	
	/**
	 * (This method is responsible for adding the places from a file. It loops
	 * through the file until there are none left to read, then parses
	 * for each name and address)
	 *
	 * @param (File file) (The filename from the list of source files)
	 */
	private static void addPlacesFromFile(File file) throws FileNotFoundException, NoSuchElementException {

		//variable for the next line in the file
		String test = "";
		//scanner object for the reading through the file
		Scanner scnr;
		//the name in the line of the file
		String name = "";
		//the address in the line of file
		String address = "";
		
		//add the file to the scanner
		scnr = new Scanner(file);
		test = scnr.nextLine();

		//loop until the next line has no text
		while (!test.isEmpty()) {
			//find the attributes of the place
			name = test.substring(0, test.indexOf(';'));

			address = test.substring(test.indexOf(';') + 1, test.length());
			address = address.trim();

			//add the place to list
			addPlaceToList(new Place(name, address));

			test = scnr.nextLine();

			
		}
		scnr.close();

	}
	/**
	 * (This method is responsible for displaying the list of
	 * files to the user. It loops through the arrayList of available
	 * files and prints them out.
	 * @return (ArrayList<String> availFiles) (the list of files available)
	 */	
	private static ArrayList<String> displayFiles(){
		
		System.out.println("My Places Files: ");
		
		//array list of strings for the names of the files in the project folder using the getMpFileNames() method
		ArrayList<String> availFiles=getMpFileNames();
		
		//print the names of the files by looping through them
		for(int i=0;i<availFiles.size();i++){
			System.out.println(" "+availFiles.get(i));
		}
		return availFiles;
	}

	/**
	 * (This method is responsible for looping through the files
	 * in the project folder and finds the one's which have the .mp file
	 * extension, and if so it adds it to the arraylist of strings
	 * @return (ArrayList<String> mpFiles) (the list of files which have
	 * 										the mp file extension)
	 */	
	private static ArrayList<String> getMpFileNames(){
		
		//create an arraylist of strings for the file names
		ArrayList<String> mpFiles=new ArrayList<String>();
		
		//get the file in the project folder
		File folder = new File(".");
		
		//loop through the folder and find all the files with .mp on the end and add their names to the list
		for ( File file : folder.listFiles()) {
		  if ( file.getName().endsWith( ".mp")) {
		     mpFiles.add(file.getName());
		  } 
		}

		return mpFiles;
	}
	
	/**
	 * (This method is responsible for adding a place to the arraylist of 
	 * places, includes an else condition where if the list already has
	 * places in it, it will loop through each one checking the name to see
	 * if the name already exists in it. If it does, then it will output
	 * a message saying that it is already in the list.)
	 *
	 * @param (Place place) (The place the user wishes to add to the list)
	 */
	private static void addPlaceToList(Place place) {
		
		//variable for determining if the place is already in the list
		boolean foo = false;
		
		if (!(list.size() > 0)) {
			list.add(place);
		} else {
			//loop through the list
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals(place.getName())) {
					System.out.println(place.getName() + " already in list.");
					foo = true;
					break;
				}
			}
			if (!foo) {
				list.add(place);
			}
		}
	}
	
	/**
	 * (This method is responsible for creating a file when writing to a file.
	 * It creates a file and adds the name and address seperated by a semicolon
	 * so the addplacetoList method can interact with it.)
	 *
	 * @param (String fileName) (The string name for the file)
	 */	
	private static void createFile(String fileName) throws IOException {
		
		//create a file with the given name in the project folder
		File file = new File(".\\" + fileName);
		file.createNewFile();

		//create a PrintWriter object for writing to file
		PrintWriter output = new PrintWriter(file);

		//loop through the list and add the places to the file
		for (int i = 0; i < list.size(); i++) {
			output.println(list.get(i).getName() + ";" + list.get(i).getAddress());
		}

		output.flush();
		output.close();
	}

}
