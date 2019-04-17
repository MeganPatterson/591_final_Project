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

	public String getName() {
		return name;
	}

	public int getCurrentAge() {
		return currentAge;
	}

	public int getRetirementAge() {
		return retirementAge;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public int getRiskLevel() {
		return riskLevel;
	}

	public int getAgeOfDeath() {
		return ageOfDeath;
	}

	public int getYearsInRetirement() {
		return yearsInRetirement;
	}

	public ArrayList<Assets> getAssets() {
		return assets;
	}

	public double getCurrentIncome() {
		return currentIncome;
	}

	public double getCurrentExpenses() {
		return currentExpenses;
	}

	public double getRetirementIncome() {
		return retirementIncome;
	}

	public double getRetirementExpenses() {
		return retirementExpenses;
	}

	public double getSavingsGoal() {
		return savingsGoal;
	}

	public ArrayList<Debt> getDebts() {
		return debts;
	}
	
	
	
}
