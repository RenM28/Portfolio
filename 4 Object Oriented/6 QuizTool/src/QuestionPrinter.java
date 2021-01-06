import java.util.ArrayList;

/**
 * This class prints each question in array in the format: answer  question.
 * @author gerne
 *
 */
public class QuestionPrinter {
	/**
	 * This function prints out every question in the array and its answer.
	 * @param questions Array of questions that contains each question in json file
	 */
	public void printQandA(ArrayList<Question> questions) {
		Question question;
		for (int i = 0; i < questions.size(); i++) { // for each question
			question = questions.get(i); // get question
			System.out.printf("%s  %s", question.getAnswer(), question.getQuestion()); // print question and answer
			System.out.println();
		}
		System.out.println();
	}
}
