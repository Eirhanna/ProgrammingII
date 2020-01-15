import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Unit test on CsvNewPurchases class. This class checks if the CsvNewPurchases
 * class receive the correct data from the csv file and if it saves them into
 * the proper list.
 * 
 * @author Ioanna Martini
 *
 */

public class CsvNewPurchasesTest {

	@Test
	public void test() {
		// Given
		ArrayList<String> name_example = new ArrayList<String>();
		ArrayList<String> month_example = new ArrayList<String>();
		ArrayList<String> fees_example = new ArrayList<String>();
		ArrayList<String> mail_example = new ArrayList<String>();
		name_example.add("Eleni"); // the name that the csv file example_test contains
		month_example.add("1"); // the month that the csv file example_test contains
		fees_example.add("268"); // the fees that the csv file example_test contains
		mail_example.add("Eleni@yahoo.gr"); // the mail that the csv file example_test contains
		String path = "C:\\Users\\imartini\\Documents\\example_test.csv";

		// When
		CsvNewPurchases objectOfCsvNewPurchasesClass = new CsvNewPurchases();
		objectOfCsvNewPurchasesClass.saveCsvData(path);
		String expected = name_example.get(0) + month_example.get(0) + fees_example.get(0) + mail_example.get(0);
		String input = objectOfCsvNewPurchasesClass.getName().get(0) + objectOfCsvNewPurchasesClass.getMonth().get(0)
				+ objectOfCsvNewPurchasesClass.getFees().get(0) + objectOfCsvNewPurchasesClass.getMail().get(0);

		// Then
		assertEquals(expected, input);
	}

}
