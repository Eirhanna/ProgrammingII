import java.util.Arrays;

/**
 * EmailThreads splits arrays into smaller ones and creates threads 
 * according to the JavaMail class and starts them.
 * @author vpsoma
 */


public class EmailThreads {
	/**
	 * This method gets a number of mails that need to get sent
	 * and splits the arrays it gets according to the numOfClients.
	 * Then, it creates new JavaMail threads and starts them.
	 * @param numOfClients an integer 
	 * 		  Clients 	   a String array of email addresses
	 * 		  Names  	   a String array of names
	 * 		  Products	   a String array of email addresses
	 * @returns Nothing
	 * @throws Exception
	 */
	public void generateThreads(int numOfClients, String[] Clients, String[] Names, String[] Products) throws Exception {
		int num;
		if (numOfClients %2 == 0) {
			num = numOfClients / 2;
		} else {
			num = numOfClients / 2 + 1;
		}
		//generates multiple JavaMail threads.
		for(int i = 0; i <= num; i+= 2) {
			String[] partOfClients = null;
			String[] partOfNames = null;
			String[] partOfProducts = null;
			try {
				//Splitting arrays into smaller ones.
				partOfClients =  Arrays.copyOfRange(Clients, i, i + 1);
				partOfNames = Arrays.copyOfRange(Names,i , i + 1);
				partOfProducts = Arrays.copyOfRange(Products, i, i + 1);
				JavaMail t = new JavaMail(partOfClients, partOfNames, partOfProducts);
				t.start();
			} catch (Exception e) {
				System.out.print("An exception has occured. We are sorry.");
			}	
		}
	}
}
