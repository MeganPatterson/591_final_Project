
public class RiskInfoCSV {
	private double RiskScore; 
	private double IGIntermFI; 
	
	
// This class will establish what each value in the arraylist means 
// (the headers of the CSV file)
	public RiskInfoCSV (double RiskScore, double IGIntermFI) {
		
	}

// This getter & setter will be used to compare against the customer supplied risk level
	public double getRiskScore() {
		return RiskScore;
	}


	public void setRiskScore(double riskScore) {
		RiskScore = riskScore;
	}

// this getter and setter will be used to evaluate the customers assets and market
	public double getIGIntermFI() {
		return IGIntermFI;
	}


	public void setIGIntermFI(double iGIntermFI) {
		IGIntermFI = iGIntermFI;
	}
	
// 
}
