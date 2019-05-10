import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for Customer class
 * @author Scott Hitchcock & Megan Patterson
 *
 */
class CustomerTest {

	@Test
	void testCustomerEquals() {
		ArrayList<Assets> assets1 = new ArrayList<>();
		ArrayList<Assets> assets2 = new ArrayList<>();
		
		Customer client1 = new Customer("Scott", 35, 55, .25, 90, 150000, 25000, 10000, 25000,
				1000000, assets1);
		Customer client2 = new Customer("Scott", 35, 55, .25, 90, 150000, 75000, 90000, 45000,
				20, assets2);
		Customer client3 = new Customer("Scott", 35, 60, .25, 90, 150000, 75000, 90000, 45000,
				20, assets2);
		
		
		assertEquals(client1.equals(client2), true);
		assertEquals(client3.equals(client1), false);	
	}
}
