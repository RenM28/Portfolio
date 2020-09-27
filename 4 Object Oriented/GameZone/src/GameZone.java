import java.util.Random;
import java.util.Scanner;
/**
 * This program allows the user to select from 2 game options: 21 or rock paper scissors.
 * They may play each game as many times as they like, unless they choose to quit.
 * Note: the 21 game is simulated assuming there is an infinite deck of cards.
 * @author gerne
 *
 */
public class GameZone {
	/**
	 * This function prints a welcome banner.
	 */
	public static void printWelcome() {
		System.out.println("****************************************");
		System.out.println("*       Welcome to the Game Zone       *");
		System.out.println("****************************************");
	}
	
	/**
	 * This function presents a menu with 3 choices to the user and returns the integer
	 * of their choice.
	 * Note: the user will repeatedly be presented with the menu of options until they
	 * enter a character within the range 1-3.
	 * @param sc The scanner object used to read user's choice
	 * @return The integer user chooses 
	 */
	public static int printMenuAndGetChoice(Scanner sc) {
		int choice;
		do {
			System.out.println("Pick your poison by entering the number of your choice: ");
			System.out.println("1. Play 21");
			System.out.println("2. Play Rock Paper Scissors");
			System.out.println("3. Quit");
			try {
				choice = sc.nextInt();
				if (choice < 1 || choice > 3) {
					System.out.println("Please enter 1-3.");
				}
			} catch (Exception ex) {
				System.out.println("Please enter a number.");
				sc.nextLine();
				choice = 0;
			} 
		} while (choice < 1 || choice > 3);
		return choice;
	}
	
	/**
	 * This function simulates a game of 21 with an infinite deck.
	 * The user can choose to draw cards as long as their total does not meet or exceed 21.
	 * If their total exceeds 21, they lose.
	 * If their total reaches 21 exactly, they win.
	 * If their total is less than 21 and they choose to stop drawing cards, they will win.
	 * if their total is greater than the computer, lose if their total is less than the
	 * computer, or tie otherwise.
	 * @param sc The scanner object used to read user's choice
	 * @param rnd The random object used to select card totals for user and computer
	 */
	public static void play21 (Scanner sc, Random rnd) {
		String choice;
		int playerDraw,  compDraw, totalDraw = 0;
		
		do {
			compDraw = rnd.nextInt(8) + 13;
			playerDraw = rnd.nextInt(11) + 1;
			totalDraw += playerDraw;
			System.out.printf("You drew %d.\n", playerDraw);
			System.out.printf("Your current total is %d.\n", totalDraw);
			if (totalDraw > 21) {
				System.out.println("You lose! The luck of the Irish is not on your side.");
				choice = "n";
			} else if (totalDraw == 21){
				System.out.println("You win, ya lucky duck!");
				choice = "n";
			} else {
				System.out.println("Do you want to draw another card (y or n)? ");
				choice = sc.next().toLowerCase().trim();
			}
		} while (choice.equals("y"));
		
		if (totalDraw < 21) {
			System.out.printf("The computer drew %d.\n", compDraw);
			if (compDraw > totalDraw) {
				System.out.println("Oof, ya lose mate. Better luck next time!");
			} else if (compDraw < totalDraw){
				System.out.println("You win! The computer was no match for your skills.");
			} else {
				System.out.println("You tied, amigo!");
			}
		}
		
	}
	
	/**
	 * This function simulates a classic game of rock, paper, scissors.
	 * The computer and user will randomly be assigned rock, paper, or scissors.
	 * The winner will then be determined based on the usual rules.
	 * @param rnd The random object used to select rock, paper, or scissors
	 */
	public static void playRockPaperScissors(Random rnd) {
		int player, comp;
		String [] choices = {"Rock", "Paper", "Scissors"};
		player = rnd.nextInt(3);
		comp = rnd.nextInt(3);
		System.out.printf("You played %s, and the computer played %s.\n", choices[player], choices[comp]);
		if (player == comp) {
			System.out.println("'Tis a tie.");
		} else if (player == 0 && comp == 2 || player == 1 && comp == 0 || player == 2 && comp == 1){
			System.out.println("We have a winner!");
		} else {
			System.out.println("I'm afraid you lost.");
		}
	}
	
	/**
	 * Main function that executes whichever game the user chooses continuously until
	 * the user enters a 3 to quit.
	 */
	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		int choice;
		printWelcome();
		do {
			choice = printMenuAndGetChoice(sc);
			if (choice == 1) {
				play21(sc, rnd);
			} else if (choice == 2) {
				playRockPaperScissors(rnd);
			} else {
				System.out.println("Thanks for playing!");
			}
		} while (choice != 3);
	}
}
