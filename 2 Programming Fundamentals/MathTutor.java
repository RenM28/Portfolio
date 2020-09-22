import java.util.Scanner;
import java.util.Random;
public class MathTutor {
	
	// function prints heading to tell user what the program is
	public static void printHeading() {
		System.out.println("***********************************");
		System.out.println("       Electronic Math Tutor");
		System.out.println("***********************************");
	}
	
	// function prints menu for user to choose which type of mathematical operation
	// they'd like practice with
	public static void printMenu() {
		System.out.println("Choose the type of problem: ");
		System.out.println("1. Addition");
		System.out.println("2. Multiplication");
		System.out.println("3. Mixed");
		System.out.println("4. Quit");
		System.out.print("Enter your choice: ");
	}
	
	// inputs: scanner, random number generator, number of problems user wants as integer,
	//         max operand user wants each problem to have, and what menu item they have chosen
	// outputs: number of answers user has correctly answered
	// function takes in inputs and shows user number of problems requested using selected
	// operation up to max operand; also keeps track of number of problems user correctly
	// answers
	public static int runProblemSet(Scanner sc, Random rand, int probCount, int maxOperand, int choice) {
		int correct = 0, answer = 0, operator = 0, response = 0, num1 = 0, num2 = 0;
		String sign = "";
		for (int i = 0; i < probCount; i++) {
			num1 = rand.nextInt(maxOperand+1);
			num2 = rand.nextInt(maxOperand+1);
			if (choice == 1) {
				sign = "+";
				answer = num1 + num2;
			}else if (choice == 2) {
				sign = "*";
				answer = num1 * num2;
			}else if (choice == 3) {
				operator = rand.nextInt(2);
				if (operator == 0) {
					sign = "+";
					answer = num1 + num2;
				}else {
					sign = "*";
					answer = num1 * num2;
				}
			}
			System.out.printf("%d %s %d = ? ", num1, sign, num2);
			response = sc.nextInt();
			if (response == answer) {
				correct += 1;
				System.out.println("Correct!");
			} else {
				System.out.printf("Incorrect. The answer is %d. \n", answer);
			}
		}
		return correct;
	}
	
	// inputs: number of answers user entered correctly and number of questions user
	//         was asked
	// function takes in inputs and determines percentage of answer user got correct
	// by dividing num correct by num asked
	public static void printResults(int correct, int asked) {
		double percentage = ((double)correct / (double)asked)*100;
		System.out.printf("You answered %d of %d correctly. \n", correct, asked);
		System.out.printf("Your percentage score was %.2f. \n", percentage);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // creates new scanner object
		Random rand = new Random();			 // creates new random object
		int choice, probCount, maxOperand, correct;
		printHeading(); // shows user heading
		do {
			printMenu(); // shows user menu
			choice = sc.nextInt(); // stores choice selected
			if (choice != 4) {
				System.out.print("How many problems? ");
				probCount = sc.nextInt();
				System.out.print("Largest operand? ");
				maxOperand = sc.nextInt();
				System.out.println("");
				correct = runProblemSet(sc, rand, probCount, maxOperand, choice);
				System.out.println("");
				printResults(correct, probCount);
				System.out.println("");
			}
		} while (choice != 4); // until user quits
	}
}
