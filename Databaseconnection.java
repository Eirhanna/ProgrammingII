import java.util.*;
import java.sql.*;

/**
 * Connection with the data base. This class connects the program with the data
 * base and then saves the data to a list.
 * 
 * @author Ioanna Martini
 *
 */

public class Databaseconnection {
	// C:\\Users\\Ειρηάννα\\Downloads\\jan18.csv

	/** The list that the data will be saved */
	private Databaseconnection totalF;
	public static ArrayList<Databaseconnection> totalFees = new ArrayList<Databaseconnection>();
	private Double t_fees;
	private Double min_fees;
	private String mail;
	private String name;

	// Connection with the sql.server
	public void dbconnection() {

		String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" + "databaseName=DB29;user=G529;password=59w495f49;";
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
			rs = stmt.executeQuery("SELECT  min(Fees) as minf, sum(Fees) as sumf, a.Mail, a.Name\r\n"
					+ "FROM Java_Agores as a, Java_Customers as b \r\n"
					+ "WHERE a.Name = b.Name AND a.Mail = b.Mail\r\n" + "GROUP BY a.Name,a.Mail");
			while (rs.next()) {
				min_fees = rs.getDouble("minf");
				t_fees = rs.getDouble("sumf");
				mail = rs.getString("Mail");
				name = rs.getString("Name");
				totalF = new Databaseconnection(t_fees, min_fees, mail, name);
				totalFees.add(totalF);
			}

			rs.close();
			stmt.close();
			dbcon.close();

		} catch (SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		}

	}

	/** Creation of getters and setters */
	public Databaseconnection getTotalF() {
		return totalF;
	}

	public void setTotalF(Databaseconnection totalF) {
		this.totalF = totalF;
	}

	public Double getT_fees() {
		return t_fees;
	}

	public void setT_fees(Double t_fees) {
		this.t_fees = t_fees;
	}

	public Double getMin_fees() {
		return min_fees;
	}

	public void setMin_fees(Double min_fees) {
		this.min_fees = min_fees;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Databaseconnection> getTotalFees() {
		return totalFees;
	}

	/** Constructor with the arguments that the list contains */
	public Databaseconnection(Double t_fees, Double min_fees, String mail, String name) {
		this.t_fees = t_fees;
		this.min_fees = min_fees;
		this.mail = mail;
		this.name = name;
		// totalFees.add(this);
	}

	/** default constructor */
	public Databaseconnection() {

	}
}
