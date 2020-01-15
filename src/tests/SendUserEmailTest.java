import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SendUserEmailTest {

	@Test
	public void test() throws Exception {
		String mails[] = { "katerina.dimatou@gmail.com", "irianna_pap@hotmail.gr" };
		String names[] = { "Katerina Dimatou", "Eirianna Papoutsi" };
		String prods[] = { "Monopoly", "Jenga" };

		String mailOfUser = "katerina.dimatou@gmail.com";

		SendUserEmail b = new SendUserEmail();

		b.sendUserMail(mailOfUser, mails, names, prods);
	}
}
