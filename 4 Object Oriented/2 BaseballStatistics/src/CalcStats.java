import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program allows the user to view the stats of baseball teams in each specified league and/or view the overall standings in the 
 * American and national leagues as many times as they would like, unless they choose to quit.
 * For individual leagues, users will see stats related to wins, losses, win percentage, and games behind the best team in the league.
 * For overall standing in all leagues, users will see win and loss records of each team, which are listed in descending order by win percentage.
 * @author gerne
 *
 */
public class CalcStats {
	
	/**
	 * This function prints a welcome banner with a description of the program.
	 */
	public static void printWelcome() {
		System.out.println("*************************************************");
		System.out.println("***         Baseball Standings Analyzer       ***");
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("This program reads a tab delimited file of current\nbaseball standings. It will either display stats"
				+ "\nby league, or by overall standings in the American\nand national leagues depending on user choice.");
		System.out.println();
	}
	
	/**
	 * This function presents a menu with 8 choices to the user and returns the integer of their choice.
	 * Note: the user will repeatedly be presented with the menu of options until they enter a integer within the range 1-8.
	 * @param sc The scanner object used to read user's choice
	 * @return The integer user chooses
	 */
	public static int showMenuAndGetChoice(Scanner sc) {
		int choice;
		do {
			System.out.println("Which standings would you like to see?");
			System.out.println("1. AL East");
			System.out.println("2. AL Central");
			System.out.println("3. AL West");
			System.out.println("4. NL East");
			System.out.println("5. NL Central");
			System.out.println("6. NL West");
			System.out.println("7. Overall");
			System.out.println("8. Exit");
			System.out.print("Enter the number of your choice: ");
			try {
				choice = sc.nextInt();
				System.out.println();
				if (choice < 1 || choice > 8) {
					System.out.println("Please enter 1-8 :D");
					System.out.println();
				}
			} catch (Exception ex) {
				System.out.println();
				System.out.println("Please enter a number :)");
				System.out.println();
				sc.nextLine();
				choice = 0;
			}
		} while (choice < 1 || choice > 8);
		return choice;
	}
	
	/**
	 * This function calculates the win percentage of a single team.
	 * @param line The string consisting of name of team, number of wins, and number of losses separated by tab
	 * @return The percentage of games won as a double
	 */
	public static double calcWinPercentage(String line) {
		String[] parts = line.split("\t");
		double winPercent = (double)Integer.parseInt(parts[1]) / (Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]));
		return winPercent;
	}
	
	/**
	 * This function calculates how many games the given team is behind the best team in the league. Essentially, it measures the average 
	 * difference between wins and losses of the given team vs. the first (best) team.
	 * @param line The String consisting of name of team, number of wins, and number of losses separated by tab
	 * @param bestWin The integer value of the team who won the most games
	 * @param bestLoss The integer value of the team who lost the least games
	 * @return The amount of games behind as a double
	 */
	public static double calcGamesBehind(String line, int bestWin, int bestLoss) {
		String[] parts = line.split("\t");
		double gamesBehind = ((bestWin - Integer.parseInt(parts[1])) + (Integer.parseInt(parts[2]) - bestLoss)) / 2.0;
		return gamesBehind;
	}
	
	/**
	 * This function prints the stats of a single league in a nicely ordered fashion with a header on top. The stats for each team include:
	 * number of wins, number of losses, win percentage, and amount of games behind.
	 * @param teams The array list of teams in chosen league
	 */
	public static void printStats(ArrayList<String> teams) {
		String[] parts;
		double winPercent, gamesBehind;
		int bestWinRec = 0, bestLossRec = 0;
		System.out.printf("%-15s%6s%12s%12s%12s\n", "Team", "Wins", "Losses", "Pct.", "Behind");
		System.out.println("-----------------------------------------------------------");
		for (int i = 0; i < teams.size(); i++) {
			parts = teams.get(i).split("\t");
			if (i == 0) {
				bestWinRec = Integer.parseInt(parts[1]);
				bestLossRec = Integer.parseInt(parts[2]);
				winPercent = calcWinPercentage(teams.get(i));
				System.out.printf("%-15s%6s%12s%12.3f\n", parts[0], parts[1], parts[2], winPercent);
			} else {
				winPercent = calcWinPercentage(teams.get(i));
				gamesBehind = calcGamesBehind(teams.get(i), bestWinRec, bestLossRec);
				System.out.printf("%-15s%6s%12s%12.3f%12.1f\n", parts[0], parts[1], parts[2], winPercent, gamesBehind);
			}
		}
		System.out.println();
	}
	
	/**
	 * This function compares the win percentage of each team in the text file and orders teams from greatest to least win percentage.
	 * @param allTeams The array list of all teams in text file
	 * @param line The string consisting of name of team, number of wins, and number of losses separated by tab
	 */
	public static void insertByWinPercentage(ArrayList<String> allTeams, String line) {
		double currWinPercent = calcWinPercentage(line);
		double prevWinPercent;
		int pos = -1;
		for (int i = 0; i < allTeams.size(); i++) {
			prevWinPercent = calcWinPercentage(allTeams.get(i));
			if (currWinPercent > prevWinPercent) {
				pos = i;
				break;
			}
		}
		
		if (pos == -1) {
			allTeams.add(line);
		} else {
			allTeams.add(pos, line);
		}
	}
	
	/**
	 * Main function that executes whichever option the user chooses (user can choose to see stats of teams within each league or overall standings
	 * in American and national leagues) continuously until user enters an 8 to quit.
	 * This function also sets up arrays of teams by league, as well as an array of all teams in text file after reading said file.
	 */
	public static void main(String[] args) {
		printWelcome();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the file of scores: ");
		String fname = sc.nextLine();
		System.out.println();
		ArrayList<String> alEast = new ArrayList<String>();
		ArrayList<String> alCentral = new ArrayList<String>();
		ArrayList<String> alWest = new ArrayList<String>();
		ArrayList<String> nlEast = new ArrayList<String>();
		ArrayList<String> nlCentral = new ArrayList<String>();
		ArrayList<String> nlWest = new ArrayList<String>();
		ArrayList<String> allTeams = new ArrayList<String>();
		ArrayList<String> target = null;
		String line, league;
		String[] parts;
		boolean proceed;
		int choice;
		try {
			Scanner fsc = new Scanner(new File(fname));
			while (fsc.hasNextLine()) {
				line = fsc.nextLine();
				parts = line.split("\t");
				if (parts[0].equalsIgnoreCase("League")) {
					league = parts[1].toUpperCase();
					if (league.equalsIgnoreCase("AL East")) {
						target = alEast;
					} else if (league.equalsIgnoreCase("AL Central")) {
						target = alCentral;
					} else if (league.equalsIgnoreCase("AL West")) {
						target = alWest;
					} else if (league.equalsIgnoreCase("NL East")) {
						target = nlEast;
					} else if (league.equalsIgnoreCase("NL Central")) {
						target = nlCentral;
					} else if (league.equalsIgnoreCase("NL West")) {
						target = nlWest;
					}
				} else {
					target.add(line);
					insertByWinPercentage(allTeams, line);
				}
			}
			fsc.close();
			proceed = true;
		} catch (Exception ex) {
			System.out.println("The file could not be found or read.");
			proceed = false;
		}
		
		if (proceed) {
			do {
				choice = showMenuAndGetChoice(sc);
				if (choice == 1) {
					printStats(alEast);
				} else if (choice == 2) {
					printStats(alCentral);
				} else if (choice == 3) {
					printStats(alWest);
				} else if (choice == 4) {
					printStats(nlEast);
				} else if (choice == 5) {
					printStats(nlCentral);
				} else if (choice == 6) {
					printStats(nlWest);
				} else if (choice == 7) {
					System.out.printf("%-15s%6s%12s", "Team", "Wins", "Losses\n");
					System.out.println("-----------------------------------");
					for (String team: allTeams) {
						parts = team.split("\t");
						System.out.printf("%-15s%6s%12s\n", parts[0], parts[1], parts[2]);
					}
					System.out.println();
				}
			} while (choice != 8);
		}
		
		
	}
}
