import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

/**
 * SendEmail is responsible to send emails to the customer that are entitled to
 * an offer
 * 
 * SendEmail gets three string arrays, one of recipients' mails, one of their
 * names and one of the products the company has decided to offer. It contacts
 * the gmail server, logs into the gmail account of the company and then
 * prepares and sends a personalized message to each client
 * 
 * @author Katerina Dimatou
 * 
 * @param array of recepients' mails
 * @param array of recepients' names
 * @param array of products that will be given out
 * 
 * @return Nothing
 * @throws Exception
 *
 */
public class SendEmail {

	public void sendMail(String[] recepients, String[] names, String[] productsToOffer) throws Exception {

		JOptionPane.showMessageDialog(null, "Ready to send email");
		Properties properties = new Properties();

		// Contacting the gmail server

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// Giving access to the gmail account of our team

		String myAccount = "detgifthub";
		String password = "promotionmail";

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount, password);
			}
		});

		// Sending the email by calling the prepareMessage method in order to create the
		// message and by using the for loop to send the email to every email on the
		// array

		for (int i = 0; i < recepients.length; i++) {
			Message message = prepareMessage(session, myAccount, recepients[i], names[i], productsToOffer[i]);
			Transport.send(message);
		}

		JOptionPane.showMessageDialog(null, "Message sent succesfully");
	}

	/**
	 * Method that is given the account of the sender and the account of the
	 * Recipient, the subject and the message of the mail and returns the whole
	 * detailed message to be sent
	 * 
	 * @param sessiom
	 * @param gmail   account of our team
	 * @param email   account of the person that will receive a present
	 * @param name    of the person that will receive a present
	 * @param product associated with the corresponding person
	 * 
	 * @return the message according to the parts given
	 * @throws Exception
	 */
	private static Message prepareMessage(Session session, String myAccount, String recepient, String name,
			String productToOffer) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("It is time for a present!");
			message.setText("Dear" + " " + name + "," + System.lineSeparator() + System.lineSeparator()
					+ "We are pleased to announce that "
					+ "it is time we rewarded you for your loyalty with a present, a product of ours totally free! "
					+ " We chose for you the product " + productToOffer
					+ " and you can receive it at our store whenever it is convenient for you "
					+ "by telling us your name combined to the name of the product that we sent you"
					+ System.lineSeparator() + System.lineSeparator() + "With our best regards, " + "always with love, "
					+ System.lineSeparator() + "DetGifthub team");
			return message;
		} catch (Exception ex) {
			Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
