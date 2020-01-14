/**
 * Creates a product the company sells to customers
 * @author vpsoma
 */
public class Product {
private String name;
	private String code;
	private int quantity;
	private int day;
	private int month;
	private int year;
	private Double price;
	private Double sellPeriod;
	
	/**
	 * The product's constructor
	 * @param name the name of the product
	 * @param code the code of the product
	 * @param quantity the quantity of the product the company buys from the suppliers
	 * @param day the day the product was bought
	 * @param month the month the product was bought
	 * @param year the year the product was bought
	 * @param price the price of the product
	 * @param sellPeriod the needed period for the company in order to sell the product based on accounting terms 
	 */
	public Product(String name, String code, int quantity, int day, int month, int year, Double price, Double sellPeriod) {
=======
import java.util.ArrayList;

public class Product {
	private String name;
	private String code;
	private double quantity;
	private int day;
	private int month;
	private int year;
	private double price;
	private double sellPeriod;
	private static double c = 0;
	double kta;
	ArrayList<Product> prod = new ArrayList<Product>();
	ArrayList<Double> sP = new ArrayList<Double>();
	public static final void costSold(double i) { /* called in main method with scanner */
		c = i; 
	}
	private static final double COST_SOLD = c;
	public Product(String name, String code, double quantity, int day, int month, int year, double price) {
>>>>>>> dc8e93ba4bbb96085fdf07afc6829915a569fa14
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.day = day;
		this.month = month;
		this.year = year;
		this.price = price;
<<<<<<< HEAD
		this.sellPeriod = sellPeriod;
	}
	
	/**
	 * Below are generated getters and setters of every field of the product
	 */
  public Double getPrice() {
	return price;
  }

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Double getSellPeriod() {
		return sellPeriod;
	}

	public void setSellPeriod(Double sellPeriod) {
		this.sellPeriod = sellPeriod;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", code=" + code + ", quantity=" + quantity + ", day=" + day + ", month="
				+ month + ", year=" + year + ", price=" + price + ", sellPeriod=" + sellPeriod + "]";
	}	
}
=======
		prod.add(this);
	}
	public Product(String name, String code, double quantity, double price) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.price = price;
	} //syzhthsh me klash an to thelun pernaw ta proioda sthn panw lista
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public double findSellPeriod() {
		try {
		kta = COST_SOLD / quantity;
		sellPeriod = 360 / kta;
		} catch(ArithmeticException e) {
			System.out.print("sellPeriod cannot be calculated");
			sellPeriod = 0;
		}
		sP.add(sellPeriod);
		return sellPeriod;
	}
}
