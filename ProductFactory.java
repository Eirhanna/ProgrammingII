import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
/**
 * This class refers to the stock of the company.
 * It creates a map that includes the stock and finds the sell period of it.
 * @author EirhannaPapoutsi
 */
public class ProductFactory {
	/** It includes the stock of the company */
	private static Map<String,List<Product>> allProducts = new HashMap<>();
	/** Represents the demand cost for the company to sell the last's year products based on accounting terms. */
	private static double costSold;

	/** 
	 * Defines a product the company bought.
	 * It creates the stock of the company using the product's name, code, quantity, price and 
	 * the day, month, year the product was bought from the company. In addition, it includes the 
	 * sell period of the product which is the needed expected period to pass for the product to be sold.
	 * Then it adds the stock to a map called allProducts.
	 */
	public static Product purchaseOfStock(String name, String code, int quantity, int day, int month, int year, double price) {
		double sellPeriod = findSellPeriod(quantity, costSold); // findSellPeriod method is called in order to calculate and save the sell period of the product
		Product newProduct = new Product(name, code, quantity, day, month, year, price, sellPeriod);

		/*
		 * Checks if the stock the company bought already exists in the map.
		 * If yes, it modifies the proper item in order to include the new one.
		 * If not, it creates a new list which includes the fields of the stock 
		 * and then adds it to the map in a form of a new item.
		 */
		if(allProducts.containsKey(newProduct.getName())) {
			allProducts.get(newProduct.getName()).add(newProduct);
		}else {
			List<Product> value = new ArrayList<Product>();
			value.add(newProduct);
			allProducts.put(newProduct.getName(), value);
		}
		return newProduct;
	}
	
	public static Map<String,List<Product>> getAllProducts() {
		return allProducts;
	}
	
	/** 
	 * Finds the sell period of the product using its quantity and the costSold variable.
	 * Calculates the sell period based on accounting types, unless the quantity nor the costSold are equal to zero.
	 * If at least one of them is zero the sell period cannot be calculated and it will be set as zero as well.
	 */
	public static double findSellPeriod(int quantity, double costSold) {
		if(quantity != 0 && costSold != 0) {
			double ratio = costSold / quantity; // Calculation of Stock Turnover Ratio based on accounting types
			return 360 / ratio; // Calculation of stock sale period based on accounting types
		}else {
			JOptionPane.showMessageDialog(null, "Sell period cannot be calculated. Setting it to 0...");
			return 0;
		}
	}

	public static void setCostSold(double cost) {
		costSold = cost;
		JOptionPane.showMessageDialog(null, "Cost sold was insert successfully!");
		Main.loadObjects();
	}	
}