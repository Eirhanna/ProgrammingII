import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductsTest {

	private Products products;
	
	@BeforeEach // runs before every unit test
	public void setUp() {
		products = new Products();
	}
	
	@Test
	public void testFindDays() {
		
		// Given
		int day = 1, month = 1, year = 2020;
		long expected = 14;

		//When
	    LocalDate now = LocalDate.now();
	    LocalDate dateThen = LocalDate.of(year, month, day);
	    Period period = Period.between(now, dateThen);
	
		//Then
		assertEquals(Math.abs(period.getDays()), expected);
	}
	
	@Test
	public void testMySortingMethod() {
		// Given 
		List<Product> input = new ArrayList<>();
		List<Product> expected = new ArrayList<>();
		Product product = new Product("Test", "code", 2, 15, 3, 2019, 12.021, 10.4);
		Product product1 = new Product("Test", "code", 12, 4, 6, 2019, 12.021, 25.3);
		Product product2 = new Product("Test", "code", 22, 4, 6, 2019, 12.021, 15.3);
		
		input.add(product);
		input.add(product1);
		input.add(product2);
		
		expected.add(product1);
		expected.add(product2);
		expected.add(product);
		System.out.println(input);
		// When
		products.sortMyListBasedOnSellPeriod(input);

		System.out.println(input);
		System.out.println(expected);
		// Then
		assertEquals(input,expected);
	}
	
	@Test
	public void reduceProductsQuantityTestCase1() { 
		
		//If the requested quantity is less than the quantity of the first product in the list
		//Given
		List<Product> input = new ArrayList<>();
		List<Product> expected = new ArrayList<>();
		int quantity = 5;
		
		Product product = new Product("Test", "code", 22, 15, 3, 2019, 12.021, 25.4);
		Product product1 = new Product("Test", "code", 12, 4, 6, 2019, 12.021, 15.3);
		Product product2 = new Product("Test", "code", 20, 4, 8, 2019, 12.021, 10.3);
		Product product3 = new Product("Test", "code", 22 - 5, 15, 3, 2019, 12.021, 25.4);
		
		input.add(product);
		input.add(product1);
		input.add(product2); // List input is already sorted based on sell period because of the way it was made
		
		expected.add(product3);
		expected.add(product1);
		expected.add(product2);
		
		//When
		products.reduceStocksQuantity(quantity, input );
		System.out.println(input);
		System.out.println(expected);
		
		//Then
		assertEquals(input.toString(), expected.toString());
	}
	
	@Test
	public void reduceProductsQuantityTestCase2() {
		//If the requested quantity is equal to the quantity of the first product in the list
		//Given
		List<Product> input = new ArrayList<>();
		List<Product> expected = new ArrayList<>();
		int quantity = 22;
		
		Product product = new Product("Test", "code", 22, 15, 3, 2019, 12.021, 25.4);
		Product product1 = new Product("Test", "code", 12, 4, 6, 2019, 12.021, 15.3);
		Product product2 = new Product("Test", "code", 20, 4, 8, 2019, 12.021, 10.3);
		
		input.add(product);
		input.add(product1);
		input.add(product2);// List input is already sorted based on sell period because of the way it was made
		
		expected.add(product1);
		expected.add(product2);
		
		//When
		products.reduceStocksQuantity(quantity, input );
		System.out.println(input);
		System.out.println(expected);
		
		//Then
		assertEquals(input.toString(), expected.toString());
	}
	
	@Test
	public void reduceProductsQuantityTestCase3() {
		/* If the requested quantity is more than the quantity of the first product in the list 
		 * and the method will be called retrospectively
		 */
		//Given
		List<Product> input = new ArrayList<>();
		List<Product> expected = new ArrayList<>();
		int quantity = 32;
		
		Product product = new Product("Test", "code", 22, 15, 3, 2019, 12.021, 25.4);
		Product product1 = new Product("Test", "code", 12, 4, 6, 2019, 12.021, 15.3);
		Product product2 = new Product("Test", "code", 20, 4, 8, 2019, 12.021, 10.3);
		Product product3 = new Product("Test", "code", 2, 4, 6, 2019, 12.021, 15.3);
		
		input.add(product);
		input.add(product1);
		input.add(product2);// List input is already sorted based on sell period because of the way it was made
		
		expected.add(product3);
		expected.add(product2);
		
		//When
		products.reduceStocksQuantity(quantity, input );
		System.out.println(input);
		System.out.println(expected);
		
		//Then
		assertEquals(input.toString(), expected.toString());
	}
}
