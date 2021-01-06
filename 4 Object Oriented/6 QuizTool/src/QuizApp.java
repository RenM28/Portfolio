import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the main functions of the program. Users are given a choice to either take a quiz where
 * they can specify the number of questions, see all the possible questions with their correct answers, or exit
 * the program.
 * @author gerne
 *
 */
public class QuizApp {
	/**
	 * This function prints a welcome banner for the user.
	 */
	public static void printBanner() {
		System.out.println("*     Welcome to the quiz of the century!    *");
		System.out.println("**********************************************");
		System.out.println("*      OOP Theory and Concept Questions      *");
		System.out.println("**********************************************");
		System.out.println("*    Warning: Not for the faint of heart     *");
		System.out.println("**********************************************");
		System.out.println();
	}
	
	/**
	 * This function displays a menu of choices to the user.
	 * It also provides error checking so that the user may only
	 * enter 1-3 as valid choices.
	 * @param sc Scanner to take in user input
	 * @return choice entered by user as integer
	 */
	public static int showMenuAndGetChoice(Scanner sc) {
		int choice; // initializes choice
		do {
			// displays menu items
			System.out.println("Here are your options: ");
			System.out.println("1. Take a quiz");
			System.out.println("2. See all questions and answers");
			System.out.println("3. Exit");
			System.out.print("Enter you choice: ");
			try {
				// registers user choice
				choice = sc.nextInt();
				// clears end of line marker
				sc.nextLine();
				System.out.println();
			} catch (Exception ex) { // if user does not enter integer
				System.out.println();
				System.out.println("Please enter a valid choice.");
				System.out.println();
				sc.nextLine();
				choice = 0;
			}
		} while (choice < 1 || choice > 3); // will keep displaying menu until valid choice selected
		return choice;
	}
	
	/**
	 * Main function that provides functionality for each of the menu options.
	 * 1 - Asks user for number of questions desired and displays that number of questions.
	 * The user provides their answer for each one and is told what their overall score is.
	 * 2 - Prints out all the questions in the json file with their respective answers.
	 * 3 - Exits the program.
	 */
	public static void main(String[] args) {
		printBanner(); // prints menu
		
		// variable and object declarations
		int choice, numQuestions, numCorrect;
		String path; // file path
		Question question;
		ArrayList<Question> questions = new ArrayList<Question>(); // array of questions
		QuestionReader qr = new QuestionReader();
		QuestionPrinter qp = new QuestionPrinter();
		Quizzer qz = new Quizzer();
		Scanner sc = new Scanner(System.in);
		
		try { // if file is valid
		System.out.print("Enter name of question file: "); // enter file
		path = sc.nextLine();
		System.out.println();
		questions = qr.readFromJSON(path); // file is read in to set up questions array
		
		do { // while user does not wish to exit
			choice = showMenuAndGetChoice(sc); // user is shown menu
			if (choice == 1) { // take quiz
				System.out.print("How many questions would you like? ");
				numQuestions = sc.nextInt();
				sc.nextLine();
				System.out.println();
				numCorrect = qz.quizTime(numQuestions, questions, sc);
			// results are displayed
			System.out.printf("You answered %s out of %s questions correct.", numCorrect, numQuestions);
			System.out.println();
			System.out.println();
			} else if (choice == 2) { // see all q's and a's
				System.out.println("Here are the answers: ");
				qp.printQandA(questions); // print questions + answers
			} 
		} while (choice != 3); // exit program
	
		} catch (Exception ex) { // if file not valid
			System.out.println("The file you entered does not exist.");
		}
		// Sign off
		System.out.println("******************************************************");
		System.out.println("*   Have a fantastic break and enjoy the holidays!   *");
		System.out.println("******************************************************");
	}
}
