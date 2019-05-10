import javax.swing.JFrame;  
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.BoxLayout;
import java.awt.Panel;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JSplitPane; 
public class Output extends JFrame{



	 
	/**
	 * This constructor takes in information to make the graph
	 * @param appTitle
	 * including, the title, axis labels and dataset
	 */
	
	public Output( String applicationTitle , String chartTitle ) {
	      super( applicationTitle );  
	      
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Category",            
	         "Score",            
	         createDataset(),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	    //Initializes chart
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel );
	
	} // closes constructor  
	
	


	/**
	 * The following method calls:
	 * 		Class: Customer; to retrieve information 
	 * about the customers age 
	 * 		Class: MonteCarloAnalysis; to get information from the results Hashmap
	 * @return
	 */

	private CategoryDataset createDataset() {  


		

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		Customer newCustomer = new Customer();//create a new customer
		//Customer newCustomer = newForm.createCustomer();
		//newCustomer.getCustomerInfo();   //Call method to get all our relevant info on the customer


		//Run a monte carlo analysis based on the information our new customer has provided
		MonteCarloAnalysis newMC = new MonteCarloAnalysis(.035, newCustomer);
		HashMap<Integer,Double> results = new HashMap<>();   //stores our monte carlo results
		results = newMC.monteCarloSimulation(100); //run the monte carlo simulation n number of times

		// First is the hashmap key to get the savings amount for a certain age, then is a title, last is the bar label (Age)
		dataset.addValue(results.get(newCustomer.getCurrentAge()), "Current Savings", ""+ newCustomer.getCurrentAge());
		dataset.addValue(results.get(newCustomer.getCurrentAge()+5), "Projected Savings", ""+ (newCustomer.getCurrentAge()+5));
		dataset.addValue(results.get(newCustomer.getRetirementAge()-2), "Projected Savings", ""+ (newCustomer.getRetirementAge()-2));
		dataset.addValue(results.get(newCustomer.getRetirementAge()), "Savings at Retirement Age", ""+ newCustomer.getRetirementAge());
		dataset.addValue(results.get(newCustomer.getRetirementAge()+5), "Savings During Retirement", ""+ (newCustomer.getRetirementAge()+5));
		dataset.addValue(results.get(newCustomer.getAgeOfDeath()-2), "Savings During Retirement", ""+ (newCustomer.getAgeOfDeath()-2));
		dataset.addValue(results.get(newCustomer.getAgeOfDeath()), "Savings at End of Retirement", ""+ newCustomer.getAgeOfDeath()); 
	
		

		// The amount of savings at the different points on the bar chart are saved in a dataset
		return dataset;  
}

}  

	


