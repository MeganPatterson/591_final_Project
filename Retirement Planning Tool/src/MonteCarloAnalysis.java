import java.util.*;

public class MonteCarloAnalysis {
	private double inflationRate;      //projected inflation rate
	private double[][] marketReturns;  //projected market returns going out year by year
	private	Customer client; //Customer object containing the data of our client
	
	/**
	 * Constructor for a MonteCarloAnalysis object.  Sets our instance variables to what is being
	 * passed to us.
	 * @param inflationRate  The projected annual inflation rate expressed as a decimal.
	 * @param mrktReturns    A 2 dimensional array of doubles that lists market returns by year.
	 * @param client         An object of type Customer for the client who we will be analyzing. 
	 */
	public MonteCarloAnalysis(double inflationRate, double[][] mrktReturns, Customer client) {
		this.inflationRate = inflationRate;
		this.marketReturns = mrktReturns;
		this.client = client;
	}
	
	public double assetGrowth(int years) {
		double totalAssets = 0;
		double tempAsset = 0;
		
		for(int j = 0; j < client.getAssets().size(); j++) {
			tempAsset = client.getAssets().get(j).getAmount();	
			
			for(int k = 0; k < years; k++) {
				tempAsset = tempAsset * ( 1 + client.getAssets().get(j).getYield() );
				tempAsset = tempAsset * ( 1 + marketReturns[k][1]);
			}
			totalAssets = totalAssets + tempAsset;	
		}
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
	 * Getter method for market returns.
	 * @return marketReturns
	 */
	public double[][] getMarketReturns() {
		return marketReturns;
	}

	/**
	 * Getter method for client
	 * @return client 
	 */
	public Customer getClient() {
		return client;
	}
	
	
	
}
