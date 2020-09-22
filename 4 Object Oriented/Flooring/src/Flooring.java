/*
 * This program calculates the amount of flooring needed for a specific shaped home 
 * as well as the cost of said flooring
 */
public class Flooring {
	public static int conversion(int sqfeet) {
		int sqInches = sqfeet*144;
		return sqInches;
	}
	
	public static void main(String[] args) {
		int dimensionsFeet = (20*13)+((25-13)*10)+((25-13)*5);
		int dimensionsInches = conversion(dimensionsFeet);
		double packageCoverage = 30*6*6;
		double packagesNeeded = Math.ceil((dimensionsInches / packageCoverage) * 1.2);
		double costOfPackages = packagesNeeded * 24.99;
		System.out.printf("You will need to pay $%.2f for %.0f laminate boards.", costOfPackages, packagesNeeded);
	}
}
