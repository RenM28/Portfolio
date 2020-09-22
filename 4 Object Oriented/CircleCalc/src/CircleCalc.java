import java.util.Random;
/**
 * This program calculates the area and circumference of a circle
 * with a random radius from 0-49
 * @author Ren Gernes
 */
public class CircleCalc {
	/** 
	 * This function computes the area of a circle
	 * @param radius The radius of the circle used in calculation
	 * @return The area of the circle
	 */
	public static double calcArea(double radius) {
		double area = Math.PI * radius * radius;
		return area;
	}
	
	/**
	 * This function computes the circumference of a circle
	 * @param radius The radius of the circle used in calculation
	 * @return The circumference of the circle
	 */
	public static double calcCircumference(double radius) {
		double circ = 2 * Math.PI * radius;
		return circ;
	}
	
	/**
	 * Main function that generates radius from 0-49
	 * and displays area and circumference to user
	 */
	public static void main(String[] args) {
		Random rnd = new Random();
		double radius = rnd.nextInt(50);
		double area = calcArea(radius);
		double circumference = calcCircumference(radius);
		System.out.printf("The area of the circle is %.2f and the circumference is %.2f.", area, circumference);
	}
}
