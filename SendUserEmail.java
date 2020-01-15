import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * SendUserEmail is responsible to send an email to the person using the program
 * who needs to be informed about the offer made to the important clients in
 * order for them to keep track of the offers they make
 * 
 * SendUserEmail gets the name and the email account of the user and three
 * string arrays, one of recipients' mails one of their names and one of the
 * products the company has decided to offer. Then, it contacts the gmail
 * server, logs into the gmail account of the company and then prepares the
 * corresponding message to the user containing the number of the gifts given
 * and more specifically a list for the rewarded clients and a list of the
 * products that were distributed
 * 
 * @author Katerina Dimatou
 * 
 * @param email account of the user of the program
 * @param array of recipients' mails
 * @param array of recipients' names
 * @param array of products that will be given out
 * 
 * @returns Nothing
 * @throws Exception
 *
 */

public class SendUserEmail {
	public void sendUserMail(String emailOfUser, String[] recepients, String[] names, String[] productsToOffer)
			throws Exception {

		int numOfGifts = productsToOffer.length;

		String peopleOfOffer = String.join(", ", names);
		String productsOfOffer = String.join(", ", productsToOffer);

		System.out.println("Ready to send email");
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

		// sending the email by calling the prepareMessage method in order to create the
		// message

		Message message = prepareMessage(session, myAccount, emailOfUser, numOfGifts, peopleOfOffer,
				productsOfOffer);
		Transport.send(message);

		System.out.println("Message sent succesfully");
	}

	/**
	 * Method that is given the account of the sender and the account of the
	 * Recipient, the subject and the message of the mail and returns the whole
	 * detailed message to be sent
	 * 
	 * @param session
	 * @param gmail    account of our team
	 * @param name     of the user of the program
	 * @param email    account of the user of the program
	 * @param decided  number of gifts
	 * @param names    of people who were given presents
	 * @param products that were given as presents
	 * 
	 * @return the whole content of the email (Message object) according to the
	 *         parts given
	 * @throws Exception
	 * 
	 */
	private static Message prepareMessage(Session session, String myAccount, String emailOfUser, int numOfGifts,
			String peopleOfOffer, String productsOfOffer) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailOfUser));
			message.setSubject("You have given out presents!");
			message.setText("Dear" + " User" + "," + System.lineSeparator() + System.lineSeparator()
					+ "We are pleased to announce that "
					+ "you have rewarded your most valuable clients for their loyalty with a present. More specifically you have "
					+ " given out " + numOfGifts
					+ " presents which where products that had passed their period of sale!"
					+ " More specifically, a more detailed reference to the generous offer you made lies underneath:"
					+ System.lineSeparator() + System.lineSeparator() + "Clients Rewarded: " + peopleOfOffer
					+ System.lineSeparator() + "Products Given Out: " + productsOfOffer + System.lineSeparator()
					+ System.lineSeparator()
					+ " This was a very good gesture on your behalf in order to show to your clients that you value them!"
					+ System.lineSeparator() + System.lineSeparator() + "Keep up the good work, " + "always with love, "
					+ System.lineSeparator() + "DetGifthub team");
			return message;
		} catch (Exception ex) {
			Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;

	}
}
