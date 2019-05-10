import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * This class creates a GUI input form to collect client information from 
 * a user to be used to analyze how they stand in regards to being able to
 * retire by a specific age.
 * @author Scott Hitchcock and Megan Patterson
 *
 */
public class InputForm extends JFrame{
	//Declaring all of the input fields that will go on our form
	private JFormattedTextField nameFld, currAgeFld, retireAgeFld, ageOfDeathFld, currIncomeFld, currExpensesFld;
	private JFormattedTextField taxRateFld, retireIncomeFld, retireExpensesFld, savingsGoalFld;
	private JFormattedTextField equityAmtFld, equityYieldFld, fiAmtFld, fiYieldFld;
	private JFormattedTextField reAmtFld, reYieldFld, otherAmtFld, otherYieldFld;
	private JTextField errorFld;
	private JButton submit, clear;
	private JRadioButton equityYesBtn, equityNoBtn, fiYesBtn, fiNoBtn, reYesBtn, reNoBtn, otherYesBtn, otherNoBtn;
	private boolean isSubmitted;
	
	//These variables will be used to hold the data that we pull in on the form
	private String name;
	private int  currentAge, retireAge, deathAge;
	private double currentIncome, currentExpenses, taxRate, retirementIncome, retirementExpenses, savingsGoal;
	private double equityAmt, equityYld, fiAmt, fiYld, reAmt, reYld, otherAmt, otherYld;
	
	//Using this arraylist to hold all of our text fields so that we can check to make sure none are blank
	private ArrayList<JFormattedTextField> fields = new ArrayList<>();  
	
	
	/**
	 * Constructor for our the InputForm class. 
	 */
	public InputForm() {
		isSubmitted = false;
	}	
	
	/**
	 * Calls the display GUI methoad and takes the data that 
	 * we have received from the user and creates a new customer object
	 * @return a customer object
	 */
	public Customer createCustomer() {
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Assets equityAsset = new Assets(1, equityAmt, equityYld);
		assets.add(equityAsset);
		Assets fiAsset = new Assets(2, fiAmt, fiYld); 
		assets.add(fiAsset); 
		Assets reAsset = new Assets(3, reAmt, reYld);
		assets.add(reAsset);
		Assets otherAsset = new Assets(4, otherAmt, otherYld);
		assets.add(otherAsset);
		
		Customer client = new Customer(name, currentAge, retireAge, taxRate, deathAge, currentIncome,
				currentExpenses, retirementIncome, retirementExpenses, savingsGoal, assets);
		return client;
	}
	
	/**
	 * This method is responsible for creating a GUI for the user that will collect all of the information
	 * that is needed to analyze the client's retirement needs.  Also checks to make sure to check that all
	 * user information is entered and won't let them submit until they have entered it all.
	 */
	public void displayGUI() {
		
		//Create the first panel that will hold just the title
		JPanel panel1 = new JPanel(new FlowLayout());
		
		//create a header with the name of the calculator.  
		//Set the font size and color and it to the first panel
		JLabel header = new JLabel("RETIREMENT PROABABILITY CALCULATOR");
		header.setForeground(Color.BLUE);
		header.setFont(new Font("Serif", Font.BOLD, 20));
		panel1.add(header);	
		
		//Create the 2nd panel which will have a 2 column grid layout
		JPanel panel2 = new JPanel(new GridLayout(32,2));
		
		//Adding label and field for user name
		panel2.add(new JLabel("Name"));
		panel2.add(nameFld = new JFormattedTextField());
		fields.add(nameFld);  //we add every text field to this ArrayList
		
		//Adding label and field for user's current age
		panel2.add(new JLabel("Current Age"));
		currAgeFld = new JFormattedTextField(new DecimalFormat("###"));
		panel2.add(currAgeFld);
		fields.add(currAgeFld);
		
		//Adding label and field for user's retirement age
		panel2.add(new JLabel("Retirement Age"));
		retireAgeFld = new JFormattedTextField(new DecimalFormat("###"));
		panel2.add(retireAgeFld);
		fields.add(retireAgeFld);
		
		//Adding label and field for user's life expectancy
		panel2.add(new JLabel("Life Expectancy"));
		ageOfDeathFld = new JFormattedTextField(new DecimalFormat("###"));
		panel2.add(ageOfDeathFld);
		fields.add(ageOfDeathFld);
		
		//Adding label and field for user's current income
		panel2.add(new JLabel("Current Annual Income (Pre-tax)"));
		currIncomeFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(currIncomeFld);
		fields.add(currIncomeFld);
		
		//Adding label and field for user's current expenses
		panel2.add(new JLabel("Current Annual Expenses"));
		currExpensesFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(currExpensesFld);
		fields.add(currExpensesFld);
		
		//Adding label and field for user's tax rate
		panel2.add(new JLabel("Effective Tax Rate (25% = .25)"));
		taxRateFld = new JFormattedTextField(new DecimalFormat(".##"));
		panel2.add(taxRateFld);
		fields.add(taxRateFld);
		
		//Adding label and field for projected retirement income
		panel2.add(new JLabel("Projected Retirement Income (Social Security/Pension)"));
		retireIncomeFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(retireIncomeFld);
		fields.add(retireIncomeFld);
		
		//Adding label and field for users retirement expenses
		panel2.add(new JLabel("Projected Annual Retirement Expenses"));
		retireExpensesFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(retireExpensesFld);
		fields.add(retireExpensesFld);
		
		//Adding label and field for users savings goal
		panel2.add(new JLabel("Savings Goal For Retirement"));
		savingsGoalFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(savingsGoalFld);
		fields.add(savingsGoalFld);
		
		//Adds a separator line across the frame
		panel2.add(new JSeparator());
		panel2.add(new JSeparator());

		//Create labels and buttons to check to see if client owns any equities
		panel2.add(new JLabel("Do You Own Any Equities?"));
		panel2.add(new JLabel(""));
		equityYesBtn = new JRadioButton("Yes");
		equityNoBtn = new JRadioButton("No");
		//Create a group for our 2 radio buttons so they can only select one.
		ButtonGroup equityButtons = new ButtonGroup();
		equityButtons.add(equityYesBtn);
		equityButtons.add(equityNoBtn);
		panel2.add(equityYesBtn);
		panel2.add(equityNoBtn);
		//Add event listeners for both buttons
		equityYesBtn.addActionListener(new equityYesListener());  
		equityNoBtn.addActionListener(new equityNoListener());

		//Create labels and field for users total equity amount
		//only activated if equityYesBtn is selected
		panel2.add(new JLabel("Equity Amount"));
		equityAmtFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(equityAmtFld);
		fields.add(equityAmtFld);
		
		//Create labels and field for users total equity yield
		//only activated if equityYesBtn is selected
		panel2.add(new JLabel("Equity Yield"));
		equityYieldFld = new JFormattedTextField(new DecimalFormat(".##"));
		panel2.add(equityYieldFld);
		fields.add(equityYieldFld);
		//Setting both equity fields to NOT be enabled to start
		equityAmtFld.setEnabled(false);
		equityYieldFld.setEnabled(false);
		
		//Adds a separator line across the frame
		panel2.add(new JSeparator());
		panel2.add(new JSeparator());
		
		//Create labels and buttons to check to see if client owns any fixed income
		panel2.add(new JLabel("Do You Own Any Fixed Income?"));
		panel2.add(new JLabel(""));
		fiYesBtn = new JRadioButton("Yes");
		fiNoBtn = new JRadioButton("No");
		//Create a group for our 2 radio buttons so they can only select one.
		ButtonGroup fiButtons = new ButtonGroup();
		fiButtons.add(fiYesBtn);
		fiButtons.add(fiNoBtn);
		panel2.add(fiYesBtn);
		panel2.add(fiNoBtn);
		//Add action listeners for both buttons
		fiYesBtn.addActionListener(new fiYesListener());
		fiNoBtn.addActionListener(new fiNoListener());
		
		//Create labels and field for users total fixed income amount
		//only activated if fiYesBtn is selected
		panel2.add(new JLabel("Fixed Income Amount"));
		fiAmtFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(fiAmtFld);
		fields.add(fiAmtFld);
		
		//Create labels and field for users total fixed income yield
		//only activated if fiYesBtn is selected
		panel2.add(new JLabel("Fixed Income Yield"));
		fiYieldFld = new JFormattedTextField(new DecimalFormat(".##"));
		panel2.add(fiYieldFld);
		fields.add(fiYieldFld);
		//Setting both Fixed income fields to NOT be enabled to start
		fiAmtFld.setEnabled(false);
		fiYieldFld.setEnabled(false);
		
		//Adds a separator line across the frame
		panel2.add(new JSeparator());
		panel2.add(new JSeparator());

		//Create labels and buttons to check to see if client owns any real estate
		panel2.add(new JLabel("Do You Own Any Real Esate Investments?"));
		panel2.add(new JLabel(""));
		reYesBtn = new JRadioButton("Yes");
		reNoBtn = new JRadioButton("No");
		//Create a group for our 2 radio buttons so they can only select one.
		ButtonGroup reButtons = new ButtonGroup();
		reButtons.add(reYesBtn);
		reButtons.add(reNoBtn);
		panel2.add(reYesBtn);
		panel2.add(reNoBtn);
		//Create a group for our 2 radio buttons so they can only select one.
		reYesBtn.addActionListener(new reYesListener());
		reNoBtn.addActionListener(new reNoListener());
		
		//Create labels and field for users total real estate amount
		//only activated if reYesBtn is selected
		panel2.add(new JLabel("Real Estate Amount"));
		reAmtFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(reAmtFld);
		fields.add(reAmtFld);
		
		//Create labels and field for users total real estate yield
		//only activated if reYesBtn is selected
		panel2.add(new JLabel("Real Estate Yield"));
		reYieldFld = new JFormattedTextField(new DecimalFormat(".##"));
		panel2.add(reYieldFld);
		fields.add(reYieldFld);
		//Setting both real estate fields to NOT be enabled to start
		reAmtFld.setEnabled(false);
		reYieldFld.setEnabled(false);
		
		//Adds a separator line across the frame
		panel2.add(new JSeparator());
		panel2.add(new JSeparator());
		
		//Create labels and buttons to check to see if client owns any other investments
		panel2.add(new JLabel("Do You Own Any Other Investments?"));
		panel2.add(new JLabel(""));
		otherYesBtn = new JRadioButton("Yes");
		otherNoBtn = new JRadioButton("No");
		//Create a group for our 2 radio buttons so they can only select one.
		ButtonGroup otherButtons = new ButtonGroup();
		otherButtons.add(otherYesBtn);
		otherButtons.add(otherNoBtn);
		panel2.add(otherYesBtn);
		panel2.add(otherNoBtn);
		//Create a group for our 2 radio buttons so they can only select one.
		otherYesBtn.addActionListener(new otherYesListener());
		otherNoBtn.addActionListener(new otherNoListener());
		
		//Create labels and field for users other amount
		//only activated if otherYesBtn is selected
		panel2.add(new JLabel("Other Amount"));
		otherAmtFld = new JFormattedTextField(new DecimalFormat("####.##"));
		panel2.add(otherAmtFld);
		fields.add(otherAmtFld);
		
		//Create labels and field for users other yield
		//only activated if otherYesBtn is selected
		panel2.add(new JLabel("Other Yield"));
		otherYieldFld = new JFormattedTextField(new DecimalFormat(".##"));
		panel2.add(otherYieldFld);
		fields.add(otherYieldFld);
		//Setting both other fields to NOT be enabled to start
		otherAmtFld.setEnabled(false);
		otherYieldFld.setEnabled(false);
		
		//Adds a separator line across the frame
		panel2.add(new JSeparator());
		panel2.add(new JSeparator());
		
		//creating this error field that we can print error messages in
		//Then we set it's border color to white and background to null
		//so that it looks like there is no text box on the frame.  
		//Sets font color to red for error messages also
		errorFld = new JTextField();
		errorFld.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
		errorFld.setBackground(null);
		errorFld.setForeground(Color.RED);
		panel2.add(errorFld);
			
		//Creates a 3rd panel with a Flow Layout that holds the submit and clear buttons
		//Submit button is defaulted to not being enabled and won't enable until user
		//has entered data in every required field. 
		JPanel panel3 = new JPanel(new FlowLayout());
		panel3.add(submit = new JButton("Submit"));
		submit.setEnabled(false);
		submit.addActionListener(new submitListener());  //add action listener for submit
		panel3.add(clear = new JButton("Clear"));
		clear.addActionListener(new clearListener());   //add action listener for clear
		
		//Create new frame with a border layout that will act as our parent frame
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		//Set background color to all the panels we have created to white
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);
		
		//Add All 3 panels to the frame and align them in their respective layouts.
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel2,BorderLayout.CENTER);
		frame.add(panel3,BorderLayout.SOUTH);
		frame.pack();  //sizes the window to fit the components that have been added
		frame.setLocationRelativeTo(null);   //tells the program to open the frame in center of the window
		frame.setVisible(true);	    //set frame to visible
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);   //tells the program to exit when the window is closed
		
		/**
		 * Document Listener that is used for all of the JText and JFormatted text fields.
		 * This will activate any time a change is made to a text field and it's job is to check to make
		 * sure that the user can't click the submit button until the client has filled in every text field.
		 */
		DocumentListener fieldsListener = new DocumentListener() {
			/**
			 * Calls the changedUpdate method
			 */
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			/**
			 * Calls the changedUpdate method
			 */
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);

			}
			
			/**
			 * On any change to the text fields it will run and make sure the client has not left the field 
			 * empty.  If it is empty it will set the submit button enabled to false.  It also checks the radio
			 * button for the respective asset fields.  If the no button is selected then the corresponding 
			 * amount and yield fields for that asset are allowed to be left blank.
			 */
			@Override
			public void changedUpdate(DocumentEvent e) {
				boolean isEnabled = false;  //default to false
				
				if(equityButtons.getSelection() != null && fiButtons.getSelection() != null 
						&& reButtons.getSelection() != null	&& otherButtons.getSelection() != null)
					isEnabled = true;
				
				//loop through all the fields in the ArrayList and make sure they aren't empty
				//Special handling for the various asset fields which will check to see whether the 
				//yes button is selected or not.
				for(int i = 0; i < fields.size(); i++) {
					if(fields.get(i).equals(equityAmtFld) || fields.get(i).equals(equityYieldFld)) {
						if(equityYesBtn.isSelected())
							if(fields.get(i).getText().isEmpty())
								isEnabled = false;
					}		
					else if(fields.get(i).equals(fiAmtFld) || fields.get(i).equals(fiYieldFld)) {
						if(fiYesBtn.isSelected())
							if(fields.get(i).getText().isEmpty())
								isEnabled = false;
					}
					else if(fields.get(i).equals(reAmtFld) || fields.get(i).equals(reYieldFld)) {
						if(reYesBtn.isSelected())
							if(fields.get(i).getText().isEmpty())
								isEnabled = false;
					}
					else if(fields.get(i).equals(otherAmtFld) || fields.get(i).equals(otherYieldFld)) {	
						if(otherYesBtn.isSelected()) 
							if(fields.get(i).getText().isEmpty())
								isEnabled = false;
					}
					else if(fields.get(i).getText().isEmpty())
						isEnabled = false;
				}
				submit.setEnabled(isEnabled);						
			}//end changedUpdate method
		}; //end document listener
		
		//add the document listener to all of the text fields in the ArrayList
		for(int i = 0; i < fields.size(); i++) {
			fields.get(i).getDocument().addDocumentListener(fieldsListener);
		}
	}//end displayGUI method
	
	/**
	 * Getter method for isSubmitted
	 * @return
	 */
	public boolean getIsSubmitted() {
		return isSubmitted;
	}

	/**
	 * Action listener class for the submit button.  Will run when the submit 
	 * button is active and is clicked by the user.  Reads the data from all 
	 * of our the fields on the form and assigns them to their respective variables.
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class submitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//This if statement checks to make sure the ages the client entered make sense
			//The clients death age must be greater then retirement age which must be greater then
			//their current age.  If they are then we proceed with getting the client data, if not
			//then we clear those fields and print an error message.
			if(Integer.parseInt(ageOfDeathFld.getText()) >  Integer.parseInt(retireAgeFld.getText())
					&& Integer.parseInt(retireAgeFld.getText()) > Integer.parseInt(currAgeFld.getText())) {
				
				name = nameFld.getText();
				currentAge = Integer.parseInt(currAgeFld.getText());
				retireAge = Integer.parseInt(retireAgeFld.getText());
				deathAge = Integer.parseInt(ageOfDeathFld.getText());
				currentIncome = Double.parseDouble(currIncomeFld.getText());
				currentExpenses = Double.parseDouble(currExpensesFld.getText());
				taxRate = Double.parseDouble(taxRateFld.getText());
				
				//converts tax rate to decimal if necessary
				if(taxRate > 1)
					taxRate = taxRate / 100;
			
				retirementIncome = Double.parseDouble(retireIncomeFld.getText());
				retirementExpenses = Double.parseDouble(retireExpensesFld.getText());
				savingsGoal = Double.parseDouble(savingsGoalFld.getText());
				
				//if client has equity assets then we pull them in
				//otherwise set each to zero
				if(equityYesBtn.isSelected()) {
					equityAmt = Double.parseDouble(equityAmtFld.getText());
					equityYld = Double.parseDouble(equityYieldFld.getText());
					if(equityYld > 1)
						equityYld = equityYld / 100;
				}
				else {
					equityAmt = 0;
					equityYld = 0;
				}
				
				//if client has fixed income assets then we pull them in
				//otherwise set each to zero
				if(fiYesBtn.isSelected()) {
					fiAmt = Double.parseDouble(fiAmtFld.getText());
					fiYld = Double.parseDouble(fiYieldFld.getText());
					if(fiYld > 1)
						fiYld = fiYld / 100;
				}
				else {
					fiAmt = 0;
					fiYld = 0;
				}
				
				//if client has real estate assets then we pull them in
				//otherwise set each to zero
				if(reYesBtn.isSelected()) {
					reAmt = Double.parseDouble(reAmtFld.getText());
					reYld = Double.parseDouble(reYieldFld.getText());
					if(reYld > 1)
						reYld = reYld / 100;
				}
				else {
					reAmt = 0;
					reYld = 0;
				}
				
				//if client has other assets then we pull them in
				//otherwise set each to zero.
				if(otherYesBtn.isSelected()) {
					otherAmt = Double.parseDouble(otherAmtFld.getText());
					otherYld = Double.parseDouble(otherYieldFld.getText());
					if(otherYld > 1)
						otherYld = otherYld / 100;
				}
				else {
					otherAmt = 0;	
					otherYld = 0;
				}
				isSubmitted = true;
				
			}//end big if statement
			
			//if client hasn't entered correct age, delete those fields and display error
			else {
				retireAgeFld.setText("");
				ageOfDeathFld.setText("");
				errorFld.setText("Age of Death/Retirement Age Invalid. Please re-enter");
			}
			

		}	
	}
	
	/**
	 * Action Listener for the clear button.  Simply clears all the data in any text 
	 * boxes that our on the form.
	 * @author Scott Hitchcock & Megan Patterson
	 *
	 */
	class clearListener implements ActionListener{
		/**
		 * Method to activate when button is clicked.  Loops through the arraylist
		 * of text fields and clears all of them.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < fields.size(); i++) {
				fields.get(i).setText("");
			}
		}
	}
	
	/**
	 * Action Listener for equity Yes radio button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class equityYesListener implements ActionListener{
		/**
		 * If user clicks yes button then we clear anything
		 * that might have been in it.  Also we set status 
		 * to enabled for both equity text fields
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			equityAmtFld.setText("");
			submit.setEnabled(false);
			equityAmtFld.setEnabled(true);
			equityYieldFld.setEnabled(true);
		}
	}
	
	/**
	 * Action Listener for equity No radio button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class equityNoListener implements ActionListener{
		/**
		 * If user selects no radio button.  Then this
		 * will clear anything that is in both of the equity
		 * text fields.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			equityAmtFld.setText(" "); //this is only here to activate the listener
			equityYieldFld.setText("");
			equityAmtFld.setEnabled(false);
			equityYieldFld.setEnabled(false);
		}
	}
	
	/**
	 * Action Listener for fixed income yes radio button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class fiYesListener implements ActionListener{
		/**
		 * If user clicks yes button then we clear anything
		 * that might have been in it.  Also we set status 
		 * to enabled for both fixed income text fields
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			fiAmtFld.setText("");
			submit.setEnabled(false);
			fiAmtFld.setEnabled(true);
			fiYieldFld.setEnabled(true);
		}
	}
	
	/**
	 * Action Listener for fixed income no radio button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class fiNoListener implements ActionListener{
		/**
		 * If user selects no radio button.  Then this
		 * will clear anything that is in both of the fixed income
		 * text fields.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			fiAmtFld.setText(" "); //this is only here to activate the listener
			fiAmtFld.setText("");
			fiAmtFld.setEnabled(false);
			fiYieldFld.setEnabled(false);
		}
	}
	
	/**
	 * Action Listener for real estate yes button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class reYesListener implements ActionListener{
		/**
		 * If user clicks yes button then we clear anything
		 * that might have been in it.  Also we set status 
		 * to enabled for both real estate text fields
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			reAmtFld.setText("");
			submit.setEnabled(false);
			reAmtFld.setEnabled(true);
			reYieldFld.setEnabled(true);			
		}
	}
	
	/**
	 * Action Listener for real estate no radio button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class reNoListener implements ActionListener{
		/**
		 * If user selects no radio button.  Then this
		 * will clear anything that is in both of the real estate
		 * text fields.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			reAmtFld.setText(" "); //this is only here to activate the listener
			reYieldFld.setText("");
			reAmtFld.setEnabled(false);
			reYieldFld.setEnabled(false);			
		}
	}
	
	/**
	 * Action Listener for other assets yes radio button	
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class otherYesListener implements ActionListener{
		/**
		 * If user clicks yes button then we clear anything
		 * that might have been in it.  Also we set status 
		 * to enabled for both other assets text fields
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			otherAmtFld.setText("");
			submit.setEnabled(false);
			otherAmtFld.setEnabled(true);
			otherYieldFld.setEnabled(true);			
		}
	}
	
	/**
	 * Action Listener for other assets no radio button
	 * @author Scott Hitchcock and Megan Patterson
	 *
	 */
	class otherNoListener implements ActionListener{
		/**
		 * If user selects no radio button.  Then this
		 * will clear anything that is in both of the other assets
		 * text fields.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			otherAmtFld.setText(" "); //this is only here to activate the listener
			otherAmtFld.setText("");
			otherAmtFld.setEnabled(false);
			otherYieldFld.setEnabled(false);	
		}
	}
}
