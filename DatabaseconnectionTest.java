package javaProg2;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit test on Databaseconnection class. This class checks if the
 * Databaseconnection class receive the correct data from the data base and if
 * it saves them into the "totalFees" list.
 * 
 * @author Ioanna Martini
 *
 */

public class DatabaseconnectionTest {

	/** Statement of the fields */
	private Double t_fees;
	private Double min_fees;
	private String mail;
	private String name;

	@Test
	public void test() {
		// Given
		t_fees = 5251.55; //The first total fee that the query returns in the data base
		min_fees = 405.80; //The first minimum fee that the query returns in the data base
		mail = "AntonisK@hotmail.com"; //The first mail that the query returns in the data base
		name = "Antonis Kuriakou"; //The first name that the query returns in the data base
		Databaseconnection objectOfDatabaseconnectionClass = new Databaseconnection();

		// When
		
		objectOfDatabaseconnectionClass.dbconnection();
		String name1 = objectOfDatabaseconnectionClass.getTotalFees().get(0).getName(); // It returns the first name that the list "totalFees" contains.
		String mail1 = objectOfDatabaseconnectionClass.getTotalFees().get(0).getMail(); // It returns the first mail that the list "totalFees" contains.
		Double min_fees1 = objectOfDatabaseconnectionClass.getTotalFees().get(0).getMin_fees(); // It returns the first minimum fee that the list "totalFees" contains.
		Double t_fees1 = objectOfDatabaseconnectionClass.getTotalFees().get(0).getT_fees(); // It returns the first summary of fees that the list "totalFees" contains.
		String input = name1 + mail1 + min_fees1 + t_fees1;
		String expected = name + mail + min_fees + t_fees;

		// Then
		assertEquals(expected, input);

	}

}
