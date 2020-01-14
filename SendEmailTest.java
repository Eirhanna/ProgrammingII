package com.progII.gifthub;

import static org.junit.Assert.*;

import org.junit.Test;

public class SendEmailTest {

	@Test
	public void test() throws Exception {
		String mails[] = { "katerina.dimatou@gmail@gmail.com" };
		String names[] = { "Katerina Dimatou" };
		String prods[] = { "Monopoly" };

		InfoMail a = new InfoMail(mails, names, prods);

		SendEmail b = new SendEmail();

		b.sendMail(a);
	}

}
