import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField isbnTxt;
	private JTextField titleTxt;
	private JTextField languageTxt;
	private JTextField releaseDateTxt;
	private JTextField priceTxt;
	private JTextField quantityTxt;
	private JButton btnAdd;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JComboBox genreBox;
	private JLabel lblInfo;
	private JLabel lblInfo2;
	private JComboBox info2Box;
	private JTextField infoTxt;
	private JComboBox info2ebook;
	private JComboBox info2audio;

	
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
	
	public AdminFrame(Users user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 946, 512);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN ADDS BOOKS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(394, 11, 147, 54);
		contentPane.add(lblNewLabel);
		
		JComboBox typeBox = new JComboBox(BookType.values());
		typeBox.setBounds(10, 250, 94, 28);
		contentPane.add(typeBox);
		
		isbnTxt = new JTextField();
		isbnTxt.setBounds(114, 250, 111, 28);
		contentPane.add(isbnTxt);
		isbnTxt.setColumns(10);
		
		titleTxt = new JTextField();
		titleTxt.setBounds(235, 250, 147, 28);
		contentPane.add(titleTxt);
		titleTxt.setColumns(10);
		
		languageTxt = new JTextField();
		languageTxt.setBounds(394, 250, 125, 28);
		contentPane.add(languageTxt);
		languageTxt.setColumns(10);
		
		releaseDateTxt = new JTextField();
		releaseDateTxt.setBounds(641, 250, 111, 28);
		contentPane.add(releaseDateTxt);
		releaseDateTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setBounds(764, 250, 125, 28);
		contentPane.add(priceTxt);
		priceTxt.setColumns(10);
		
		quantityTxt = new JTextField();
		quantityTxt.setBounds(235, 342, 111, 28);
		contentPane.add(quantityTxt);
		quantityTxt.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String regex = "^(0?[1-9]|[12][0-9]|3[01])-[0-9]+-[0-9]+$";
				if(!releaseDateTxt.getText().matches(regex)) {
					JOptionPane.showMessageDialog(null, "WRONG DATE FORMAT, MUST BE DD-MM-YYYY","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(isbnTxt.getText().equals("") || titleTxt.getText().equals("") || languageTxt.getText().equals("") || releaseDateTxt.getText().equals("") || priceTxt.getText().equals("") || quantityTxt.getText().equals("") || infoTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "MUST FILL ALL FIELDS","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if((!is_numeric(isbnTxt.getText())) || (isbnTxt.getText().length() != 8)) {
					JOptionPane.showMessageDialog(null, "ISBN MUST CONSIST OF NUMBERS AND MUST BE 8 CHARACTERS","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(!is_numeric(priceTxt.getText())) {
					JOptionPane.showMessageDialog(null, "PRICE MUST BE A NUMBER","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(!is_numeric(quantityTxt.getText())) {
					JOptionPane.showMessageDialog(null, "QUANTITY MUST BE A NUMBER","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(!is_numeric(infoTxt.getText())) {
					if(typeBox.getSelectedItem().toString().equals("audiobook") || typeBox.getSelectedItem().toString().equals("ebook")) {
						JOptionPane.showMessageDialog(null, "LENGTH MUST BE A NUMBER","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else if(typeBox.getSelectedItem().toString().equals("paperback")) {
						JOptionPane.showMessageDialog(null, "NUMBER OF PAGES MUST BE A NUMBER","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(genreBox.getSelectedItem().toString().equals("All Genre")) {
					JOptionPane.showMessageDialog(null, "MUST SELECT A GENRE","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					try {
						FileWriter writer = new FileWriter("src/Stock.txt",true);
						BufferedWriter bw = new BufferedWriter(writer);
						List<String> list = new ArrayList<>();
						if(typeBox.getSelectedItem().toString().equals("paperback")) {
							
							list.add(isbnTxt.getText());
							list.add(" "+typeBox.getSelectedItem().toString());
							list.add(" "+titleTxt.getText());
							list.add(" "+languageTxt.getText());
							list.add(" "+genreBox.getSelectedItem().toString());
							list.add(" "+releaseDateTxt.getText());
							list.add(" "+priceTxt.getText());
							list.add(" "+quantityTxt.getText());
							list.add(" "+infoTxt.getText());
							list.add(" "+info2Box.getSelectedItem().toString() + "\n");
							bw.write(String.join(",", list));
							bw.close();
							JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED BOOK");
						}
						else if(typeBox.getSelectedItem().toString().equals("ebook")) {
							
							list.add(isbnTxt.getText());
							list.add(" "+typeBox.getSelectedItem().toString());
							list.add(" "+titleTxt.getText());
							list.add(" "+languageTxt.getText());
							list.add(" "+genreBox.getSelectedItem().toString());
							list.add(" "+releaseDateTxt.getText());
							list.add(" "+priceTxt.getText());
							list.add(" "+quantityTxt.getText());
							list.add(" "+infoTxt.getText());
							list.add(" "+info2ebook.getSelectedItem().toString() + "\n");
							bw.write(String.join(",", list));
							bw.close();
							JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED BOOK");
							
						}
						else if(typeBox.getSelectedItem().toString().equals("audiobook")) {
							
							list.add(isbnTxt.getText());
							list.add(" "+typeBox.getSelectedItem().toString());
							list.add(" "+titleTxt.getText());
							list.add(" "+languageTxt.getText());
							list.add(" "+genreBox.getSelectedItem().toString());
							list.add(" "+releaseDateTxt.getText());
							list.add(" "+priceTxt.getText());
							list.add(" "+quantityTxt.getText());
							list.add(" "+infoTxt.getText());
							list.add(" "+info2audio.getSelectedItem().toString() + "\n");
							bw.write(String.join(",", list));
							bw.close();
							JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED BOOK");
							
						}

						
					
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				}
				
			}
		});
		btnAdd.setBounds(408, 376, 111, 35);
		contentPane.add(btnAdd);
		
		lblNewLabel_1 = new JLabel("type");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(37, 225, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(148, 225, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("title");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(295, 225, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("language");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(431, 225, 88, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("genre");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(562, 225, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel(" release date");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(658, 225, 94, 14);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("price");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(808, 225, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("quantity");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(268, 317, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		genreBox = new JComboBox(genreType.values());
		genreBox.setBounds(526, 250, 105, 28);
		contentPane.add(genreBox);
		
		lblInfo2 = new JLabel("Condition");
		lblInfo2.setForeground(Color.WHITE);
		lblInfo2.setBounds(625, 317, 105, 14);
		contentPane.add(lblInfo2);
		
		info2Box = new JComboBox(BookCondition.values());
		info2Box.setBounds(587, 342, 130, 28);
		contentPane.add(info2Box);
		
		lblInfo = new JLabel("Number of Pages");
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setBounds(413, 317, 138, 14);
		contentPane.add(lblInfo);
		
		infoTxt = new JTextField();
		infoTxt.setBounds(394, 342, 138, 28);
		contentPane.add(infoTxt);
		infoTxt.setColumns(10);
		
		info2ebook = new JComboBox(EbookFormat.values());
		info2ebook.setBounds(587, 342, 130, 28);
		contentPane.add(info2ebook);
		
		info2audio = new JComboBox(AudioFormat.values());
		info2audio.setBounds(587, 342, 130, 29);
		contentPane.add(info2audio);
		
		JButton btnLogout = new JButton("LOG OUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnLogout.setBounds(789, 11, 116, 35);
		contentPane.add(btnLogout);
		info2Box.setVisible(true);
		info2ebook.setVisible(false);
		info2audio.setVisible(false);
		typeBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String selected = typeBox.getSelectedItem().toString();
				if(selected.equals("paperback")) {
					lblInfo.setText("Number of Pages");
					info2Box.setVisible(true);
					info2ebook.setVisible(false);
					info2audio.setVisible(false);
				}
				else if(selected.equals("ebook")) {
					lblInfo.setText("Number of Pages");
					lblInfo2.setText("Format");
					info2Box.setVisible(false);
					info2ebook.setVisible(true);
					info2audio.setVisible(false);
				}
				else if(selected.equals("audiobook")) {
					lblInfo.setText("length");
					lblInfo2.setText("Format");
					info2Box.setVisible(false);
					info2ebook.setVisible(false);
					info2audio.setVisible(true);
				}
			}
		});
		

	}
}
