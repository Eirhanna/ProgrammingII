**
 * JavaMail accepts three String[] arrays. The first arrays contains mails,
 * the second names and the third products.
 * Then proceeds to send mails with the sendMail method of 
 * the SendEmail class.
 * <p>
 * JavaMail extends the Thread class.
 * @author vpsoma
 */

public class JavaMail extends Thread {
	private String[] partOfClients;
	private String[] partOfNames;
	private String[] partOfProducts;
	//Initializes a SendMail object.
	SendEmail objectOfSendEmailClass = new SendEmail();
	/**
	 * Initializes the three arrays.
	 */
	public JavaMail(String[] partOfClients, String[] partOfNames, String[] partOfProducts) {
		super();
		this.partOfClients = partOfClients;
		this.partOfNames = partOfNames;
		this.partOfProducts = partOfProducts;
	}
	/**
	 * This method uses the sendMail method of the SendEmail class 
	 * with the three arrays as parameters.
	 * 
	 * @param partOfClients a String array of email addresses.
	 * 		  partOfNames   a String array of names.
	 * 		  partOfProducts a String array of products.
	 * @returns Nothing.
	 * @throws InterruptedException and Exception.
	 * @see InterruptedException and Exception.
	 */
	public void run(){
    	try {
    		//Calls the sendMail method in order to send mails.
    		objectOfSendEmailClass.sendMail(partOfClients, partOfNames, partOfProducts);
    	}catch(InterruptedException e) {
    		System.out.println("An interruption occured.");
    	} catch(Exception e) {
    		System.out.println("An exception occured.");
    	}
	}
}
