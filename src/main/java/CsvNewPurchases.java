import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Import data from csv file. This class takes the new purchases of the company
 * from a csv file, separates the name,mail,fees and the month of each purchase
 * and saves them to separate lists.
 *
 * @author Ioanna Martini
 * 
 */

public class CsvNewPurchases {

	/** Statement of the static fields */
	static ArrayList<String> name = new ArrayList<String>();
	static ArrayList<String> month = new ArrayList<String>();
	static ArrayList<String> fees = new ArrayList<String>();
	static ArrayList<String> mail = new ArrayList<String>();
	static CsvNewPurchases objectOfCsvNewPurchases = new CsvNewPurchases();

	public ArrayList<String> getName() {
		return name;
	}

	public void setName(ArrayList<String> name) {
		this.name = name;
	}

	public ArrayList<String> getMonth() {
		return month;
	}

	public void setMonth(ArrayList<String> month) {
		this.month = month;
	}

	public ArrayList<String> getFees() {
		return fees;
	}

	public void setFees(ArrayList<String> fees) {
		this.fees = fees;
	}

	public ArrayList<String> getMail() {
		return mail;
	}

	public void setMail(ArrayList<String> mail) {
		this.mail = mail;
	}

	/** Separation of the elements of each line and storage in lists */
	public void saveCsvData(String path) {
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		FileReader file = null;

		try {
			file = new FileReader(csvFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found..."); // To emfanizei sth grafikh diepafh
		}
		try {
			br = new BufferedReader(file);
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] newPurchase = line.split(cvsSplitBy);
				name.add(newPurchase[0]);
				month.add(newPurchase[1]);
				fees.add(newPurchase[2]);
				mail.add(newPurchase[3]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
