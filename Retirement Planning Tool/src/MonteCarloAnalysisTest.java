import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MonteCarloAnalysisTest {

	@Test
	void testAssetGrowth() {
		double[][] marketReturns = new double[3][2];
		marketReturns[0][0] = 1;
		marketReturns[0][1] = .10;
		marketReturns[1][0] = 2;
		marketReturns[1][1] = .15;
		marketReturns[2][0] = 3;
		marketReturns[2][1] = -.10;
		
		double inflationRate = .02;
		
		Assets stock1 = new Assets("Stocks", 500000, .04);
		Assets stock2 = new Assets("Stocks", 200000, .1);
		Assets stock3 = new Assets("Stocks", 100000, .02);

		ArrayList<Assets> testAssets = new ArrayList<>();
		testAssets.add(stock1);
		testAssets.add(stock2);
		testAssets.add(stock3);
		
		ArrayList<Debt>	testDebts = new ArrayList<>();
		
		Customer johnDoe = new Customer("John Doe", 50, 70, .25, 55, 90, 75000, 50000, 25000, 20000, 1000000, testAssets, testDebts);
				
		MonteCarloAnalysis test = new MonteCarloAnalysis(inflationRate, marketReturns, johnDoe);
		System.out.println(test.assetGrowth(3));
		assertEquals(Math.round(test.assetGrowth(3)),  1064216);
	}

}
