
public class Assets {
	private String assetType;  //Type of asset, for example cash/stocks/bonds/house/debt
	private double amount; //the amount owned of the asset
	private double yield;  //Expressed in percentage form, what does the asset yield. Example: .04 for 4%
	
	/**
	 * Constructs a blank Asset object and defaults the variables 
	 */
	public Assets() {
		this.assetType = "";
		this.amount = 0.0;
		this.yield = 0.0;
	}
	
	/**
	 * Constructs a new Asset objects and sets the instance variables equal to the values that we
	 * are passed.
	 * @param type  Type of asset
	 * @param amt   The amount of the asset that is owned
	 * @param yld   The annual yield of the assets in decimal format. .05 = 5%
	 */
	public Assets(String type, double amt, double yld) {
		this.assetType = type;
		this.amount = amt;
		this.yield = yld;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getYield() {
		return yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	
	
}
