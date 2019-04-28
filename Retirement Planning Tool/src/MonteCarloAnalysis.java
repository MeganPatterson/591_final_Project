import java.io.File;
import java.util.*;

public class MonteCarloAnalysis {
	private double inflationRate;      //projected inflation rate
	private ArrayList<MarketReturns> mrktReturns;  //projected market returns going out year by year
	private	Customer client; //Customer object containing the data of our client
	private String marketDataFile = "marketReturns.csv";   //the name of the file that contains our returns
	private ArrayList<Double> assetGrowth = new ArrayList<>(); //going to track the growth of our asset from year to year
	/**
	 * Constructor for a MonteCarloAnalysis object.  Sets our instance variables to what is being
	 * passed to us.
	 * @param inflationRate  The projected annual inflation rate expressed as a decimal.
	 * @param mrktReturns    A 2 dimensional array of doubles that lists market returns by year.
	 * @param client         An object of type Customer for the client who we will be analyzing. 
	 */
	public MonteCarloAnalysis(double inflationRate, Customer client) {
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

	public ArrayList<Double> assetGrowth() {
		ArrayList<Assets> assets = new ArrayList<>(client.getAssets()); //creating a copy of assets that we can change
		ArrayList<Double> totalAssets = new ArrayList<>();  //going to use this to track total assets year over year
		double total = 0; //using this to track the total assets each year 
		
		//for the first year we just add all the assets up
		for(int j = 0; j < assets.size(); j++) {
			total = total + assets.get(j).getAmount();
		}
		totalAssets.add(total);
		
		Random generator = new Random(); //random number generator to randomly pick a year of returns
		
		for(int i = client.getCurrentAge() + 1; i <= client.getAgeOfDeath(); i++) {	
			total = 0;
			int rand = generator.nextInt(mrktReturns.size()); //generate random year of returns
			
			//this for loop will loop through all the assets in the asset arraylist
			for(int j = 0; j < assets.size(); j++) {
				double assetTotal = 0;
				
				int tempType = assets.get(j).getAssetType();
				double tempAmt = assets.get(j).getAmount();
				double tempRate = assets.get(j).getYield();
				
				//if asset is equity we want it to use this. Our returns are total return so no need to include the yield
				if(tempType == 1) {
					assetTotal = tempAmt;
					assetTotal = assetTotal * (1 + mrktReturns.get(rand).getStockReturn());
				}
				//for fixed income we use this.  Our returns are total return so no need to include the yield
				else if(tempType == 2) {
					assetTotal = tempAmt;
					assetTotal = assetTotal * (1 + mrktReturns.get(rand).getBondReturn());
				}
				//for real estate use this.  Want to grow it at the yield plus inflation 
				else if(tempType == 3) {
					assetTotal = tempAmt * (1 + tempRate);
					assetTotal = assetTotal * (1 + inflationRate);
				}
				//finally for other we want to use this.  will just grow at the rate we were provided
				else if(tempType == 4) {
					assetTotal = tempAmt * (1 + tempRate);
				}
				assets.get(j).setAmount(assetTotal);
				total = total + assetTotal;
				
			}//end assets for loop
			
			totalAssets.add(total);
			
		}//end years for loop
		return totalAssets;
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
	
	public static void main(String[] args) {
		Assets stock1 = new Assets(1, 100000, .05);
		Assets stock2 = new Assets(2, 200000, .03);
		Assets stock3 = new Assets(3, 300000, .04);
		Assets stock4 = new Assets(4, 400000, .02);

		ArrayList<Assets> testAssets = new ArrayList<>();
		testAssets.add(stock1);
		testAssets.add(stock2);
		testAssets.add(stock3);
		testAssets.add(stock4);
		
		ArrayList<Debt>	testDebts = new ArrayList<>();
		
		Customer johnDoe = new Customer("John Doe", 50, 55, .25, 55, 60, 75000, 50000, 25000, 20000, 1000000, testAssets, testDebts);
		
		MonteCarloAnalysis test = new MonteCarloAnalysis(.02, johnDoe);
		
		ArrayList <Double> test2 = new ArrayList<>();
		test2 = test.assetGrowth();
		for(int i = 0; i < test2.size(); i++) {
			System.out.println(test2.get(i));
		}
		
	}
	
}
