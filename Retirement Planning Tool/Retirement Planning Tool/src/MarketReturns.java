
public class MarketReturns {
	private int year;  //holds the year of the return
	private double bondReturn;  //the bond return for the year in decimal format
	private double stockReturn;  //the stock return for the year in decimal format
	
	/**
	 * Constructor for the market returns class.  It creates a new instance of the class
	 * and sets the instance variables of the class equal to the variables that we are passed in.
	 * @param year  The year the market return is from	
	 * @param bondReturn  The yearly bond return expressed in decimal form
	 * @param stockReturn  The yearly stock return expressed in decimal form.
	 */
	public MarketReturns(int year, double bondReturn, double stockReturn) {
		this.year = year;
		this.bondReturn = bondReturn;
		this.stockReturn = stockReturn;
	}

	/**
	 * Getter method for the year variable.
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Getter method for the bondReturn variable
	 * @return bondReturn
	 */
	public double getBondReturn() {
		return bondReturn;
	}
	
	/**
	 * Getter method for the stockReturn variable
	 * @return stockReturn
	 */
	public double getStockReturn() {
		return stockReturn;
	}
}
