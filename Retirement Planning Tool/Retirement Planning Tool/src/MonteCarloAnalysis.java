import java.io.File;
import java.util.*;

public class MonteCarloAnalysis {
	private double inflationRate;      //projected inflation rate
	private ArrayList<MarketReturns> mrktReturns;  //projected market returns going out year by year
	private	Customer client; //Customer object containing the data of our client
	private String marketDataFile = "marketReturns.csv";   //the name of the file that contains our returns
	private double probabilityOfSuccess; //will store the clients probability of succesful retirement
	
	/**
	 * Constructor for a MonteCarloAnalysis object.  Sets our instance variables to what is being
	 * passed to us.
	 * @param inflationRate  The projected annual inflation rate expressed as a decimal.
	 * @param mrktReturns    A 2 dimensional array of doubles that lists market returns by year.
	 * @param client         An object of type Customer for the client who we will be analyzing. 
	 */
	public MonteCarloAnalysis(double inflationRate, Customer client) {
		probabilityOfSuccess = 0;
		this.inflationRate = inflationRate;
		this.client = client;
		mrktReturns = new ArrayList <MarketReturns>();
		importMarketData();
	}
	
	/**
	 * Private method that parses a CSV of market returns and loads them into our mrktReturns
	 * ArrayList.  The format of the CSV columns is year, bond return, and stock return.  The 
	 * returns are all shown as percents in decimal format.
	 */
	private void importMarketData() {
		File f = new File(marketDataFile);
		Scanner in; 
		try {
			in = new Scanner(f);
			in.nextLine(); //skip the first line since it has header rows
			
			while(in.hasNextLine()) {
				String wholeLine = in.nextLine();
				String splitLine[] = wholeLine.split(",");
				
				int year = Integer.parseInt(splitLine[0]);
				double bondReturn = Double.parseDouble(splitLine[1]);
				double stockReturn = Double.parseDouble(splitLine[2]);
				
				MarketReturns newYear = new MarketReturns(year, bondReturn, stockReturn);
				mrktReturns.add(newYear);
			}
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public HashMap<Integer,Double> assetGrowth() {
		ArrayList<Assets> assets = new ArrayList<>(); //Arraylist that will store a copy of assets that we can change
		HashMap<Integer,Double> totalAssets = new HashMap<>();  //going to use this to track total assets year over year
		double total = 0; //using this to track the total assets each year 
		
		//Creating a copy of our assets that we will be able to change
		for(int i = 0; i < client.getAssets().size(); i++) {
			Assets tempAsset = new Assets(client.getAssets().get(i));
			assets.add(tempAsset);
		}
		
		//for the first year we just add all the assets up
		for(int j = 0; j < assets.size(); j++) {
			total = total + assets.get(j).getAmount();
		}
		totalAssets.put(client.getCurrentAge(), total);
		
		Random generator = new Random(); //random number generator to randomly pick a year of returns
		
		//going to grow the assets starting with the clients current age and going to 20 years past their death
		//Doing this so they will know what happens if they happen to live longer then expected.
		//Starts with client current age + 1 because we already did the first year above
		for(int i = client.getCurrentAge() + 1; i <= client.getAgeOfDeath() + 20; i++) {	
			total = 0;
			int rand = generator.nextInt(mrktReturns.size()); //generate random year of returns
			
			HashMap<Integer, Double> allocation = new HashMap<>();
			allocation = getAssetAllocation(assets);
			
			//this for loop will loop through all the assets in the asset arraylist
			for(int j = 0; j < assets.size(); j++) {
				double assetTotal = 0;
				
				int tempType = assets.get(j).getAssetType();
				double tempAmt = assets.get(j).getAmount();
				double tempRate = assets.get(j).getYield();
				
				//if asset is equity we want it to use this. Our returns are total return so no need to include the yield
				if(tempType == 1) {
					double savings = allocation.get(1) * computeYearlySavings(i); 
					assetTotal = tempAmt;
					assetTotal = (assetTotal + savings ) * (1 + mrktReturns.get(rand).getStockReturn());
				}
				//for fixed income we use this.  Our returns are total return so no need to include the yield
				else if(tempType == 2) {
					double savings = allocation.get(2) * computeYearlySavings(i); 
					assetTotal = tempAmt;
					assetTotal = (assetTotal + savings)* (1 + mrktReturns.get(rand).getBondReturn());
				}
				//for real estate use this.  Want to grow it at the yield plus inflation 
				else if(tempType == 3) {
					double savings = allocation.get(3) * computeYearlySavings(i); 
					assetTotal = tempAmt * (1 + tempRate);
					assetTotal = (assetTotal + savings)* (1 + inflationRate);
				}
				//finally for other we want to use this.  will just grow at the rate we were provided
				else if(tempType == 4) {
					double savings = allocation.get(4) * computeYearlySavings(i); 
					assetTotal = (tempAmt + savings) * (1 + tempRate);
				}
				assets.get(j).setAmount(assetTotal);
				total = total + assetTotal;
				
			}//end assets for loop
			
			totalAssets.put(i, total);
			
		}//end years for loop
		return totalAssets;
	}
	
	/**
	 * Method computes the asset allocation of each types of assets and returns them
	 * in decimal form stored in a HashMap.
	 * @param assets Arraylist of assets to computer the allocation on
	 * @return A HashMap with a key of integers corresponding to asset types
	 * and holding doubles that reflect their asset allocation.
	 */
	public HashMap<Integer, Double> getAssetAllocation(ArrayList<Assets> assets){
		HashMap<Integer, Double> assetAllocation = new HashMap<Integer,Double>();
		double totalAssets = 0;
		for(int i = 0; i < assets.size(); i++) {
			totalAssets = totalAssets + assets.get(i).getAmount();
		}
		
		for(int i = 0; i < assets.size(); i++) {
			double percent = 0.0;
			percent = assets.get(i).getAmount() / totalAssets;
			assetAllocation.put(i + 1, percent);  //add 1 to i for our key because our asset types don't start with 0
		}
		
		return assetAllocation;
	}
	
	/**
	 * This method will look at the clients age and will return a value showing how much money the client was able
	 * to save that year or how much money they spent.  If they client spent more then they saved it will return a negative
	 * value.  Am assuming that all income pre-retirement is taxable, and that all income post retirement is tax free.
	 * @param ageInYearToCompute This is the age of the client in the year that we want to computer his savings/expenses
	 * @return The amount that the client has saved or spent for the year.  Will be positive for savings 
	 * and negative if they spent more then their income.
	 */
	public double computeYearlySavings(int ageInYearToCompute) {
		
		//if client isn't retired yet we want to use this IF statement
		//Am making the assumption that the clients income is taxable
		///and that his income/expenses will increase each year by rate of inflation
		if(ageInYearToCompute < client.getRetirementAge()) {
			int yearNumber = ageInYearToCompute - client.getCurrentAge();
			double inflationIncome = client.getCurrentIncome() * Math.pow(1 + inflationRate, yearNumber);
			double inflationExpenses = client.getCurrentExpenses() * Math.pow(1 + inflationRate, yearNumber);
			return ( inflationIncome * ( 1 - client.getTaxRate() ) ) - inflationExpenses; 
			
		}
		//if client is retired yet we want to use this ELSE statement
		//Am making the assumption that the clients income is non-taxable
		///and that his income/expenses will increase each year by rate of inflation
		//starting with year of retirement
		else {
			int yearNumber = ageInYearToCompute - client.getCurrentAge();
			double inflationIncome = client.getRetirementIncome() * Math.pow(1 + inflationRate, yearNumber);
			double inflationExpenses = client.getRetirementExpenses() * Math.pow(1 + inflationRate, yearNumber);
			return inflationIncome - inflationExpenses;
		}
	}
	
	/**
	 * This method will run a monte carlo simulation on the asset growth of a portfolio.  It will store
	 * the average results in a HashMap, with the client age as the key, and the average dollar amount at 
	 * that age as the value.  
	 * @param iterations The number of times we want the monte carlo simulation to run
	 * @return A HashMap containing the average results across all the iterations
	 */
	public HashMap<Integer,Double> monteCarloSimulation(int iterations){
		HashMap<Integer,Double> averageResults = new HashMap<Integer, Double>();  //will hold our results
		double tempAmount = 0.0; //just holds a temporary value before we add it to new map
		int counter = 0; //using this to count the number of times we have a positive balance at age of death
		
		//this HashMap will be a temporary hashmap of the results from asset growth method
		HashMap<Integer,Double> temp = new HashMap<Integer, Double>();  
		temp = assetGrowth();
		
		//FOR EACH loop that we are running the first time to establish all our keys
		//in our averageResults hashMap
		for (Integer theKey : temp.keySet()) {
			averageResults.put(theKey, temp.get(theKey));
		}
		
		//FOR loop to run for the number of iterations in our simulation
		for(int i = 1; i < iterations; i++) {
			temp = assetGrowth();
			
			//IF statement to see if client still has money left at projected age of death
			if(temp.get(client.getAgeOfDeath()) > 0)
				counter++;
			
			//FOR EACH loop to take our values from our latest iteration
			//and add them to our averageResults HashMap
			for (Integer theKey : temp.keySet()) {
				tempAmount = temp.get(theKey) + averageResults.get(theKey);
				averageResults.put(theKey, tempAmount);
			}
		}
		
		//Final FOR EACH loop to convert the hashmap from cumulative total to an average
		for (Integer theKey : averageResults.keySet()) {
			double average = averageResults.get(theKey) / iterations;
			averageResults.put(theKey, average);
		}
		probabilityOfSuccess = (double) counter / iterations; //compute probability
		return averageResults;
	}
		
	/**
	 * Getter method for inflation rate
	 * @return inflationRate
	 */
	public double getInflationRate() {
		return inflationRate;
	}

	/**
 	* Getter method for mrkt returns.
 	* @return mrktReturns
 	*/
	public ArrayList<MarketReturns> getMrktReturns() {
		return mrktReturns;
	}

	/**
	 * Getter method for client
	 * @return client 
	 */
	public Customer getClient() {
		return client;
	}
	
	/**
	 * Getter method for probability of Success
	 * @return probabilityOfSuccess
	 */
	public double getProbabilityOfSuccess() {
		return probabilityOfSuccess;
	}
	
//	//main method to test to make sure it's running correctly
//	public static void main(String[] args) {
//		Assets stock1 = new Assets(1, 100000, .05);
//		Assets stock2 = new Assets(2, 200000, .03);
//		Assets stock3 = new Assets(3, 300000, .04);
//		Assets stock4 = new Assets(4, 400000, .03);
//
//		
//		ArrayList<Assets> testAssets = new ArrayList<>();
//		testAssets.add(stock1);
//		testAssets.add(stock2);
//		testAssets.add(stock3);
//		testAssets.add(stock4);
//		
//		ArrayList<Debt>	testDebts = new ArrayList<>();
//		
//		Customer johnDoe = new Customer("John Doe", 50, 55, .25, 55, 60, 85000, 75000, 25000, 75000, 1000000, testAssets, testDebts);
//		
//		MonteCarloAnalysis test = new MonteCarloAnalysis(.02, johnDoe);
//		
//		HashMap <Integer,Double> test2 = new HashMap<>();
//		test2 = test.assetGrowth();
//		for(int i = 0; i < test2.size(); i++) {
//			int age = test.getClient().getCurrentAge() + i;
//			System.out.print("Age: " + age + "; ");
//			System.out.println(test2.get(age));
//		}
//		
//		HashMap<Integer,Double> test3 = new HashMap<>();
//		test3 = test.getAssetAllocation(testAssets);
//		for(int i = 0; i < test3.size(); i++) {
//			System.out.println(test3.get(i+1));
//		}
//		test.monteCarloSimulation(5);
//		
//	}
	
}
