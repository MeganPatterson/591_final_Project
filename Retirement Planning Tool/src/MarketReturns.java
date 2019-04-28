
public class MarketReturns {
	private int year;
	private double bondReturn;
	private double stockReturn;
	
	public MarketReturns(int year, double bondReturn, double stockReturn) {
		this.year = year;
		this.bondReturn = bondReturn;
		this.stockReturn = stockReturn;
	}

	public int getYear() {
		return year;
	}

	public double getBondReturn() {
		return bondReturn;
	}

	public double getStockReturn() {
		return stockReturn;
	}
	
	

}
