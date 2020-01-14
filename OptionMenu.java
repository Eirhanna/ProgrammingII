import java.awt.*;
import sun.audio.*;
import java.awt.event.*;
import java.awt.EventQueue;
import javax.swing.*;
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
	int choice;
	/**
	 * Represents the name of the product that the user will select when he clicks
	 * in option 3
	 */
	String nameOfProduct;
	/** Represents the path of the csv file */
	String path;
	/** Initializes objects of other classes in order to call their methods */
	Databaseconnection objectOfDatabaseconnectionClass = new Databaseconnection();
	ProductFactory objectOfProductFactoryClass = new ProductFactory();
	NewPurchasesSeparation objectOfNewPurchasesSeparationClass = new NewPurchasesSeparation();
	Products objectOfProductsClass = new Products();
	CsvNewPurchases objectOfCsvNewPurchasesClass = new CsvNewPurchases();
	Customer objectOfCustomerClass = new Customer();
	Gifts objectOfGiftsClass;
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

	boolean continueLoop = true;
	boolean continueLoop10 = true;
	boolean continueLoop11 = true;
	boolean continueLoop12 = true;
	boolean continueLoop13 = true;
	boolean continueLoop14 = true;
	boolean continueLoop15 = true;
	boolean continueLoop16 = true;
	boolean continueLoop17 = true;
	boolean continueLoop18 = true;

	/**
	 * Constructor
	 */
	public OptionMenu() {

		// create the main menu
		JFrame F = new JFrame("Main Menu");
		JMenuBar menubar = new JMenuBar();

		JMenu menu1 = new JMenu("Select option!");
		menubar.add(menu1);
		menubar.setPreferredSize(new Dimension(100, 40));
		menu1.setFont(new Font("sans-serif", Font.PLAIN, 20));
		item1.setFont(new Font("sans-serif", Font.PLAIN, 16));
		item2.setFont(new Font("sans-serif", Font.PLAIN, 16));
		item3.setFont(new Font("sans-serif", Font.PLAIN, 16));
		item4.setFont(new Font("sans-serif", Font.PLAIN, 17));
		menu1.setOpaque(true);
		menu1.setBackground(new java.awt.Color(255, 229, 156));

		// add action listener
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);

		// add items to menu
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);

		// set size,location
		F.setJMenuBar(menubar);
		F.pack();
		F.setLocationRelativeTo(null);
		F.setSize(820, 600);
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(450, 300, 13, 35);
		F.getContentPane().add(label);
		F.pack();
		F.setLocationRelativeTo(null);
		
		// create the password frame
		JFrame passFrame = new JFrame("Enter your administration password");
		passFrame.setSize(400, 230);
		passFrame.setDefaultCloseOperation(passFrame.EXIT_ON_CLOSE);
		passFrame.setLocationRelativeTo(null);

		JLabel Label = new JLabel();
		JLabel textlabel = new JLabel("Enter the password", SwingConstants.CENTER);
		textlabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passFrame.setResizable(false);
		JPanel Panel = new JPanel();
		Image passimg = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		Label.setIcon(new ImageIcon(passimg));
		Label.setBounds(200, 50, 13, 35);
		passFrame.add(Label);
		Panel.setBounds(200, 200, 50, 20);
		Panel.add(textlabel);
		passFrame.add(Panel);

		JPasswordField pass = new JPasswordField(10);
		pass.setEchoChar('*');
		pass.setBounds(200, 200, 50, 20);
		passFrame.setVisible(true);

		pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				JPasswordField input = (JPasswordField) ae.getSource();
				char[] pd = input.getPassword();
				String p = new String(pd);
				if (p.contentEquals(password)) {
					try {
						JOptionPane.showMessageDialog(null, "Correct password");
						String mail = JOptionPane.showInputDialog("Please insert your e-mail");
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please insert your e-mail");
					}
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

		// create the submenu of customer's purchase option
		JFrame F2 = new JFrame("Click a product");
		F2.setLocationRelativeTo(null);
		F2.setSize(500, 370);
		JPanel panel2 = new JPanel();
		F2.add(panel2);

		// create and add the buttons
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

		// add ActionListeners to the buttons
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
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null, "you didn't insert the data");
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
						nameOfProduct = "Doll and  Bear Toybox";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
						break;
					} catch (NumberFormatException e) {
						choice = JOptionPane.showOptionDialog(null, "Invalid Entry. Would You Like To Try Again",
								"ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null, "you didn't insert the data");
					}
				}
			}

		});
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueLoop = true;
				do {
					try {
						quantOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Please insert quantity"));
						nameOfProduct = "Grand Surprise ToyBox";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
					} catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Please insert a valid quantity");
					}
					continueLoop = false;
				} while (continueLoop);
			}

		});
		button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueLoop = true;
				do {
					try {
						quantOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Please insert quantity"));
						nameOfProduct = "HotWheels Car";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
					} catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Please insert a valid quantity");
					}
					continueLoop = false;
				} while (continueLoop);
			}
		});
		button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueLoop = true;
				do {
					try {
						quantOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Please insert quantity"));
						nameOfProduct = "Stuffed Duck Abie";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
					} catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Please insert a valid quantity");
					}
					continueLoop = false;
				} while (continueLoop);
			}
		});
		button10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueLoop = true;
				do {
					try {
						quantOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Please insert quantity"));
						nameOfProduct = "Train Pollar Express";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
					} catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Please insert a valid quantity");
					}
					continueLoop = false;
				} while (continueLoop);
			}
		});
		button11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueLoop = true;
				do {
					try {
						quantOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Please insert quantity"));
						nameOfProduct = "Stuffed Zebra Martin";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
					} catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Please insert a valid quantity");
					}
					continueLoop = false;
				} while (continueLoop);
			}
		});
		button12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueLoop = true;
				do {
					try {
						quantOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Please insert quantity"));
						nameOfProduct = "Teddy Bear Molly";
						objectOfProductsClass.implementsCustomersPurchase(nameOfProduct, quantOfProduct);
					} catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Please insert a valid quantity");
					}
					continueLoop = false;
				} while (continueLoop);
			}
		});

		// add ActionListener for the new frame F2
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					F2.setVisible(true);
				}
			}
		});

		// add ActionListeners to the buttons

	}

	// Handles actions based in option
	public void actionPerformed(ActionEvent event) throws NumberFormatException {
		if (event.getSource() == item1) {
			// ImageIcon icon = new ImageIcon("src/image/download.png");
			do {
				try {
					cost = Double.parseDouble(JOptionPane.showInputDialog("Please insert the cost: "));
					ProductFactory.setCostSold(cost);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog(null, "Sorry...invalid input was insert! Please try again.");
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "The cost sold is equal to zero...");
				}
				continueLoop10 = false;
			} while (continueLoop10);
		} else if (event.getSource() == item2) {
			do {
				name = JOptionPane.showInputDialog("Please insert the name ");
				if (name == " ") {
					JOptionPane.showInputDialog("Please insert the name ");
				} else {
					continueLoop11 = false;
				}
			} while (continueLoop11);
			do {
				try {
					code = JOptionPane.showInputDialog("Please insert the code ");
				} catch (NumberFormatException e) {
					System.exit(0);
				}
				continueLoop12 = false;
			} while (continueLoop12);

			do {
				try {
					quantity = Integer.parseInt(JOptionPane.showInputDialog("Please insert the quantity "));
				} catch (NullPointerException e) {
					System.exit(0);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog(null, "Sorry...invalid quantity was insert! Please try again.");
				}
				continueLoop13 = false;
			} while (continueLoop13);

			do {
				try {
					day = Integer.parseInt(JOptionPane.showInputDialog("Please insert the day "));
				} catch (NullPointerException e) {
					System.exit(0);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog(null, "Sorry...invalid day was insert! Please try again.");
				}
				continueLoop14 = false;
			} while (continueLoop14);

			do {
				try {
					month = Integer.parseInt(JOptionPane.showInputDialog("Please insert the month "));
				} catch (NullPointerException e) {
					System.exit(0);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog(null, "Sorry...invalid month insert! Please try again.");
				}
				continueLoop15 = false;
			} while (continueLoop15);
			do {
				try {
					year = Integer.parseInt(JOptionPane.showInputDialog("Please insert the year"));
				} catch (NullPointerException e) {
					System.exit(0);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog(null, "Sorry...invalid year was insert! Please try again.");
				}
				continueLoop16 = false;
			} while (continueLoop16);
			do {
				try {
					price = Double.parseDouble(JOptionPane.showInputDialog("Please insert the price "));
				} catch (NullPointerException e) {
					System.exit(0);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog(null, "Sorry...invalid price was insert! Please try again.");
				}
				continueLoop17 = false;
			} while (continueLoop17);

			ProductFactory.purchaseOfStock(name, code, quantity, day, month, year, price);
			System.out.println(ProductFactory.getAllProducts());
			JOptionPane.showMessageDialog(null, "Thank you!!");

		} else if (event.getSource() == item4) {
			objectOfDatabaseconnectionClass.dbconnection();
			String path = (JOptionPane.showInputDialog("Please insert the path of the csv file: "));
			objectOfCsvNewPurchasesClass.saveCsvData(path);
			objectOfNewPurchasesSeparationClass.separateCustomers();
			objectOfCustomerClass.findsCustomersThatDeserveAnOffer();
			objectOfGiftsClass = new Gifts();
			objectOfGiftsClass.findGiftsReceivers(objectOfCustomerClass.getNewoffered(),
					objectOfProductsClass.createListofProductsPassedTheSellPeriod());
		}
	}

}
