import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;


public class Runner extends JFrame {

	//private JPanel contentPane;

	
	public static void main(String[] args) throws Exception{  
		
		//This is the code i added to run the input form
		//it runs the form, and once click is submitted it will
		//create a new customer with the information it recieved.
		InputForm newForm = new InputForm();   //create newform
		newForm.displayGUI();   //display form
		
		//while loop to wait until submit button is clicked
		while(newForm.getIsSubmitted() == false) { 
			try {
				Thread.sleep(500);  //sleeps program so loop won't kill memory
			} catch (InterruptedException e) { 
				e.printStackTrace(); 
			}
		}
		Customer newCustomer = newForm.createCustomer();  //create new customer	 
		
		//Customer newCustomer = new Customer();  //create a new customer
		newCustomer.getCustomerInfo();   //Call method to get all our relevant info on the customer
			
		// runs the Customer Retirement Details Class     
	  	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomersRetirementDetails frame = new CustomersRetirementDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			 Output chart = new Output("Retirement Planning", 
			         "Retirement Summary");
			      chart.pack( );        
			      RefineryUtilities.centerFrameOnScreen( chart );        
			      chart.setVisible( true ); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
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
	
	

	

	
	//************* to add a panel to runner***********************
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Runner frame = new Runner();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public Runner() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//	}
//
//}
