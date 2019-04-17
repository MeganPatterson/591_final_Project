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

	public double getInflationRate() {
		return inflationRate;
	}

	public double[][] getMarketReturns() {
		return marketReturns;
	}

	public Customer getClient() {
		return client;
	}
	
	
	
}
