import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Figure out which customer has to take an offer by evaluating his fees and
 * the fact that he might have already taken an offer.
 * 
 * @author Vasiliki Chalkiopoulou
 *
 */

public class Customer extends NewPurchasesSeparation {

	// Customers that deserve to take an offer.
	private String offeredMail, offeredName;
	private Customer offer;
	static ArrayList<NewPurchasesSeparation> newoffered;
	static ArrayList<Double> totalfee;
	static ArrayList<Integer> firstcase;
	static ArrayList<Customer> offered = new ArrayList<Customer>();
	Databaseconnection objectOfDatabaseconnectionClass = new Databaseconnection();
	NewPurchasesSeparation objectOfNewPurchasesSeparation = new NewPurchasesSeparation();

	/**
	 * This method calculates the total fees of every old customer including those
	 * that exist in the data base.
	 */
	public void addTheNewFees() {

		// A list that contains the total fees of every old customer.
		totalfee = new ArrayList<Double>();
		// Initializing the list with the total fees of the previous year for every
		// customer.
		for (int h = 0; h < Databaseconnection.totalFees.size(); h++) {
			totalfee.add(Databaseconnection.totalFees.get(h).getT_fees());
		}

		// A list that shows with 1 the customers that reduced a lot their purchases and
		// with 0 the one's who did not.
		firstcase = new ArrayList<Integer>();
		// Initializing the list with 0.
		for (int l = 0; l < Databaseconnection.totalFees.size(); l++) {
			firstcase.add(0);
		}
		// A loop getting every customer.
		for (int i = 0; i < NewPurchasesSeparation.OldCustomers.size(); i++) {
			// Finds the position of the customer at the initial list and add his new
			// purchase to the same position
			// into the totalfee list.
			for (int k = 0; k < Databaseconnection.totalFees.size(); k++) {
				if ((NewPurchasesSeparation.OldCustomers.get(i).getNewName())
						.equals(Databaseconnection.totalFees.get(k).getName())) {
					double a = NewPurchasesSeparation.OldCustomers.get(i).getNf() + totalfee.get(k);
					totalfee.set(k, a);

					// Checks if the new purchase is smaller that the minimun purchase of the
					// previous year.
					if (NewPurchasesSeparation.OldCustomers.get(i).getNf() < Databaseconnection.totalFees.get(k)
							.getMin_fees()) {
						firstcase.set(k, 1);
					}
					break;
				}
			}
		}
	}

	/**
	 * This method ,after having the total amount of expenses of every customer
	 * finds out if a customer that has thw specific month lower expenses than the
	 * usual can take a gift if he hasn't get any till now.
	 */
	public ArrayList<NewPurchasesSeparation> findsCustomersThatDeserveAnOffer() {
		Customer object = new Customer();
		object.addTheNewFees();
		createOfferedArrayList();
		NewPurchasesSeparation newf;
		newoffered = new ArrayList<NewPurchasesSeparation>();
		boolean found = false;
		int sizeoffered = offered.size();
		// If no one has get an offer, it sends for an offer the customers that have
		// reduced a lot their purchases.
		if (sizeoffered == 0) {
			// For every customer with the firstcase=1 makes an object and adds it into the
			// list newoffered.
			for (int i = 0; i < firstcase.size(); i++) {
				if (firstcase.get(i) == 1) {
					newf = new NewPurchasesSeparation(Databaseconnection.totalFees.get(i).getName(),
							Databaseconnection.totalFees.get(i).getMail(), totalfee.get(i));
					newoffered.add(newf);
				}
			}
		}
		// Else it sends for an offer the customers that have reduced a lot their
		// purchases and that have not taken an offer before.
		else {
			for (int i = 0; i < firstcase.size(); i++) {
				if (firstcase.get(i) == 1) {
					found = false;
					for (int j = 0; j < sizeoffered; j++) {
						if ((offered.get(j).getOfferedName()).equals(Databaseconnection.totalFees.get(i).getName())) {
							found = true;
							break;
						}
					}
					if (found == false) {
						newf = new NewPurchasesSeparation(Databaseconnection.totalFees.get(i).getName(),
								Databaseconnection.totalFees.get(i).getMail(), String.valueOf(totalfee.get(i)));
						newoffered.add(newf);
					}
				}
			}
		}
		return newoffered;
	}

	/**
	 * This method helps us keep the data of the offered list every time that the
	 * program runs.
	 */
	public void createOfferedArrayList() {
		String url = "jdbc:sqlserver://195.251.249.161:1433;" + "databaseName=DB29;user=G529;password=59w495f49;";
		Connection dbcon;
		Statement stmt;
		ResultSet rs;

		/** declare ODBC connectivity */
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (java.lang.ClassNotFoundException e) {
			System.out.print("ClassNotFoundException: ");
			System.out.println(e.getMessage());
		}

		/** execute SQL statements */
		try {
			dbcon = DriverManager.getConnection(url);
			stmt = dbcon.createStatement();
			rs = stmt.executeQuery("SELECT  Name,Mail\r\n" + "FROM Java_Offered\r\n");
			while (rs.next()) {
				offeredMail = rs.getString("Mail");
				offeredName = rs.getString("Name");
				offer = new Customer(offeredName, offeredMail);
				offered.add(offer);
			}
			rs.close();
			stmt.close();
			dbcon.close();
		} catch (SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		}

	}

	// Default constructor.
	public Customer() {
	}

	public ArrayList<NewPurchasesSeparation> getNewoffered() {
		return newoffered;
	}

	public static void setNewoffered(ArrayList<NewPurchasesSeparation> newoffered) {
		Customer.newoffered = newoffered;
	}

	public ArrayList<Customer> getOffered() {
		return offered;
	}

	public void setOffered(ArrayList<Customer> offered) {
		Customer.offered = offered;
	}

	public String getOfferedMail() {
		return offeredMail;
	}

	public void setOfferedMail(String offeredmail) {
		this.offeredMail = offeredmail;
	}

	public String getOfferedName() {
		return offeredName;
	}

	public void setOfferedName(String offeredname) {
		this.offeredName = offeredname;
	}

	// Constructor.
	public Customer(String offeredName, String offeredMail) {
		this.offeredName = offeredName;
		this.offeredMail = offeredMail;
	}
}
