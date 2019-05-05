import java.util.*;

public class Customer {
	private String name;        //client name
	private int currentAge;     //clients current age 
	private int retirementAge;  //Projected retirement age of client
	private double taxRate;     //clients projected tax rate expressed in decimal format
	private int riskLevel;      //Clients ability to take risk. Int value from 1 to 100
	private int ageOfDeath;     //How long the client expects to live
	private int yearsInRetirement;  //This is the age of death minus their retirement age
	private	ArrayList<Assets>  assets;    //ArrayList of Asset objects to represent what the client owns.
	private ArrayList<Debt> debts;   //ArrayList of debt objects
	private double currentIncome; //Clients current annual income
	private double currentExpenses; //Clients current annual expenses
	private double retirementIncome;  //Projected income during retirement(Social sec, pension, etc)
	private double retirementExpenses;  //Projected expenses during retirement
	private double savingsGoal;   //Goal for savings at start of retirement.
	
	/**
	 * General constructor for the customer class.  
	 */
	public Customer() {
		assets = new ArrayList<Assets>();
		debts = new ArrayList<Debt>();
	}
	
	
	/**
	 * Constructor for the Customer class. Is passed through all the data that are customer has 
	 * provided for us, and sets our instance variables equal to this data.  
	 * @param name  Client's name
	 * @param curAge  Client's current age in Int form.
	 * @param retireAge Client's retirement age in Int form.
	 * @param taxRate  Client's effective tax rate expressed as a decimal. For example 50% = .5
	 * @param riskLvl Int value from 1 to 100 representing clients willingness to take risk
	 * @param deathAge  Age in int form that the client expects to die
	 * @param curIncome  Client's current annual income
	 * @param curExp  Client's current annual expenses
	 * @param retireIncome  Client's projected income during retirement
	 * @param retireExp  Client's projected expenses during retirement
	 * @param savingsGoal Amount client wants to have saved at retirement 
	 * @param assets  ArrayList of asset objects expressing what the client currently owns.
	 */
	public Customer(String name, int curAge, int retireAge, double taxRate, int riskLvl, int deathAge,
			double curIncome, double curExp, double retireIncome, double retireExp, double savingsGoal, 
			ArrayList<Assets> assets, ArrayList<Debt> debts) {
		
		this.name = name;   
		this.currentAge = curAge;
		this.retirementAge = retireAge;
		this.taxRate = taxRate; 
		this.riskLevel = riskLvl;
		this.ageOfDeath = deathAge;
		this.currentIncome = curIncome; 
		this.currentExpenses = curExp; 
		this.retirementIncome = retireIncome;
		this.retirementExpenses = retireExp;
		this.savingsGoal = savingsGoal; 
		this.assets = assets; 
		this.debts = debts;
		yearsInRetirement = ageOfDeath - retirementAge; 
	}

	/**
	 * This method will prompt the user to enter all of the data that we need to get a complete profile on them.  
	 * At the same time it will also check to make sure all of the users inputs are valid and keep asking the client for additional 
	 * assets if they have multiple things they need to enter.  
	 */
	public void getCustomerInfo() {
		System.out.println("Welcome to your retirement planning tool.  Lets start by gathering some information about your self.");
		System.out.println("There are quite a few questions so this make take a couple minutes.");
		Scanner userInput = new Scanner(System.in);
		String repeatAnswer = "yes"; //This will be the answer to see if the customer wants to re-enter their info.
		
		while(repeatAnswer.charAt(0) == 'y' || repeatAnswer.charAt(0) == 'Y'){
			//Get customer name
			System.out.print("Please enter your name: ");
			name = userInput.nextLine();
			
			//Get customer age.  Make sure it can only be an int.
			System.out.print("\nWhat is your current age: ");
			while(!userInput.hasNextInt()) {
				System.out.print("Please enter your age (numbers only): ");
				userInput.next();
			}
			currentAge = userInput.nextInt();
			
			//Get customer's retirement age.  Make sure it can only be an int.
			System.out.print("\nWhat age do you plan to retire at: ");
			while(!userInput.hasNextInt()) {
				System.out.print("Please enter your retirement age (numbers only): ");
				userInput.next();
			}
			retirementAge = userInput.nextInt();
			
			//Get customers life expectancy.  Make sure it can only be an int
			System.out.print("\nWhat age do you expect to live to: ");
			while(!userInput.hasNextInt()) {
				System.out.print("Please enter your estimated life span (numbers only): ");
				userInput.next();
			}
			ageOfDeath = userInput.nextInt();
			yearsInRetirement = ageOfDeath - retirementAge;
			
			//Get customers annual pre-tax income.  Make sure it's double only
			System.out.println("\nNow let's get some information on your finances.");
			System.out.print("What is your current annual pre-tax income: ");
			while(!userInput.hasNextDouble()) {
				System.out.print("Please enter your current annual pre-tax income(numbers only): ");
				userInput.next();
			}
			currentIncome = userInput.nextDouble();
			
			//Get customer's current annual expenses.  Make sure it's double format only
			System.out.print("\nHow much are your current annual expenses: ");
			while(!userInput.hasNextDouble()) {
				System.out.print("Please enter your current annual expenses(numbers only): ");
				userInput.next();
			}
			currentExpenses = userInput.nextDouble();
			
			//Get customer's tax rate.  Make sure it's a double and in decimal form.
			System.out.print("\nWhat is your current effective tax rate as a decimal (ex: 35% = .35): ");
			while(!userInput.hasNextDouble()) {
				System.out.print("Please enter your effective tax rate(numbers only): ");
				userInput.next();
			}
			taxRate = userInput.nextDouble();
			while(taxRate > 1) {
				System.out.print("Please make sure your tax rate is in decimal format: ");
				while(!userInput.hasNextDouble()) {
					System.out.print("Please enter your effective tax rate(numbers only): ");
					userInput.next();
				}
				taxRate = userInput.nextDouble();
			}
			
			//Get customer's retirement income
			System.out.println("\nWhat do you expect your annual income to be during retirement");
			System.out.print("(Anything not included in your assets like pension or social security): ");
			while(!userInput.hasNextDouble()) {
				System.out.print("Please enter your expected retirement income(numbers only): ");
				userInput.next();
			}
			retirementIncome = userInput.nextDouble();
	
			//Get customer's retirement expenses
			System.out.print("\nWhat do you expect your annual expenses to be during retirement: ");
			while(!userInput.hasNextDouble()) {
				System.out.print("Please enter your expected retirement expenses(numbers only): ");
				userInput.next();
			}
			retirementExpenses = userInput.nextDouble();
			
			//Get customers retirement savings goal
			System.out.print("\nWhat is your savings goal for the start of your retirement? ");
			while(!userInput.hasNextDouble()) {
				System.out.print("Please enter your savings goal(numbers only): ");
				userInput.next();
			}
			savingsGoal = userInput.nextDouble();
			
			//Going to gather information on the customers assets and store them in an ArrayList of asset objects
			System.out.print("\nNow let's gather some information on any assets you may have (equities, bonds, real estate investments, etc.");
			
			assets.clear(); //make sure the arraylist is totally empty before starting
			
			for(int i = 1; i <= 4; i++) {
				String answer;
				if(i == 1)
					System.out.print("\nDo you currently own any equity investments? (Y or N): ");
				else if(i == 2)
					System.out.print("\nDo you currently own any fixed income investments? (Y or N): ");
				else if(i == 3)
					System.out.print("\nDo you currently own any real estate investments? (Y or N): ");
				else if(i == 4)
					System.out.print("\nDo you currently own any other investments?  (Y or N): ");
		
				answer = userInput.next();
				
				if(answer.charAt(0) == 'y' || answer.charAt(0) == 'Y'){
					System.out.print("\nWhat is the total amount that you own: ");
					while(!userInput.hasNextDouble()) {
						System.out.print("Please enter how much you own in numbers only:  ");
						userInput.next();
					}
					double assetAmount = userInput.nextDouble();
					
					System.out.print("\nWhat is the current annual yield on the asset(5% = .05): ");
					while(!userInput.hasNextDouble()) {
						System.out.print("Please enter the annual yield(numbers only): ");
						userInput.next();
					}
					double assetYield = userInput.nextDouble();
					while(assetYield > 1) {
						System.out.print("Please make sure the yield is in decimal format: ");
						while(!userInput.hasNextDouble()) {
							System.out.print("Please enter the annual yield(numbers only): ");
							userInput.next();
						}
						assetYield = userInput.nextDouble();
					}
					
					//create new asset and it to our arrayList
					Assets newAsset = new Assets(i, assetAmount, assetYield);
					assets.add(newAsset);
				}
				else {
					Assets newAsset = new Assets(i,0,0);
					assets.add(newAsset);
				}
			}//end of for loop
			
			//Going to ask the client about his ability to take risk
			riskLevel  = 0;
			System.out.print("\nFinally, on a scale of 1 to 100, how would you rate your willingness to take risk? ");
			while(riskLevel < 1 || riskLevel > 100) {
				while(!userInput.hasNextInt()) {
					System.out.print("Please enter a number from 1 to 100: ");
					userInput.next();
				}
				riskLevel = userInput.nextInt();
			}
			displayCustomerTraits();
			userInput.nextLine();
			System.out.print("Does you want to make any corrections (Y or N): ");
			repeatAnswer = userInput.nextLine();
		}//end while loop
			userInput.close();
	}
	
	/**
	 * This method summarizes everything that the customer has entered.  It formats the output
	 * and displays it to the user so they can see that have entered things correctly.
	 */
	public void displayCustomerTraits() {
		System.out.println("**************************************************************");
		System.out.println("Name: " + name);
		System.out.println("Current Age: " + currentAge);
		System.out.println("Retirement Age: " + retirementAge);
		System.out.println("Life expectancy: " + ageOfDeath);
		System.out.printf("Current Income: %,.2f", currentIncome);
		System.out.printf("\nCurrent Expenses: %,.2f", currentExpenses);
		System.out.println("\nCurrent Tax Rate: " + taxRate);
		System.out.printf("Expected Retirement Income: %,.2f", retirementIncome);
		System.out.printf("\nExpected Retirement Expenses: %,.2f" , retirementExpenses);
		System.out.printf("\nRetirement Savings Goal: %,.2f" , savingsGoal);
		for(int i = 0; i < assets.size(); i++) {
			if(i + 1 == 1)
				System.out.printf("\nEquities Owned: %,.2f" , assets.get(i).getAmount());
			else if (i + 1 == 2)
				System.out.printf("Fixed Income Owned: %,.2f" , assets.get(i).getAmount());
			else if (i + 1 == 3)
				System.out.printf("Real Esate Owned: %,.2f" , assets.get(i).getAmount());
			else if (i + 1 == 4)
				System.out.printf("Other Investments Owned: %,.2f" , assets.get(i).getAmount());
			System.out.println("; Yield: " + assets.get(i).getYield());
		}
		System.out.println("Willingness To Take Risk: " + riskLevel);
		System.out.println("**************************************************************");
	}
	
	
	/**
	 * Getter method for name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for currentAge
	 * @return currentAge
	 */
	public int getCurrentAge() {
		return currentAge;
	}

	/**
	 * Getter method for retirementAge
	 * @return retirementAge
	 */
	public int getRetirementAge() {
		return retirementAge;
	}
	
	/**
	 * Getter method for taxRate
	 * @return taxRate
	 */
	public double getTaxRate() {
		return taxRate;
	}

	/**
	 * Getter method for RiskLevel
	 * @return riskLevel
	 */
	public int getRiskLevel() {
		return riskLevel;
	}

	/**
	 * Getter method for ageOfDeath
	 * @return ageOfDeath
	 */
	public int getAgeOfDeath() {
		return ageOfDeath;
	}

	/**
	 * Getter method for yearsInRetirement
	 * @return yearsInRetirement
	 */
	public int getYearsInRetirement() {
		return yearsInRetirement;
	}

	/**
	 * Getter method for assets
	 * @return assets
	 */
	public ArrayList<Assets> getAssets() {
		return assets;
	}

	/**
	 * Getter method for current Income
	 * @return currentIncome
	 */
	public double getCurrentIncome() {
		return currentIncome;
	}
	
	/**
	 * Getter method for current expenses
	 * @return currentExpenses
	 */
	public double getCurrentExpenses() {
		return currentExpenses;
	}

	/**
	 * Getter method for retirement Income
	 * @return retirementIncome
	 */
	public double getRetirementIncome() {
		return retirementIncome;
	}
	
	/**
	 * Getter method for retirement expenses
	 * @return retirementExpenses
	 */
	public double getRetirementExpenses() {
		return retirementExpenses;
	}

	/**
	 * Getter method for savingsGoal
	 * @return savingsGoal
	 */
	public double getSavingsGoal() {
		return savingsGoal;
	}

	/**
	 * Getter method for debts
	 * @return debts
	 */
	public ArrayList<Debt> getDebts() {
		return debts;
	}
	
	
	//main class to run some tests
//	public static void main(String[] args) {
//		Customer test = new Customer();
//		test.getCustomerInfo();
//		//MonteCarloAnalysis mcTest = new MonteCarloAnalysis(.02, test);
//	
//	}
	
}
