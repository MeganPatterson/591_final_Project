import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.SystemColor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class CustomersRetirementDetails extends JFrame {

	private JPanel contentPane;
	private JTextField txtHello;
	private JTextField txtYourSavingsAt;
	private JTextField txtYourSavingsAt_1;
	private JTextField txtThenYouWill;
	private JTextField txtIfYouRetire;
	private JTextField txtIfYouStick;
	private JTextField txtCongratulations;
	private JTextField txtItIsRecommended;
	private JTextField txtCongratulations_1;
	private JTextField txtYourCurrentPlan;
	private JTextField txtYouShouldSpeak;

	/**
	 * Launch the application.
	 */
//	public static void main1(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CustomersRetirementDetails frame = new CustomersRetirementDetails();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CustomersRetirementDetails() {

		Customer newCustomer = new Customer();  //create a new customer
		newCustomer.getCustomerInfo();   //Call method to get all our relevant info on the customer




		//Run a monte carlo analysis based on the information our new customer has provided
		MonteCarloAnalysis newMC = new MonteCarloAnalysis(.035, newCustomer);
		HashMap<Integer,Double> results = new HashMap<>();   //stores our monte carlo results
		results = newMC.monteCarloSimulation(100); //run the monte carlo simulation n number of times

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1022, 996);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		// This text field greets the customer with their name
		txtHello = new JTextField();
		txtHello.setBackground(new Color(192, 192, 192));
		txtHello.setForeground(new Color(0, 0, 255));
		txtHello.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtHello.setText("Hello "+ newCustomer.getName()+"!");
		txtHello.setBounds(15, 37, 331, 45);
		contentPane.add(txtHello);
		txtHello.setColumns(10);

		// this text field reminds them of the age they sais they wanted to retire at
		txtIfYouRetire = new JTextField();
		txtIfYouRetire.setText("If you retire at the age of " + (newCustomer.getRetirementAge()));
		txtIfYouRetire.setForeground(new Color(0, 102, 153));
		txtIfYouRetire.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIfYouRetire.setColumns(10);
		txtIfYouRetire.setBackground(new Color(192, 192, 192));
		txtIfYouRetire.setBounds(25, 98, 753, 31);
		contentPane.add(txtIfYouRetire);

		// this text field informs them about how much money they will have when they start retirement
		txtYourSavingsAt = new JTextField();
		txtYourSavingsAt.setBackground(new Color(192, 192, 192));
		txtYourSavingsAt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtYourSavingsAt.setForeground(new Color(0, 102, 153));
		txtYourSavingsAt.setText("When you begin your retirement your savings will be: $ " + (results.get(newCustomer.getRetirementAge()))) ;
		txtYourSavingsAt.setBounds(25, 133, 753, 39);
		contentPane.add(txtYourSavingsAt);
		txtYourSavingsAt.setColumns(10);

		// this text field tells them how much they reported they would spend annually in retirement
		txtIfYouStick = new JTextField();
		txtIfYouStick.setText("If you stick to an annual speding of $" + newCustomer.getRetirementExpenses() +   " in retirement,");
		txtIfYouStick.setForeground(new Color(0, 0, 139));
		txtIfYouStick.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIfYouStick.setColumns(10);
		txtIfYouStick.setBackground(new Color(192, 192, 192));
		txtIfYouStick.setBounds(25, 188, 753, 31);
		contentPane.add(txtIfYouStick);

		txtThenYouWill = new JTextField();
		txtThenYouWill.setText("then you will have $" + (results.get(newCustomer.getAgeOfDeath()))  
				+  " savings at the end of your retirement");
		txtThenYouWill.setForeground(new Color(0, 0, 139));
		txtThenYouWill.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtThenYouWill.setColumns(10);
		txtThenYouWill.setBackground(new Color(192, 192, 192));
		txtThenYouWill.setBounds(25, 235, 753, 36);
		contentPane.add(txtThenYouWill);


		if (results.get(newCustomer.getAgeOfDeath())> 0){

			txtYourSavingsAt_1 = new JTextField();
			txtYourSavingsAt_1.setBackground(new Color(192, 192, 192));
			txtYourSavingsAt_1.setText("Your current retirement plan is working.");
			txtYourSavingsAt_1.setForeground(new Color(0, 100, 0));
			txtYourSavingsAt_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtYourSavingsAt_1.setColumns(10);
			txtYourSavingsAt_1.setBounds(25, 302, 562, 55);
			contentPane.add(txtYourSavingsAt_1);

			txtCongratulations_1 = new JTextField();
			txtCongratulations_1.setBackground(new Color(192, 192, 192));
			txtCongratulations_1.setForeground(new Color(0, 128, 0));
			txtCongratulations_1.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 48));
			txtCongratulations_1.setText("Congratulations!!");
			txtCongratulations_1.setBounds(172, 363, 398, 71);
			contentPane.add(txtCongratulations_1);
			txtCongratulations_1.setColumns(10);
		}
		else {

			txtYourCurrentPlan = new JTextField();
			txtYourCurrentPlan.setBackground(new Color(192, 192, 192));
			txtYourCurrentPlan.setForeground(new Color(0, 0, 139));
			txtYourCurrentPlan.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtYourCurrentPlan.setText("Your current plan is not working.");
			txtYourCurrentPlan.setBounds(25, 294, 562, 71);
			contentPane.add(txtYourCurrentPlan);
			txtYourCurrentPlan.setColumns(10);

			txtYouShouldSpeak = new JTextField();
			txtYouShouldSpeak.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtYouShouldSpeak.setForeground(new Color(255, 0, 0));
			txtYouShouldSpeak.setBackground(new Color(192, 192, 192));
			txtYouShouldSpeak.setText("You should speak with a financial advisor to help you meet your goals. ");
			txtYouShouldSpeak.setBounds(25, 383, 658, 55);
			contentPane.add(txtYouShouldSpeak);
			txtYouShouldSpeak.setColumns(10);

			
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 484, 957, 399);
		contentPane.add(panel);
			
	}



}
