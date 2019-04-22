import java.util.*;
import java.io.*;


/**
 * This class will take in a Users preferred Risk level and 
 * pair it to the appropriate row of the CSV file that is stored in an
 * ArrayList
 */
public class RiskLevel {
	private String filename; 
	

	private static ArrayList<String> levelValues; 
	/**
	 * This constructor
	 * @param file
	 * reads
	 */
	public RiskLevel(String file) {
		
		filename = file; 
		levelValues = new ArrayList <String>();
		readFile(); 
		
	}
		
	/** 
	 * This will read the file and store it to 
	 * an ArrayList
	 * @return 
	 */
	
	//private void readFile() {
	private ArrayList<String> readFile() {	
		try {
			
			File inputFile = new File (filename);
			Scanner in = new Scanner(inputFile);
			
			while (in.hasNextLine()) {
				
				String line = in.nextLine();
				levelValues.add(line);
				
			}
			
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return levelValues; 
	}

	/**
	 * This method will get the Risk level provided by the customer and 
	 * pull the appropriate line of the arrayList 		
	 */
	public static void customerRiskLevelDef() {
		// getRiskLevel
		//ArrayList<String> levelValues
	}
	
	public static void main(String[] args) {
		System.out.println(levelValues);
	}
}
