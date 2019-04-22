import java.util.*;
import java.io.*;



/**
 * This class will take in a Users preferred Risk level and 
 * pair it to the appropriate row of the CSV file that is stored in an
 * ArrayList
 */
public class RiskLevel {

// creates a string Array List 
	public static ArrayList <String> readAsList (String filename){
		ArrayList<String> result = new ArrayList <String>(); 
		try {
			Scanner sc = new Scanner (new File(filename));
			sc.nextLine(); 
			while (sc.hasNextLine()) {
				result.add(sc.nextLine());
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
		
	}
// reads in the Arraylist of Strings
	public static String [] readAsArray(String filename) {
		String[] result = new String [100]; 
		
		return result; 
	}
	
// saves the ArrayList
	 public static ArrayList <String> readAsList(String filename) {
		 ArrayList<String> result = new ArrayList<String>(); 
		 
		 return result; 
	 }
	 
// this method will look through the Arraylist for the Customers Risk level 
	 public static void CustomerRiskLevel() {
		 
	 }
}
