import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Font;

public class SecondFrame extends JFrame {

	private JPanel contentPane;
	private String message;
	private JTable bookTable;
	private JTextField txtSearch;
	private DefaultTableModel dtmBooks;
	private JComboBox bookTypeBox;
	private String selected;
	private JTable table;
	private DefaultTableModel dtmSelected;
	private Double totalPrice = 0.00;
	private JLayeredPane layeredPane;
	private JTable payPalTable;
	private JTextField textEmail;
	private DefaultTableModel dtmPayPal;
	private boolean error;
	private JTable CreditCardTable;
	private JTextField textCreditNum;
	private JTextField textCreditSec;
	private DefaultTableModel dtmCredit;
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	
	public static boolean is_numeric(String someString) {
		if(someString == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(someString);
			
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public void updateTable(String selected,List<Books> bookList) {
		
		selected = bookTypeBox.getSelectedItem().toString();
		
		while(dtmBooks.getRowCount() > 0) {
			dtmBooks.removeRow(0);
		}
		String searchText = txtSearch.getText();
		System.out.println(searchText);
		
		Collections.sort(bookList);
		for(Books book : bookList) {
			if(selected.equals("All Genre")) {
				if((book.getTitle().trim().toLowerCase()).contains(searchText.toLowerCase())){
					Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
					dtmBooks.addRow(rowdata);
				}
				else if(searchText.equals("")) {
					Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
					dtmBooks.addRow(rowdata);
				}
			}
			else {
			
				if(book.getTitle().trim().toLowerCase().contains(searchText.toLowerCase()) && (book.getGenre().trim().toLowerCase().equals(selected.toLowerCase()))) {
					
					Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
					dtmBooks.addRow(rowdata);
				
				}
				else if(searchText.equals("") && (book.getGenre().trim().toLowerCase().equals(selected.toLowerCase()))) {
					Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
					dtmBooks.addRow(rowdata);
				}
			}
		}
		
	}
	
	
	
	public SecondFrame(Users user) {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 502);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1254, 452);
		contentPane.add(tabbedPane);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setForeground(Color.BLACK);
		bookPanel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Books", null, bookPanel, null);
		bookPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 1239, 345);
		bookPanel.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.setForeground(Color.BLACK);
		bookTable.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(bookTable);
		
		dtmBooks = new DefaultTableModel();
		dtmBooks.setColumnIdentifiers(new Object[] {"ISBN", "Type", "Title", "Language", "Genre", "Release Date", "Price", "Quantity", "INFO", "INFO2"});
		bookTable.setModel(dtmBooks);
		
		bookTable.setDefaultEditor(Object.class, null);
		
		List<Books> bookList = new ArrayList<>();
		
		try {
			String line;
			BufferedReader userReader = new BufferedReader(new FileReader("src/Stock.txt"));
			while((line = userReader.readLine()) != null) {
				
				
				
				String[] data = line.split(",");
				
				Books book = new Books(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), Double.parseDouble(data[6].trim()), Integer.parseInt(data[7].trim()), data[8].trim(), data[9].trim());
				bookList.add(book);
				
			}
			
			Collections.sort(bookList);
			for(Books book : bookList) {
				Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
				dtmBooks.addRow(rowdata);
			}
			userReader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		txtSearch = new JTextField();
		txtSearch.setForeground(Color.BLACK);
		txtSearch.setBackground(Color.LIGHT_GRAY);
		txtSearch.setBounds(168, 24, 263, 20);
		bookPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		bookTypeBox = new JComboBox(genreType.values());
		bookTypeBox.setForeground(Color.DARK_GRAY);
		bookTypeBox.setBounds(10, 23, 148, 22);
		bookPanel.add(bookTypeBox);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setBackground(Color.GRAY);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String selected = bookTypeBox.getSelectedItem().toString();
				
//				while(dtmBooks.getRowCount() > 0) {
//					dtmBooks.removeRow(0);
//				}
//				String searchText = txtSearch.getText();
//				System.out.println(searchText);
//				
//				Collections.sort(bookList);
//				for(Books book : bookList) {
//					if(selected.equals("All Genre")) {
//						if((book.getTitle().trim().toLowerCase()).contains(searchText.toLowerCase())){
//							Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
//							dtmBooks.addRow(rowdata);
//						}
//						else if(searchText.equals("")) {
//							Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
//							dtmBooks.addRow(rowdata);
//						}
//					}
//					else {
//					
//						if(book.getTitle().trim().toLowerCase().contains(searchText.toLowerCase()) && (book.getGenre().trim().toLowerCase().equals(selected.toLowerCase()))) {
//							
//							Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
//							dtmBooks.addRow(rowdata);
//						
//						}
//						else if(searchText.equals("") && (book.getGenre().trim().toLowerCase().equals(selected.toLowerCase()))) {
//							Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInStock(), book.getInfo(), book.getInfo2()};
//							dtmBooks.addRow(rowdata);
//						}
//					}
//				}
				
				updateTable(selected, bookList);
			}
		});
		btnSearch.setBounds(441, 23, 89, 23);
		bookPanel.add(btnSearch);
		
	
		bookTypeBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String selected = bookTypeBox.getSelectedItem().toString();
				updateTable(selected, bookList);
			}
		});
		
		
		JButton btnLogout = new JButton("Log out");
		btnLogout.setBackground(Color.GRAY);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
				System.out.println(user.getBasket());
			}
		});
		btnLogout.setBounds(1150, 11, 89, 23);
		bookPanel.add(btnLogout);
		
		layeredPane = new JLayeredPane();
		tabbedPane.addTab("Basket and Payment", null, layeredPane, null);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel basketPanel = new JPanel();
		basketPanel.setBackground(Color.DARK_GRAY);
		layeredPane.add(basketPanel, "name_294166791353600");
		basketPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 1229, 213);
		basketPanel.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		dtmSelected = new DefaultTableModel();
		dtmSelected.setColumnIdentifiers(new Object[] {"ISBN", "Type", "Title", "Language", "Genre", "Release Date", "Price", "INFO", "INFO2"});
		table.setModel(dtmSelected);
		table.setDefaultEditor(Object.class, null);
		
		

		

		
		
		
		
		JLabel totalLabel = new JLabel("TOTAL TO BE PAID: ");
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		totalLabel.setForeground(Color.WHITE);
		totalLabel.setBounds(20, 235, 213, 69);
		basketPanel.add(totalLabel);
		
		
		JButton btnAddBooks = new JButton("add book");
		btnAddBooks.setBackground(Color.GRAY);
		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(dtmSelected.getRowCount() > 0) {
					dtmSelected.removeRow(0);
				}
				while(dtmPayPal.getRowCount() > 0) {
					dtmPayPal.removeRow(0);
				}
				
				while(dtmCredit.getRowCount() > 0) {
					dtmCredit.removeRow(0);
				}
				
				String isbn = ((String) bookTable.getValueAt(bookTable.getSelectedRow(), 0)).trim();
				String type = ((String) bookTable.getValueAt(bookTable.getSelectedRow(), 1)).trim();
				String selected = bookTypeBox.getSelectedItem().toString();
				System.out.println(error);
					totalPrice = 0.00;
					for(Books eachBook : bookList) {
						
						
						if((eachBook.getIsbn().equals(isbn)) && (eachBook.getBookType().equals(type))) {
							if(eachBook.getInStock() <= 0) {
								JOptionPane.showMessageDialog(null, "NO MORE BOOKS IN STOCK","ERROR", JOptionPane.ERROR_MESSAGE);
							}
							else if(user.getBasket().contains(eachBook)) {
								JOptionPane.showMessageDialog(null, "CANNOT ADD SAME BOOK TWICE","ERROR", JOptionPane.ERROR_MESSAGE);
							}
							else {
								eachBook.decStock(1);
								user.addBooks(eachBook);
							}
						}
						
					}
					for(Books book : user.getBasket()) {
						Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInfo(), book.getInfo2()};
						dtmSelected.addRow(rowdata);
						dtmPayPal.addRow(rowdata);
						dtmCredit.addRow(rowdata);
					}
					
					
					for(Books book : user.getBasket()) {
						totalPrice += book.getPrice();
					}
					
					totalLabel.setText("TOTAL TO BE PAID: " + totalPrice.toString());
					
					updateTable(selected, bookList);
			}
		});
		
		JButton btnCancel = new JButton("Cancel Basket");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dtmSelected.getRowCount() > 0) {
				String selected = bookTypeBox.getSelectedItem().toString();
				totalPrice = 0.00;
				while(dtmSelected.getRowCount() > 0) {
					dtmSelected.removeRow(0);
				}
				
				for(Books book : bookList) {
					if(user.getBasket().contains(book)) {
						book.returnToBasket();
					}
				}
				
				user.emptyBasket();
				totalLabel.setText("TOTAL TO BE PAID: " + totalPrice.toString());
				
				updateTable(selected, bookList);
				}
				else {
					JOptionPane.showMessageDialog(null, "NO BOOKS IN BASKET","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancel.setBackground(Color.GRAY);
		btnCancel.setBounds(20, 353, 168, 48);
		basketPanel.add(btnCancel);
		
		btnAddBooks.setBounds(538, 23, 98, 23);
		bookPanel.add(btnAddBooks);
		
		JPanel payPalPanel = new JPanel();
		payPalPanel.setBackground(Color.DARK_GRAY);
		layeredPane.add(payPalPanel, "name_294221258534500");
		payPalPanel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 51, 1229, 223);
		payPalPanel.add(scrollPane_2);
		
		payPalTable = new JTable();
		scrollPane_2.setViewportView(payPalTable);
		dtmPayPal = new DefaultTableModel();
		dtmPayPal.setColumnIdentifiers(new Object[] {"ISBN", "Type", "Title", "Language", "Genre", "Release Date", "Price", "INFO", "INFO2"});
		payPalTable.setModel(dtmPayPal);
		
		JLabel lblNewLabel = new JLabel("YOUR ORDERS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 11, 234, 36);
		payPalPanel.add(lblNewLabel);
		
		JLabel lblPay = new JLabel("HOW MUCH YOU'LL PAY: ");
		lblPay.setForeground(Color.WHITE);
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPay.setBounds(10, 285, 295, 64);
		payPalPanel.add(lblPay);
		
		textEmail = new JTextField();
		textEmail.setBounds(154, 360, 132, 36);
		payPalPanel.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(90, 360, 124, 36);
		payPalPanel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Place Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textEmail.getText();
				if(email.equals("")) {
					JOptionPane.showMessageDialog(null, "EMAIL NOT ENTERED","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(dtmCredit.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "NO BOOKS IN BASKET","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, totalPrice + " Paid using PayPal");
					user.emptyBasket();
					while(dtmPayPal.getRowCount() > 0) {
						dtmPayPal.removeRow(0);
						dtmSelected.removeRow(0);
						dtmCredit.removeRow(0);
						
					}
					totalPrice = 0.0;
					lblPay.setText("TOTAL TO BE PAID: " + totalPrice);
					totalLabel.setText("TOTAL TO BE PAID: " + totalPrice);
					
					try {
					FileWriter writer = new FileWriter("src/Stock.txt");
					
						for(Books book : bookList) {
							List<String> list = new ArrayList<>();
							list.add(book.getIsbn());
							list.add(" " + book.getBookType());
							list.add(" " + book.getTitle());
							list.add(" " + book.getLanguage());
							list.add(" " + book.getGenre());
							list.add(" " + book.getReleaseDate());
							list.add(" " + book.getPrice().toString());
							list.add(" " + book.getInStock().toString());
							list.add(" " + book.getInfo());
							list.add(" " + book.getInfo2());
							System.out.println(String.join(",", list));
								
							writer.append(String.join(",", list));
							writer.append("\n");
							}
						writer.flush();
						writer.close();
						
					
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		});
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(491, 353, 149, 51);
		payPalPanel.add(btnNewButton);
		
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(Color.DARK_GRAY);
		layeredPane.add(cardPanel, "name_294223162623600");
		cardPanel.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 56, 1229, 249);
		cardPanel.add(scrollPane_3);
		
		CreditCardTable = new JTable();
		scrollPane_3.setViewportView(CreditCardTable);
		
		dtmCredit = new DefaultTableModel();
		dtmCredit.setColumnIdentifiers(new Object[] {"ISBN", "Type", "Title", "Language", "Genre", "Release Date", "Price", "INFO", "INFO2"});
		CreditCardTable.setModel(dtmCredit);
		
		JLabel lblNewLabel_2 = new JLabel("YOUR ORDER");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(10, 11, 223, 39);
		cardPanel.add(lblNewLabel_2);
		
		JLabel lblPayCredit = new JLabel("TOTAL TO BE PAID: ");
		lblPayCredit.setForeground(Color.WHITE);
		lblPayCredit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPayCredit.setBounds(10, 313, 223, 39);
		cardPanel.add(lblPayCredit);
		
		textCreditNum = new JTextField();
		textCreditNum.setBounds(218, 363, 296, 50);
		cardPanel.add(textCreditNum);
		textCreditNum.setColumns(10);
		
		textCreditNum.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(textCreditNum.getText().length() >= 6)
					e.consume();
			}
		});
		
		textCreditSec = new JTextField();
		textCreditSec.setBounds(677, 363, 190, 50);
		cardPanel.add(textCreditSec);
		textCreditSec.setColumns(10);
		
		textCreditSec.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(textCreditSec.getText().length() >= 3)
					e.consume();
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("CARD NUMBER");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(81, 369, 127, 39);
		cardPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("SECURITY CODE");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(540, 361, 127, 50);
		cardPanel.add(lblNewLabel_5);
		
		JButton btnCredit = new JButton("PLACE ORDER");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cardNumber = textCreditNum.getText();
				String security = textCreditSec.getText();
				if(!is_numeric(cardNumber) || !is_numeric(security)) {
					JOptionPane.showMessageDialog(null, "CARD NUMBER AND SECURITY NUMBER MUST CONSIST OF NUMBERS","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(dtmCredit.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "NO BOOKS IN BASKET","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, totalPrice + " Paid using Credit Card");
					user.emptyBasket();
					while(dtmPayPal.getRowCount() > 0) {
						dtmPayPal.removeRow(0);
						dtmSelected.removeRow(0);
						dtmCredit.removeRow(0);
					}
					totalPrice = 0.0;
					lblPay.setText("TOTAL TO BE PAID: " + totalPrice);
					totalLabel.setText("TOTAL TO BE PAID: " + totalPrice);
					
					try {
					FileWriter writer = new FileWriter("src/Stock.txt");
					
						for(Books book : bookList) {
							List<String> list = new ArrayList<>();
							list.add(book.getIsbn());
							list.add(" " + book.getBookType());
							list.add(" " + book.getTitle());
							list.add(" " + book.getLanguage());
							list.add(" " + book.getGenre());
							list.add(" " + book.getReleaseDate());
							list.add(" " + book.getPrice().toString());
							list.add(" " + book.getInStock().toString());
							list.add(" " + book.getInfo());
							list.add(" " + book.getInfo2());
							System.out.println(String.join(",", list));
								
							writer.append(String.join(",", list));
							writer.append("\n");
							}
						writer.flush();
						writer.close();
						
					
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				
			}
				
			}
		});
		btnCredit.setBackground(Color.GRAY);
		btnCredit.setBounds(877, 363, 157, 50);
		cardPanel.add(btnCredit);
		
		JButton btnBasketCred = new JButton("BACK TO BASKET");
		btnBasketCred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(basketPanel);
			}
		});
		btnBasketCred.setBackground(Color.GRAY);
		btnBasketCred.setBounds(1103, 363, 136, 50);
		cardPanel.add(btnBasketCred);
		
		JButton btnPayPal = new JButton("Pay with PayPal");
		btnPayPal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(payPalPanel);
				totalPrice = 0.00;
				while(dtmPayPal.getRowCount() > 0) {
					dtmPayPal.removeRow(0);
				}
				for(Books book : user.getBasket()) {
					Object[] rowdata = new Object[] {book.getIsbn(), book.getBookType(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getPrice(), book.getInfo(), book.getInfo2()};
					dtmPayPal.addRow(rowdata);
				}
				
				
				for(Books book : user.getBasket()) {
					totalPrice += book.getPrice();
				}
				
				lblPay.setText("TOTAL TO BE PAID: " + totalPrice.toString());
				lblPay.setText("TOTAL TO BE PAID: " + totalPrice.toString());
				
			}
		});
		btnPayPal.setBackground(Color.CYAN);
		btnPayPal.setBounds(293, 353, 176, 48);
		
		basketPanel.add(btnPayPal);
		
		
		JButton btnCard = new JButton("Pay with Credit Cad");
		btnCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(cardPanel);
			}
		});
		btnCard.setBackground(Color.GREEN);
		btnCard.setBounds(592, 353, 176, 48);
		basketPanel.add(btnCard);
		
		JButton btnNewButton_1 = new JButton("<-- Back to Basket");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(basketPanel);
			}
		});
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(1083, 353, 156, 60);
		payPalPanel.add(btnNewButton_1);
		
		
		JComboBox comboBox = new JComboBox(Domains.values());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(296, 360, 181, 36);
		payPalPanel.add(comboBox);
		
		
		JLabel userLabel = new JLabel("User: " + user.getSurname().trim());
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(10, 0, 113, 14);
		contentPane.add(userLabel);
	}
}
