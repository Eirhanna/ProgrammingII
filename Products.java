package javaProg2;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Products {
	
	/**
	 * It moves products that have surpassed the sell period from the list product to the list productsPastTheSellPeriod 
	 * @return a list with the products over the sell period 
	 */
	public ArrayList<Product> createListofProductsPassedTheSellPeriod() {
		ArrayList <Product> productsPassedTheSellPeriod = new ArrayList <Product>(); // productsPastTheSellPeriod the list contains the products that have passed the sell period
		for (List<Product> products : ProductFactory.getAllProducts().values()) { // runs the values of all products
			for (Product product : products) { // if the product is sold for more days than the period of sale its moved to the list with the expired products
				if (findDifferencebetweenTwoDates(product) - product.getSellPeriod() > 0) {
					productsPassedTheSellPeriod.add(product); // the product is copied to the list with the expired products
					ProductFactory.getAllProducts().remove(product);
	                        }
	                  }
		}
        return productsPassedTheSellPeriod; //the list with the leftover products is returned
	} 

	/**
	 * It finds the period has passed between two dates.
	 * It calculates the current date and finds the days passed from the date the given stock was bought 
	 * using classes and  methods from the java API.
	 * @param input a product with reference type Product
	 * @return a period between two days
	 */
	protected Integer findDifferencebetweenTwoDates(Product input) {
		LocalDate now = LocalDate.now(); // Finds the current date
	    LocalDate dateThen = LocalDate.of(input.getYear(), input.getMonth(), input.getMonth()); // Changes the date of the input into an object of LocalDate reference type
	    /* Calls the static method between in order to estimate the days passed between the two parameters given 
	     * and saves it to the variable named period 
	     */
	    Period period = Period.between(now, dateThen); 
	    return Math.abs(period.getDays());
	}
	
	/**
	 * It implements a purchase of a customer.
	 * Given a name of a product, it reduces the number of stock according to the given quantity.
	 * @param name the name of a product a customer buys
	 * @param quantity the quantity of a product a customer buys
	 */
     public void implementsCustomersPurchase(String name, int quantity) {
    	 List<Product> productsOfInterest = ProductFactory.getAllProducts().get(name); // 
    	 long sum = 0;// represents the total stock of the product 
    	 
    	 try {
    		 sum = productsOfInterest.stream().mapToInt(Product::getQuantity).sum(); // calculation of total amount of the product 
    	 } catch (NullPointerException e) {
    		 System.err.println("Exception caught: " + e);
    		 
    	 }
    	 
    	 // Return a message in case the demand quantity is more than the stock.
    	 if (quantity > sum) {
    		 System.out.println("Sorry! :( We do not have so many items of this product.");
    		 return;
    	 }
    	 
    	 // Calls a method to sort the list in decreasing order
    	 sortMyListBasedOnSellPeriod(productsOfInterest);
    	 
    	 // Calls a method to reduce correctly the stock of the product that was sold.
    	 reduceStocksQuantity(quantity,productsOfInterest);
	}
     /**
      * It sorts the given list. 
      * Based on the sell period of the elements the list contains it sorts them in decreasing order.
      * @param listToSort the list we want to sort
      */
     protected void sortMyListBasedOnSellPeriod(List<Product> listToSort) {
    	 Collections.sort(listToSort, new Comparator<Product>() {
    		 public int compare(Product one, Product other) {
    			 return other.getSellPeriod().compareTo(one.getSellPeriod());
    		 }
    	 }); 
     }
     
     /**
      * Given a product quantity and a list with products in stock, it retrospectively subtracts the quantity from the stock. 
      * @param quantity the quantity which has been sold and needs to be reduced from the stock
      * @param productList the list of products in stock 
      */
     protected void reduceStocksQuantity(int quantity, List<Product> productList) {
    	 final int INDEX = 0; /* The index will always be zero
     	                       * because in order to move on to the next item of the list 
 							   * a needed condition is to have all previous elements of the zero position removed
 							   * and the rest of them moved to the left
 							   */
    	 if (quantity < productList.get(INDEX).getQuantity()) {
     		 // If the quantity requested is less than that of the first cell, remove it from the existing quantity
    		 productList.get(INDEX).setQuantity(productList.get(INDEX).getQuantity() - quantity);
     	 } else if (quantity == productList.get(INDEX).getQuantity()) {
 			 // If the quantity requested is equal to the existing quantity just remove the existing quantity 
     		productList.remove(INDEX);
 		 }  else {
 			 // if quantity requested outweighs the existing quantity remove the existing and reduce the requested
 			quantity = quantity - productList.get(INDEX).getQuantity();
 			productList.remove(INDEX);
 			reduceStocksQuantity(quantity,productList); // calls quantity() method retrospectively to reduce the stock's quantity
 	     }
      }
}
