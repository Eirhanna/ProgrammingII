package ProgrammingII;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Updated.ProductFactory;

public class ProfuctFactoryTest {
	
	private ProductFactory productFactory;
	
	@BeforeEach 
	public void setUp() {
		productFactory = new ProductFactory();
	}

	@Test
	public void findSellPeriodTest() {
		
		//Given
		int quantity = 40;
		double costSold = 576;
		double expected = 360 / (costSold / quantity);
		
		//When
		double result = productFactory.findSellPeriod(quantity, costSold);
		
		//Then
		assertEquals(result, expected);
	}
}
