import static org.junit.Assert.*;

import org.junit.Test;

public class SendEmailTest {

	@Test
	public void test() throws Exception {
		String mails[] = { "katerina.dimatou@gmail.com" };
		String names[] = { "Katerina Dimatou" };
		String prods[] = { "Monopoly" };

		SendEmail b = new SendEmail();

		b.sendMail(mails, names, prods);
	}

}
