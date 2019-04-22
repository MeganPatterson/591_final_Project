import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class csvTest {

	@Test
	void testAsArray() {
		ArrayList <String> result = RiskLevel.readAsList("riskAllocations.csv");
		assertEquals(result.size(), 100); 
		
	}

	
	
}
