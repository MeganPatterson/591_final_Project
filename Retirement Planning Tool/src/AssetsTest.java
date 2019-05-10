import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit Test Suite for the Assets class
 * @author Scott Hitchcock & Megan Patterson
 *
 */
class AssetsTest {

	//test that our asset is correctly compounding over a certain amount of years
	@Test
	void testCompoundAsset() {
		Assets testAsset = new Assets(1, 100000, .10);
		testAsset.compoundAsset(7);
		assertEquals(testAsset.getAmount(), 194871.71, .01);
	}

}
