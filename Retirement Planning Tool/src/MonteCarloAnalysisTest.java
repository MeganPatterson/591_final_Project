import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for the MonteCarloAnalysis Class
 * @author Scott Hitchcock & Megan Patterson
 *
 */
class MonteCarloAnalysisTest {

	//Test to make sure AssetAllocation method is computing correctly
	@Test
	void testGetAssetAllocation() {
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Assets equityAsset = new Assets(1,10000,.04);
		Assets fiAsset = new Assets(2,20000,.05);
		Assets reAsset = new Assets(3,30000,.02);
		Assets otherAsset = new Assets(4,40000,.03);
		assets.add(equityAsset);
		assets.add(fiAsset);
		assets.add(reAsset);
		assets.add(otherAsset);
	
		MonteCarloAnalysis test = new MonteCarloAnalysis();
		assertEquals(test.getAssetAllocation(assets).get(1), .10, .001);
		assertEquals(test.getAssetAllocation(assets).get(2), .20, .001);
		assertEquals(test.getAssetAllocation(assets).get(3), .30, .001);
		assertEquals(test.getAssetAllocation(assets).get(4), .40, .001);
	}
	
	//Test to make sure AssetAllocation method is computing correctly when
	//I have assets with amounts of 0
	@Test
	void testGetAssetAllocationEmptyAssets() {
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Assets equityAsset = new Assets(1,50000,.04);
		Assets fiAsset = new Assets(2,0,0.0);
		Assets reAsset = new Assets(3,75000,.02);
		Assets otherAsset = new Assets(4,0,0.0);
		assets.add(equityAsset);
		assets.add(fiAsset);
		assets.add(reAsset);
		assets.add(otherAsset);
	
		MonteCarloAnalysis test = new MonteCarloAnalysis();
		assertEquals(test.getAssetAllocation(assets).get(1), .4, .001);
		assertEquals(test.getAssetAllocation(assets).get(2), 0.0, .001);
		assertEquals(test.getAssetAllocation(assets).get(3), .60, .001);
		assertEquals(test.getAssetAllocation(assets).get(4), 0.0, .001);
	}
	
	//Test to make sure asset allocation works when there are negatives
	//and zeroes involved
	@Test
	void testGetAssetAllocationNegative() { 
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Assets equityAsset = new Assets(1,-50000,.04);
		Assets fiAsset = new Assets(2,0,.00);
		Assets reAsset = new Assets(3,-30000,.02);
		Assets otherAsset = new Assets(4,-20000,.03);
		assets.add(equityAsset);
		assets.add(fiAsset);
		assets.add(reAsset);
		assets.add(otherAsset);
		
		MonteCarloAnalysis test = new MonteCarloAnalysis();
		assertEquals(test.getAssetAllocation(assets).get(1), .5, .001);
		assertEquals(test.getAssetAllocation(assets).get(2), 0.0, .001);
		assertEquals(test.getAssetAllocation(assets).get(3), .3, .001);
		assertEquals(test.getAssetAllocation(assets).get(4), .2, .001);
	}
	
	//Testing to make sure that we are importing the entire market data file
	@Test
	void testImportMarketData() {
		MonteCarloAnalysis test = new MonteCarloAnalysis();
		test.importMarketData();
		assertEquals(test.getMrktReturns().size(),39);
	}
	
	//Check to see that the market data is loading into the correct spots
	@Test
	void testMarketDataCorrect() {
		MonteCarloAnalysis test = new MonteCarloAnalysis();
		test.importMarketData();
		assertEquals(test.getMrktReturns().get(7).getYear(),1987);
		assertEquals(test.getMrktReturns().get(7).getStockReturn(),.0525);
		assertEquals(test.getMrktReturns().get(7).getBondReturn(),.0275);
	}
	
	//Testing the yearly savings for a year before the client is retired
	@Test
	void testComputeYearlySavings() {
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Customer client = new Customer("scott", 35, 55, .25, 90, 150000,75000, 25000, 60000,1000000, assets);
		MonteCarloAnalysis test = new MonteCarloAnalysis(.035,client);
		double savings = test.computeYearlySavings(36);
		assertEquals(savings,38812.5, .001);
	}
	
	//Testing the yearly savings a 2nd time to make sure it works once the client has passed retirement age
	@Test
	void testComputeYearlySavingsRetired() {
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Customer client = new Customer("scott", 35, 55, .25, 90, 150000,75000, 25000, 60000,1000000, assets);
		MonteCarloAnalysis test = new MonteCarloAnalysis(.035,client);
		double savings = test.computeYearlySavings(60);
		assertEquals(savings,-82713.57445, .001);
	}
	
	//I want to test that my probability of success is calculating roughly correct.
	//Since we are running a monte carlo simulation this number will differ slightly each time
	//But I used an online retirement simulator to get a number that I think this should be close
	//to given my inputs
	@Test
	void testProbabilityOfSuccess(){
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Assets equityAsset = new Assets(1,75000,0.0);
		Assets fiAsset = new Assets(2,75000,0.0);
		Assets reAsset = new Assets(3,0,0.0);
		Assets otherAsset = new Assets(4,0,0.0);
		assets.add(equityAsset);
		assets.add(fiAsset);
		assets.add(reAsset);
		assets.add(otherAsset);
		Customer client = new Customer("scott", 35, 55, 0.0, 90, 10000,0, 10000, 100000,1000000, assets);
		
		MonteCarloAnalysis test = new MonteCarloAnalysis(.035,client);
		test.monteCarloSimulation(100);
		assertEquals(test.getProbabilityOfSuccess(), .20, .1);
	}
	
	//Testing to make sure that we are correctly computing the asset growth 
	//for the clients entire life.  Am unable to test specific amounts each year since
	//the growth depends on a random generated return
	@Test
	void testAssetGrowth() {
		ArrayList<Assets> assets = new ArrayList<Assets>();
		Assets equityAsset = new Assets(1,75000,0.0);
		Assets fiAsset = new Assets(2,75000,0.0);
		Assets reAsset = new Assets(3,0,0.0);
		Assets otherAsset = new Assets(4,0,0.0);
		assets.add(equityAsset);
		assets.add(fiAsset);
		assets.add(reAsset);
		assets.add(otherAsset);
		Customer client = new Customer("scott", 35, 55, 0.0, 90, 10000,0, 10000, 100000,1000000, assets);
		
		MonteCarloAnalysis test = new MonteCarloAnalysis(.035,client);
		HashMap<Integer,Double >results = new HashMap<>();
		results = test.assetGrowth();
		
		//My HashMap size should be equal to the difference between clients age of death and their current age.
		//Then that adjusted by adding 1 for their final year of death being included
		//Then adjusted by adding 20 because we want to run the calculations past the date
		//in case they live longer
		assertEquals(results.size(),test.getClient().getAgeOfDeath()-test.getClient().getCurrentAge() + 1 + 20);
	}
}
