/**
 * Model class that holds data about each question
 * the user can be asked.
 * @author gerne
 *
 */
public class Question {
	// variables that make up each javadoc object
	private String question;
	private String answer;
	private String a;
	private String b;
	private String c;
	private String d;
	
	// get functions that allow outside access to variables
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	public String getA() {
		return a;
	}
	public String getB() {
		return b;
	}
	public String getC() {
		return c;
	}
	public String getD() {
		return d;
	}
	
	// set functions 
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setA(String a) {
		this.a = a;
	}
	public void setB(String b) {
		this.b = b;
	}
	public void setC(String c) {
		this.c = c;
	}
	public void setD(String d) {
		this.d = d;
	}
	
	/**
	 * Default constructor that contains default values for each variable.
	 */
	public Question() {
		question = "What is love?";
		a = "";
		b = "";
		c = "";
		d = "";
		answer = "Baby don't hurt me.";
	}
	
	/**
	 * Non-defualt constructor with inputs for each variable.
	 * @param question Question to be answered as String
	 * @param a Option a as String
	 * @param b Option b as String
	 * @param c Option c as String
	 * @param d Option d as String
	 * @param answer Answer to question as String
	 */
	public Question(String question, String a, String b, String c, String d, String answer) {
		setQuestion(question);
		setA(a);
		setB(b);
		setC(c);
		setD(d);
		setAnswer(answer);
	}
	
	// overrides toString function to print each object as 
	// a string containing the question and answer
	@Override
	public String toString() {
		return String.format("%s\na. %s\nb. %s\nc. %s\nd. %s", question, a, b, c, d);
	}
}
