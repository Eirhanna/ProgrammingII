import java.awt.*;
import sun.audio.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.io.*;
import java.util.InputMismatchException;

/**
 * This class creates in GUI the main menu. First it creates a verification
 * JFrame to enter a password and then it presents the main menu JFrame with the
 * four options
 */
public class OptionMenu extends JFrame implements ActionListener {
	/** Represents the JMenu items in the main menu's options */
	private JMenuItem item1 = new JMenuItem("1.Set the cost of sold items");
	private JMenuItem item2 = new JMenuItem("2.Buy product's stock");
	private JMenuItem item3 = new JMenuItem("3.Add customer's purchase");
	private JMenuItem item4 = new JMenuItem("4.Make an offer");
	/** Represents the correct password of our user */
	private static String password = "pass";
	/**
	 * Represents the quantity of the product that the user will select when he
	 * clicks in option 3
	 */
	int quantOfProduct;
	/** Represents the user's choice in a JOptionPane */
	int choice, choice1;
	/**
	 * Represents the name of the product that the user will select when he clicks
	 * in option 3
	 */
	String nameOfProduct;
	/** Represents the path of the csv file */
	String path, mail;
	/** Initializes objects of other classes in order to call their methods */
	Databaseconnection objectOfDatabaseconnectionClass = new Databaseconnection();
	ProductFactory objectOfProductFactoryClass = new ProductFactory();
	NewPurchasesSeparation objectOfNewPurchasesSeparationClass = new NewPurchasesSeparation();
	Products objectOfProductsClass = new Products();
	CsvNewPurchases objectOfCsvNewPurchasesClass = new CsvNewPurchases();
	Customer objectOfCustomerClass = new Customer();
	Gifts objectOfGiftsClass;
	UIManager UI = new UIManager();
	boolean continueLoop = true;
	/**
	 * Represents the name, code, quantity, day, month , year, price and cost of the
	 * product in option 2 of the main menu
	 */
	String name, code;
	int quantity, day, month, year;
	double price, cost;
	/**
	 * Represents the number of gifts that the user wants to make in option 4 of the
	 * main menu
	 */
	int numberOfGifts;

	/**
	 * Constructor
	 */
	public OptionMenu() {
		
		/**
		 * Set backround color for all JOptionPanes
		 */
		UI.put("OptionPane.background", new ColorUIResource(253, 255, 108));
		UI.put("Panel.background", new ColorUIResource(253, 255, 108));
		
		/**
		 * Create the main menu 
		 */
		JFrame F = new JFrame("Main Menu");
		JMenuBar menubar = new JMenuBar();
		
		/**
		 * Create the main options
		 */
		JMenu menu1 = new JMenu("Select option!");
		menubar.add(menu1);
		menubar.setPreferredSize(new Dimension(100, 40));
		menu1.setFont(new Font("sans-serif", Font.PLAIN, 20));
		item1.setFont(new Font("sans-serif", Font.PLAIN, 16));
		item2.setFont(new Font("sans-serif", Font.PLAIN, 16));
		item3.setFont(new Font("sans-serif", Font.PLAIN, 16));
		item4.setFont(new Font("sans-serif", Font.PLAIN, 17));
		menu1.setOpaque(true);
		menu1.setBackground(new java.awt.Color(253, 255, 108));

		/**
		 * Add ActionListeners to the main options
		 */
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);

		/**
		 * Add items to the menu
		 */
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);

		/**
		 * Format the F JFrame
		 */
		F.setJMenuBar(menubar);
		F.pack();
		F.setLocationRelativeTo(null);
		F.setSize(820, 600);
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo page.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(450, 300, 13, 35);
		F.getContentPane().add(label);
		F.pack();
		F.setLocationRelativeTo(null);

		/**
		 * Create and Format the Password Frame 
		 */
		JFrame passFrame = new JFrame("Welcome to Det Gifthub");

		passFrame.setSize(500, 270);
		passFrame.setDefaultCloseOperation(passFrame.EXIT_ON_CLOSE);
		passFrame.setLocationRelativeTo(null);

		JLabel Label = new JLabel();
		JLabel textlabel = new JLabel("Enter the password", SwingConstants.CENTER);
		textlabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		passFrame.setResizable(false);
		JPanel Panel = new JPanel();
		Image passimg = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		Label.setIcon(new ImageIcon(passimg));
		Label.setBounds(200, 50, 13, 35);
		passFrame.add(Label);
		Panel.setBounds(200, 200, 50, 20);
		Panel.add(textlabel);
		Panel.setBackground(new java.awt.Color(133, 255, 155));
		passFrame.add(Panel);

		JPasswordField pass = new JPasswordField(10);
		pass.setEchoChar('*');
		pass.setBounds(200, 200, 50, 20);
		passFrame.setVisible(true);
		
		/**
		 * Add ActionListener to @pass 
		 */
		pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				JPasswordField input = (JPasswordField) ae.getSource();
				char[] pd = input.getPassword();
				String p = new String(pd);
				if (p.contentEquals(password)) {
					JOptionPane.showMessageDialog(null, "Correct password!");
					F.setVisible(true);
					passFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(passFrame, " Sorry...Wrong Password!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		});
		Panel.add(Label);
		Panel.add(pass);

		/**
		 * Create the JFrame F2 which will appear when the user clicks the third option 
		 * in the main menu 
		 */
		JFrame F2 = new JFrame("Click a product");
		F2.setLocationRelativeTo(null);
		F2.setSize(500, 370);
		JPanel panel2 = new JPanel();
		F2.add(panel2);

		/**
		 * Create and add the buttons to F2
		 */
		ImageIcon Product1 = new ImageIcon(this.getClass().getResource("/toybox1.png"));
		ImageIcon Product2 = new ImageIcon(this.getClass().getResource("/ball.png"));
		ImageIcon Product3 = new ImageIcon(this.getClass().getResource("/toybox2.png"));
		ImageIcon Product4 = new ImageIcon(this.getClass().getResource("/car.png"));
		ImageIcon Product5 = new ImageIcon(this.getClass().getResource("/duck.png"));
		ImageIcon Product6 = new ImageIcon(this.getClass().getResource("/train.png"));
		ImageIcon Product7 = new ImageIcon(this.getClass().getResource("/zebra.png"));
		ImageIcon Product8 = new ImageIcon(this.getClass().getResource("/teddy-bear.png"));
		JButton button5 = new JButton("  Doll and  Bear Toybox", Product1);
		JButton button6 = new JButton("  Colourfull Ball ", Product2);
		JButton button7 = new JButton("Grand Surprise ToyBox ", Product3);
		JButton button8 = new JButton("HotWheels Car ", Product4);
		JButton button9 = new JButton("Stuffed Duck Abie", Product5);
		JButton button10 = new JButton("Train Pollar Express", Product6);
		JButton button11 = new JButton("Stuffed Zebra Martin", Product7);
		JButton button12 = new JButton("Teddy Bear Molly", Product8);
		setLayout(new FlowLayout());
		add(button5);
		add(button6);
		panel2.add(button5);
		panel2.add(button6);
		panel2.add(button7);
		panel2.add(button8);
		panel2.add(button9);
		panel2.add(button10);
		panel2.add(button11);
		panel2.add(button12);
		button5.setBounds(250, 250, 120, 35);

		/**
		 * Add ActionListers to the product buttons 
		 */
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Doll and  Bear Toybox";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Grand Surprise ToyBox";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "HotWheels Car";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Stuffed Duck Abie";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Train Pollar Express";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Stuffed Zebra Martin";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Teddy Bear Molly";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		button12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				while (true) {
					String str = JOptionPane.showInputDialog("Please insert quantity: ");
					try {
						quantOfProduct = Integer.parseInt(str);
						nameOfProduct = "Colourfull Ball";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid on No Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}
				}
			}
		});
		/**
		 * Add ActionListener for option 3 in the main menu so that the 
		 * F2 appears when you click 
		 */
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				{
					F2.setVisible(true);
				}
			}
		});
	}

	int result;

	/**
	 * Handles Actions based in option 1,2 and 4
	 */
	public void actionPerformed(ActionEvent event) throws NumberFormatException {
		if (event.getSource() == item1) {
			while (true) {
				String str = JOptionPane.showInputDialog("Please insert the cost of goods sold : ");
				try {
					cost = Integer.parseInt(str);
					ProductFactory.setCostSold(cost);
					break;
				} catch (NumberFormatException e) {
					choice = JOptionPane.showOptionDialog(null, "Invalid or no Entry...Try Again!", "ERROR",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (choice == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "You have to set the cost first!! ");
						break;
					} else if (choice == JOptionPane.CLOSED_OPTION) {
						result = JOptionPane.showConfirmDialog(null, "Exit?", "Confirm Exit",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							break;
						}
					}
				}
			}
		} else if (event.getSource() == item2) {
			JTextField fieldName = new JTextField();
			JTextField fieldCode = new JTextField();
			JTextField fieldQuantity = new JTextField();
			JTextField fieldDay = new JTextField();
			JTextField fieldMonth = new JTextField();
			JTextField fieldYear = new JTextField();
			JTextField fieldPrice = new JTextField();
			Object[] message = { "Insert the name:", fieldName, "Insert the code:", fieldCode, "Insert the quantity:",
					fieldQuantity, "Insert the day:", fieldDay, "Insert the month:", fieldMonth, "Insert the year:",
					fieldYear, "Insert the price:", fieldPrice, };
			int option = JOptionPane.showConfirmDialog(null, message, "Please enter all your values",
					JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if (ProductFactory.getAllProducts().containsKey(fieldName.getText())) {
					choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy stock with these data?",
							"Confirm stock's purchase", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							null, null);
					if (choice == JOptionPane.YES_OPTION) {
						ProductFactory.purchaseOfStock(fieldName.getText(), fieldCode.getText(),
								Integer.parseInt(fieldQuantity.getText()), Integer.parseInt(fieldDay.getText()),
								Integer.parseInt(fieldMonth.getText()), Integer.parseInt(fieldYear.getText()),
								Double.parseDouble(fieldPrice.getText()));
						JOptionPane.showMessageDialog(null, "Stock bought successfully!!");
					} else {

					}
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, this product does not exist...");
				}

			} else if (event.getSource() == item4) {
				while (true) {
					mail = (JOptionPane.showInputDialog("Please insert your e-mail: "));
					choice1 = JOptionPane.showOptionDialog(null, "Are you sure you want to continue?", "ERROR",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (choice1 == JOptionPane.NO_OPTION) {
						mail = (JOptionPane.showInputDialog("Please insert your e-mail: "));
					} else {
						break;
					}
				}
				objectOfDatabaseconnectionClass.dbconnection();
				try {
					String path = (JOptionPane.showInputDialog("Please insert the path of the csv file: "));
				} catch (NullPointerException e) {
					System.exit(0);
				}

				objectOfDatabaseconnectionClass.dbconnection();
				objectOfNewPurchasesSeparationClass.separateCustomers();
				objectOfCustomerClass.findsCustomersThatDeserveAnOffer();
				objectOfGiftsClass = new Gifts();
				try {
					objectOfGiftsClass.findGiftsReceivers();
				} catch (Exception e) {
					System.err.print("Exception caught: " + e);
				}
			}
		}
	}
}
