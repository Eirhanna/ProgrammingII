package com.progII.gifthub;

/**
 * 
 * Creates an object that contains three sorted arrays, one with the names of
 * the clients, one with the mails of the clients and one with the products to
 * be given as presents
 * 
 * @author Katerina Dimatou
 *
 */

public class InfoMail {
	private String[] namesOfCustomersForGifts;
	private String[] mailsOfCustomersForGifts;
	private String[] namesOfProductsAsGifts;

	/**
	 * Constructor
	 * 
	 * @param mailsOfCustomersForGifts
	 * @param namesOfCustomersForGifts
	 * @param namesOfProductsAsGifts
	 */

	public InfoMail(String[] mailsOfCustomersForGifts, String[] namesOfCustomersForGifts,
			String[] namesOfProductsAsGifts) {
		this.setMailsOfCustomersForGifts(mailsOfCustomersForGifts);
		this.setNamesOfCustomersForGifts(namesOfCustomersForGifts);
		this.setNamesOfProductsAsGifts(namesOfProductsAsGifts);
	}

	/**
	 * Below are generated getters and setters of every array of information
	 */

	public String[] getNamesOfCustomersForGifts() {
		return namesOfCustomersForGifts;
	}

	public void setNamesOfCustomersForGifts(String[] namesOfCustomersForGifts) {
		this.namesOfCustomersForGifts = namesOfCustomersForGifts;
	}

	public String[] getMailsOfCustomersForGifts() {
		return mailsOfCustomersForGifts;
	}

	public void setMailsOfCustomersForGifts(String[] mailsOfCustomersForGifts) {
		this.mailsOfCustomersForGifts = mailsOfCustomersForGifts;
	}

	public String[] getNamesOfProductsAsGifts() {
		return namesOfProductsAsGifts;
	}

	public void setNamesOfProductsAsGifts(String[] namesOfProductsAsGifts) {
		this.namesOfProductsAsGifts = namesOfProductsAsGifts;
	}
}
