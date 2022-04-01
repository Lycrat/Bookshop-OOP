import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox; 

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private boolean logged;
	private List<Users> listOfUsers = new ArrayList<>();
	private boolean sameUser;
	enum logType{
		notLogged, userLogged, adminLogged;
	}
	Users user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		logged = false;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(92, 102, 78, 14);
		contentPane.add(lblNewLabel);
		
		
		try {
			String line;
			BufferedReader userReader = new BufferedReader(new FileReader("src/UserAccounts.txt"));
			while((line = userReader.readLine()) != null) {
				
				String[] data = line.split(",");
				
				Users currentUser = new Users(data[1].trim(), Integer.parseInt(data[0].trim()), data[2], Integer.parseInt(data[3].trim()), data[4].trim(), data[5].trim(), data[6].trim());
				
				listOfUsers.add(currentUser);
			}
			userReader.close();
		}catch(IOException e1) {
			System.err.println(e1.getMessage());
		}
		
		
		JComboBox userBox = new JComboBox(ListUsernames.values());
		userBox.setBounds(158, 99, 78, 20);
		contentPane.add(userBox);
		
		JButton btnSubmit = new JButton("Login");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logType log = logType.notLogged;
				
				
							
				System.out.println(listOfUsers);
				for(Users eachUser : listOfUsers) {
					if((userBox.getSelectedItem().toString().equals(eachUser.getUsername())) && (eachUser.getRole().equals("customer"))) {
						user = eachUser;
						log = logType.userLogged;
					}
					else if((userBox.getSelectedItem().toString().equals(eachUser.getUsername())) && (eachUser.getRole().equals("admin"))) {
						user = eachUser;
						log = logType.adminLogged;
					}
				}
				switch(log) {
					case notLogged:
						JOptionPane.showMessageDialog(null, "Wrong Username","ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					case userLogged:
						JOptionPane.showMessageDialog(null, (user.getSurname().trim()) + " Logged in Successfully");
						SecondFrame scf = new SecondFrame(user);
						setVisible(false);
						scf.setVisible(true);
						break;
					case adminLogged:
						JOptionPane.showMessageDialog(null, (user.getSurname().trim()) + " Logged in Successfully as ADMIN");
						AdminFrame ad = new AdminFrame(user);
						setVisible(false);
						ad.setVisible(true);
						break;
				}
					
			}
		});
		
		btnSubmit.setBounds(158, 130, 89, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblNewLabel_2 = new JLabel("Library System");
		lblNewLabel_2.setBounds(161, 24, 86, 47);
		contentPane.add(lblNewLabel_2);
		

	}
}
