import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 * This program allows the user to view the amount of deaths from Covid-19 over a period of 240 days for
 * all countries specified in the form of a graph. 
 * They may request cumulative or daily death plots.
 * Users can select countries and plot types until they choose to quit.
 * @author gerne
 *
 */
public class CovidPlotter {
	
	/**
	 * This function prints a welcome banner for the user
	 */
	public static void printWelcome() {
		System.out.println("**************************************************************");
		System.out.println("***         INTERNATIONAL COVID-19 MORTALITY RATES         ***");
		System.out.println("**************************************************************");
	}
	
	/**
	 * This function creates a linked hash map whose keys are the names of countries and values are arrays
	 * of number of cumulative deaths from the text file that the user enters.
	 * @param fsc The scanner object used to read user's choice of file
	 * @return The linked hash map with keys as names of countries and values as arrays of death counts
	 */
	public static LinkedHashMap<String, double[]> readData(Scanner fsc){
		LinkedHashMap<String, double[]> result = new LinkedHashMap<String, double[]>();
		fsc.nextLine(); // skips title line
		String name, line; // sets up name of country and line being read
		String[] parts; 
		double[] deaths; // sets up array of cumulative death counts
		// for each line in file
		while (fsc.hasNextLine()) {
			line = fsc.nextLine();
			parts = line.split("\t"); // split line by tabs
			name = parts[0]; // name of country
			deaths = new double[parts.length-1]; 
			// fill array of cumulative death count
			for (int i = 1; i < parts.length; i++) {
				deaths[i-1] = Double.parseDouble(parts[i]);
			}
			// add to hash map w/ name as key and cumulative death counts as values
			result.put(name, deaths);
		}
		return result;
	}
	
	/**
	 * This function creates an array of day numbers that matches the number of entries in the cumulative 
	 * deaths array. 
	 * @param howMany The number of entries in the array as an integer
	 * @return The array filled out with day numbers
	 */
	public static double[] getDays(int howMany) {
		double[] result = new double[howMany];
		// fill array of day numbers
		for (int i = 0; i < howMany; i++) {
			result[i] = i;
		}
		return result;
	}
	
	/**
	 * This function creates the plot environment that will be displayed to the user. The plot title will
	 * vary based on user choice (cumulative vs. daily death counts).
	 * @param plot The plot object where plot will be displayed
	 * @param choice The user choice as a String indicating which plot type they'd like to see (C or D)
	 */
	public static void setUpAndShowPlot(Plot2DPanel plot, String choice) {
		JFrame frm = new JFrame(); // initializes frame
		frm.setBounds(100,100,500,500); // sets screen bounds
		
		// if user wants cumulative plot
		if (choice.equalsIgnoreCase("C")) {
			frm.setTitle("Cumulative Deaths");
			BaseLabel title = new BaseLabel("Cumulative Deaths", Color.RED, 0.5, 1.1);
			plot.addPlotable(title);
		// if user wants daily plot
		} else {
			frm.setTitle("Daily Deaths");
			BaseLabel title = new BaseLabel("Daily Deaths", Color.RED, 0.5, 1.1);
			plot.addPlotable(title);
		}
		
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // removes plot from memory on close
		Container c = frm.getContentPane();
		c.setLayout(new BorderLayout());
		plot.addLegend("SOUTH"); // adds legend
		plot.setAxisLabels("Day", "Deaths"); // adds axis labels
		c.add(plot, BorderLayout.CENTER);
		frm.setVisible(true); // shows plot to user
	}
	
	/**
	 * Main function that will display the cumulative or daily death plot of 
	 * whichever country/countries the user enters. The user will continue to be prompted
	 * to choose their countries of interest and plot types until they choose to quit.
	 */
	public static void main(String[] args) throws Exception {
		LinkedHashMap<String, double[]> countryLog; // sets up hash map to log countries + deaths
		String inputtedCountries; // sets up user choice of countries
		String choice; // sets up user choice of cumulative/daily death plot
		String[] countries; // initializes array of countries
		Scanner sc = new Scanner(System.in);
		double[] deathCount; // sets up array for cumulative death counts
		
		printWelcome(); // prints banner to welcome user
		System.out.print("Enter the name of the file of scores: ");
		String fname = sc.nextLine();
		
		// if file name is valid program will read file
		try {
			Scanner fsc = new Scanner(new File(fname));
			countryLog = readData(fsc); // set up linked hash map 
		} catch (Exception ex) {
			countryLog = null;
		}
		
		// if file name is not valid
		if (countryLog == null) {
			System.out.println("The file could not be read.");
		// if file name is valid
		} else {
			do {
				// user enters which countries they want to see death counts for
				System.out.println("Enter countries to plot, separated by commas (or quit to end): ");
				inputtedCountries = sc.nextLine();
				
				// if user does not want to quit
				if (!inputtedCountries.equalsIgnoreCase("quit")) {
					// user will be prompted to type choice until valid letter (C or D) is entered 
					do {
						System.out.println("[D]aily or [C]umulative?");
						choice = sc.nextLine();
					} while (!(choice.equalsIgnoreCase("C") || choice.equalsIgnoreCase("D")));
					
					Plot2DPanel plot = new Plot2DPanel(); // sets up plot environment
					countries = inputtedCountries.split(","); // splits entered countries by comma
					
					boolean showPlot = true; // value used to determine if plot should be shown to user
					// for each country user enters
					for (String country: countries) {
						country = country.trim(); // strip blank space
						// if country not in text file
						if (!countryLog.containsKey(country)) {
							// user told which country is invalid and plot is not displayed
							System.out.printf("%s is not in the data set. \n", country);
							showPlot = false;
						// if country in text file
						} else {
							deathCount = countryLog.get(country); // array of cumulative deaths
							if (choice.equalsIgnoreCase("D")) {
								// sets up array for daily death counts
								double[] dailyDeathCount = new double[deathCount.length];
								// fills out daily array
								for (int i = 0; i < deathCount.length; i++) {
									if (i == 0) {
										dailyDeathCount[i] = deathCount[i];
									} else {
										dailyDeathCount[i] = deathCount[i] - deathCount[i-1];
									}
								}
								// plots daily deaths
								plot.addLinePlot(country, getDays(deathCount.length), dailyDeathCount);
							} else {
								// plots cumulative deaths
								plot.addLinePlot(country, getDays(deathCount.length), deathCount);
							}
						}
					}
					// if all country names entered are valid, plot will be displayed to user
					if (showPlot) {
						setUpAndShowPlot(plot, choice);
					}
				}
			} while (!inputtedCountries.equalsIgnoreCase("quit")); // program runs until user quits
			System.out.println("Do your task, wear a mask! "); // parting words to help stop covid
			}
		}
}

