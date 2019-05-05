import java.util.*;

public class Runner {
/**
 * This class will run the program
 * @param args
 */
	public static void main(String[] args) {
		Customer newCustomer = new Customer();  //create a new customer
		newCustomer.getCustomerInfo();   //Call method to get all our relevant info on the customer
		
		//Run a monte carlo analysis based on the information our new customer has provided
		MonteCarloAnalysis newMC = new MonteCarloAnalysis(.035, newCustomer);
		HashMap<Integer,Double> results = new HashMap<>();   //stores our monte carlo results
		results = newMC.monteCarloSimulation(100); //run the monte carlo simulation n number of times
		
		//I just have this for loop in right now to make sure it's working
		for(int i = newCustomer.getCurrentAge(); i <= newCustomer.getAgeOfDeath(); i++) {
			System.out.print("Age: " + i + "; ");
			System.out.printf("%,.2f", results.get(i));
			System.out.println();
		}
		System.out.println(newMC.getProbabilityOfSuccess());

	}
	
}