import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GiftsTest {
	private Gifts products;
	private Gifts customers;
	private Gifts gifts;
	
	/*@BeforeEach // runs before every unit test
	public void setUp() {
		products = new Gifts();
	}*/
	
	@Test
	public void testfindNumberOfProductsAsGifts() {
		// Given 
		ArrayList<Product> prod = new ArrayList<>();
		Product product = new Product("Test", "code", 2, 15, 3, 2019, 5.05, 10.4);
		Product product1 = new Product("Test", "code", 10, 4, 6, 2019, 25.00, 25.3);
		Product product2 = new Product("Test", "code", 5, 4, 6, 2019, 12.021, 15.3);
		
		prod.add(product1);
		prod.add(product2);
		prod.add(product);
		
		int expected = 17;
		
		// When
		int input = products.findNumberOfProductsAsGifts(prod);
		
		// Then
		assertEquals(input, expected);
	}
	
	@Test
	public void testfindNumberOfGifts() {
		// Given 
		int numberOfPoductsAsGifts = 20;
		int sizeOfnewoffered = 15;
		int expected = 15;
		
		// When
		int input = gifts.findNumberOfGifts(sizeOfnewoffered);
		
		// Then
		assertEquals(input, expected);
	}
	
	@Test
	public void testsortMyListBasedOnThePrice() {
		// Given 
		ArrayList<Product> input = new ArrayList<>();
		ArrayList<Product> expected = new ArrayList<>();
		Product product = new Product("Test", "code", 2, 15, 3, 2019, 5.05, 10.4);
		Product product1 = new Product("Test", "code", 12, 4, 6, 2019, 25.00, 25.3);
		Product product2 = new Product("Test", "code", 22, 4, 6, 2019, 12.021, 15.3);
		
		input.add(product);
		input.add(product1);
		input.add(product2);
		
		expected.add(product1);
		expected.add(product2);
		expected.add(product);
		
		
		// When
		products.sortMyListBasedOnThePrice(input);
		System.out.println(input);
		
		
		// Then
		assertEquals(input,expected);
	}
	
	@Test
	public void testsortMyListBasedOnTheTotalFees() {
		// Given 
		ArrayList<NewPurchasesSeparation> input = new ArrayList<>();
		ArrayList<NewPurchasesSeparation> expected = new ArrayList<>();
		NewPurchasesSeparation customer = new NewPurchasesSeparation("Test", "mail", "100");
		NewPurchasesSeparation customer1 = new NewPurchasesSeparation("Test1", "mail", "5000");
		NewPurchasesSeparation customer2 = new NewPurchasesSeparation("Test", "mail", "300");
		
		input.add(customer);
		input.add(customer1);
		input.add(customer2);
		
		expected.add(customer1);
		expected.add(customer2);
		expected.add(customer);
		System.out.println(input);
		// When
		customers.sortMyListBasedOnTheTotalFees(input);
		System.out.println(input);
		System.out.println(expected);
		// Then
		assertEquals(input,expected);
	}
	
	/*@Test
	public void testfindGiftsReceivers() {
		// Given 
	
		ArrayList<NewPurchasesSeparation> input = new ArrayList<>();
		NewPurchasesSeparation customer = new NewPurchasesSeparation("Test", "mail", "100");
		NewPurchasesSeparation customer1 = new NewPurchasesSeparation("Test1", "mail", "5000");
		NewPurchasesSeparation customer2 = new NewPurchasesSeparation("Test", "mail", "300");
		
		input.add(customer1);
		input.add(customer2);
		input.add(customer);
		
		ArrayList<Product> input2= new ArrayList<>();
		Product product = new Product("Test", "code", 1, 15, 3, 2019, 5.05, 10.4);
		Product product1 = new Product("Test", "code", 5, 4, 6, 2019, 25.00, 25.3);
		Product product2 = new Product("Test", "code", 3, 4, 6, 2019, 12.021, 15.3);
		
		input2.add(product1);
		input2.add(product2);
		input2.add(product);
		
		int numberOfGifts = 6;
		
		
		
		// When
	
		// Then
		assertEquals(input,expected);
	}*/
	
}

