import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class chooses a user specified number of random questions and returns
 * the number of questions answered correctly.
 * @author gerne
 *
 */
public class Quizzer {
	/**
	 * This function chooses a random, user specified number of questions from an
	 * array of questions and displays them to the user.
	 * It also returns the number of correctly answered questions.
	 * @param howMany Number of questions as integer
	 * @param questions Array of questions
	 * @param sc Scanner to take in user input
	 * @return Number of correct answers an integer
	 */
	public int quizTime(int howMany, ArrayList<Question> questions, Scanner sc) {
		Random rand = new Random(); // Random object
		int index; // index of question
		int numCorrect = 0; // initializes number correct
		String ans; // initializes user answer
		Question question; // question object
		
		for (int i = 0; i < howMany; i++) { // for number of times specified
			// selects random int between 0 and number of questions
			// and indexes array list to pull question
			index = rand.nextInt(questions.size());
			question = questions.get(index);
			System.out.println(question);
			
			// user types answer choice
			System.out.print("Type the letter of your choice: ");
			ans = sc.nextLine();
			
			// if user answer gets question right, 1 is added to numCorrect and user is given
			// congratulations message
			if (ans.equalsIgnoreCase(question.getAnswer())) {
				System.out.println("Ya nailed it! Nice going genius!");
				numCorrect += 1;
			} else { // if user is incorrect, the correct answer is displayed
				System.out.printf("Sorry. The correct answer is %s.", question.getAnswer());
				System.out.println();
			}
			System.out.println();
		}
		return numCorrect;
	}
}
