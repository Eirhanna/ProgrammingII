import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Separation of the customers. This class checks if the purchases from the csv
 * file have been made by existing or new customers and saves the data into two
 * separated lists based on this treaty.
 * 
 * @author Ioanna Martini
 *
 */

public class NewPurchasesSeparation extends CsvNewPurchases {

	/** Creation of a list with the new fees of the customers */
	static ArrayList<NewPurchasesSeparation> OldCustomers;
	Databaseconnection objectOfDatabaseconnectionClass;
	static CsvNewPurchases objectOfCsvNewPurchasesClass;
	// The data that we need
	static NewPurchasesSeparation newF;
	private String newName;
	private String newMonth;
	private String newFees;
	private Double newfees;
	private String newMail;
	private double nf = 0;
	String path;

	private String offeredName;

	/** Creation of a list with the new customers */
	private ArrayList<NewPurchasesSeparation> NewCustomers = new ArrayList<NewPurchasesSeparation>();

	/** Checks if the new purchases are from existing customers or not */
	public void separateCustomers() {

		OldCustomers = new ArrayList<NewPurchasesSeparation>();
		objectOfDatabaseconnectionClass = new Databaseconnection();
		objectOfCsvNewPurchasesClass = new CsvNewPurchases();

		objectOfDatabaseconnectionClass.dbconnection();
		try {
			path = (JOptionPane.showInputDialog("Please insert the path of the csv file: "));
		} catch (NullPointerException e) {
			System.exit(0);
		}
		objectOfCsvNewPurchasesClass.saveCsvData(path);

		/*
		 * Checks if the customer already exists if yes, adds his new fees to a new list
		 * if not, adds the new customer to another list
		 */
		for (int i = 0; i < CsvNewPurchases.name.size(); i++) {
			boolean found = false;
			int j = 0;
			String nameCsv = CsvNewPurchases.name.get(i);
			do {
				if (nameCsv.equals(Databaseconnection.totalFees.get(j).getName())) {
					found = true;
				}
				j = j + 1;
			} while (found == false && j < Databaseconnection.totalFees.size());

			/*
			 * if the name was found to the initial list(totalFees), it adds the customer's
			 * data to the OldCustomers list because he already exists.
			 */
			if (found == true) {
				newName = objectOfCsvNewPurchases.getName().get(i);
				newMonth = objectOfCsvNewPurchases.getMonth().get(i);
				newFees = objectOfCsvNewPurchases.getFees().get(i);
				nf = Double.parseDouble(newFees);
				newMail = objectOfCsvNewPurchases.getMail().get(i);
				newF = new NewPurchasesSeparation(newName, newMonth, nf, newMail);
				// Adds data to the proper list
				OldCustomers.add(newF);

			}
			/*
			 * if the name doesn't exist in the initial list (totalFees), this is a purchase
			 * from a new customer so it adds the customer's data to the NewCustomers list.
			 */
			else {
				newName = objectOfCsvNewPurchases.getName().get(i);
				newMonth = objectOfCsvNewPurchases.getMonth().get(i);
				newFees = objectOfCsvNewPurchases.getFees().get(i);
				newMail = objectOfCsvNewPurchases.getMail().get(i);
				newF = new NewPurchasesSeparation(newName, newMonth, newFees, newMail);
				// Adds data to the proper list
				NewCustomers.add(newF);
			}
		}
	}

	/** Creation of getters and setters */

	public ArrayList<NewPurchasesSeparation> getOldCustomers() {
		return OldCustomers;
	}

	public void setNewFees(ArrayList<NewPurchasesSeparation> newFees) {
		OldCustomers = newFees;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewMonth() {
		return newMonth;
	}

	public void setNewMonth(String newMonth) {
		this.newMonth = newMonth;
	}

	public String getNewFees() {
		return newFees;
	}

	public void setNewFees(String newFees) {
		this.newFees = newFees;
	}

	public ArrayList<NewPurchasesSeparation> getNewCustomers() {
		return NewCustomers;
	}

	public double getNf() {
		return nf;
	}

	public void setNf(double nf) {
		this.nf = nf;
	}

	public void setNewCustomers(ArrayList<NewPurchasesSeparation> newCustomers) {
		NewCustomers = newCustomers;
	}

	public String getNewMail() {
		return newMail;
	}

	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}

	public Double getNewfees() {
		return newfees;
	}

	public void setNewfees(Double newfees) {
		this.newfees = newfees;
	}

	public String getOfferedName() {
		return offeredName;
	}

	public void setOfferedName(String offeredName) {
		this.offeredName = offeredName;
	}

	// Constructor with the arguments that the list OldCustomers contains
	public NewPurchasesSeparation(String newName, String newMonth, double nf, String newMail) {
		this.newName = newName;
		this.newMonth = newMonth;
		this.nf = nf;
		this.newMail = newMail;
	}

	// Constructor with the arguments that the list NewCustomers contains
	public NewPurchasesSeparation(String newName, String newMonth, String newFees, String newMail) {
		this.newName = newName;
		this.newMonth = newMonth;
		this.newFees = newFees;
		this.newMail = newMail;
	}

	public NewPurchasesSeparation(String newName, String newMail, Double newfees) {
		this.newName = newName;
		this.newfees = newfees;
		this.newMail = newMail;
	}

	/** Default constructor */
	public NewPurchasesSeparation() {

	}

	// Constructor
	public NewPurchasesSeparation(String newName, String newMail, String newFees) {
		this.newName = newName;
		this.newMail = newMail;
		this.newFees = newFees;
	}
}
