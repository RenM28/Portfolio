import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class reads a json file of questions and returns them as an array list.
 * @author gerne
 *
 */
public class QuestionReader {
	/**
	 * This function reads in a file name as a String and returns an array list
	 * of questions.
	 * @param fname File name as a String
	 * @return Array list of questions
	 */
	public ArrayList<Question> readFromJSON(String fname) {
		try { // if file is valid
			ArrayList<Question> questions = new ArrayList<Question>();
			FileReader reader = new FileReader(new File(fname));
			// returns outer JSON object
			JSONParser parser = new JSONParser();
			// outer shell
			JSONObject all = (JSONObject)parser.parse(reader);
			// grabs array from all
			JSONArray arr = (JSONArray)all.get("questions");
			Iterator itr = arr.iterator(); // helps step through entries
			JSONObject questionObject; // object to grab
			String question, a, b, c, d, answer; // field values
			while (itr.hasNext()) {
				// next entry in itr
				questionObject = (JSONObject)itr.next();
				// get values of fields from object
				question = questionObject.get("question").toString();
				a = questionObject.get("a").toString();
				b = questionObject.get("b").toString();
				c = questionObject.get("c").toString();
				d = questionObject.get("d").toString();
				answer = questionObject.get("answer").toString();
				// add brand new question to list
				questions.add(new Question(question, a, b, c, d, answer));
			}
			reader.close();
			return questions; // returns question array
		} catch (Exception ex) {
			return null; // if file not valid
		}
	}
}
