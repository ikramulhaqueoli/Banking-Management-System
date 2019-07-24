package frames;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.nio.file.*;
import repository.*;
import entity.*;
import others.*;

public class SignupFrame extends JFrame implements ActionListener
{
	private JLabel fullNameLabel, nidLabel, birthLabel, emailLabel, phoneLabel, passLabel;
	private JLabel copyright, selectedImageLabel, genderLabel, confirmPassLabel, typeLabel, showUID;
	private JTextField fullNameTF, nidTF, emailTF, phoneTF;
	private JPasswordField passPF, confirmPassPF;
	private JComboBox birthDCB, birthMCB, birthYCB, genderCB;
	private JPanel panel;
	private JRadioButton btnSavings, btnOverdraft;
	private ButtonGroup typeGroup;
	private JButton singUpbtn, backBtn, selectImageButton;
	private File profilePicture;
	private JOptionPane popupMessage;
	
	public SignupFrame(LoginFrame lframe)
	{
		super("Sign Up | Amader Bank");
		this.setSize(600, 900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));
		
		fullNameLabel = new JLabel("Full Name");
		fullNameLabel.setBounds(20, 5, 200, 35);
		fullNameLabel.setFont(Common.customFont1Small);
		fullNameLabel.setForeground(new Color(41,41,41));
		panel.add(fullNameLabel);
		
		fullNameTF = new JTextField();
		fullNameTF.setBounds(20, 35, 560, 35);
		fullNameTF.setFont(Common.customFont1Small);
		fullNameTF.setForeground(Color.BLACK);
		panel.add(fullNameTF);
		
		nidLabel = new JLabel("National ID");
		nidLabel.setBounds(20, 85, 200, 35);
		nidLabel.setFont(Common.customFont1Small);
		nidLabel.setForeground(new Color(41,41,41));
		panel.add(nidLabel);
		
		nidTF = new JTextField();
		nidTF.setBounds(20, 115, 560, 35);
		nidTF.setFont(Common.customFont1Small);
		nidTF.setForeground(Color.BLACK);
		panel.add(nidTF);
		
		birthLabel = new JLabel("Date of Birth");
		birthLabel.setBounds(20, 170, 200, 35);
		birthLabel.setFont(Common.customFont1Small);
		birthLabel.setForeground(new Color(41,41,41));
		panel.add(birthLabel);

		birthDCB = new JComboBox(Common.days);
		birthDCB.setBounds(20, 200, 65, 35);
		birthDCB.setFont(Common.customFont1Small);
		birthDCB.setForeground(Color.BLACK);
		panel.add(birthDCB);

		birthMCB = new JComboBox(Common.months);
		birthMCB.setBounds(90, 200, 135, 35);
		birthMCB.setFont(Common.customFont1Small);
		birthMCB.setForeground(Color.BLACK);
		panel.add(birthMCB);

		birthYCB = new JComboBox(Common.years);
		birthYCB.setBounds(230, 200, 120, 35);
		birthYCB.setFont(Common.customFont1Small);
		birthYCB.setForeground(Color.BLACK);
		panel.add(birthYCB);

		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(400, 170, 200, 35);
		genderLabel.setFont(Common.customFont1Small);
		genderLabel.setForeground(new Color(41,41,41));
		panel.add(genderLabel);

		genderCB = new JComboBox(Common.genders);
		genderCB.setBounds(400, 200, 175, 35);
		genderCB.setFont(Common.customFont1Small);
		genderCB.setForeground(Color.BLACK);
		panel.add(genderCB);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 255, 200, 35);
		emailLabel.setFont(Common.customFont1Small);
		emailLabel.setForeground(new Color(41,41,41));
		panel.add(emailLabel);
		
		emailTF = new JTextField();
		emailTF.setBounds(20, 285, 560, 35);
		emailTF.setFont(Common.customFont1Small);
		emailTF.setForeground(Color.BLACK);
		panel.add(emailTF);
		
		phoneLabel = new JLabel("Mobile No.");
		phoneLabel.setBounds(20, 340, 200, 35);
		phoneLabel.setFont(Common.customFont1Small);
		phoneLabel.setForeground(new Color(41,41,41));
		panel.add(phoneLabel);
		
		phoneTF = new JTextField();
		phoneTF.setBounds(20, 370, 560, 35);
		phoneTF.setFont(Common.customFont1Small);
		phoneTF.setForeground(Color.BLACK);
		panel.add(phoneTF);
		
		passLabel = new JLabel("Password");
		passLabel.setBounds(20, 425, 200, 35);
		passLabel.setFont(Common.customFont1Small);
		passLabel.setForeground(new Color(41,41,41));
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(20, 455, 560, 35);
		passPF.setFont(Common.customFont1Small);
		passPF.setForeground(Color.BLACK);
		panel.add(passPF);
		
		confirmPassLabel = new JLabel("Confirm Password");
		confirmPassLabel.setBounds(20, 510, 200, 35);
		confirmPassLabel.setFont(Common.customFont1Small);
		confirmPassLabel.setForeground(new Color(41,41,41));
		panel.add(confirmPassLabel);
		
		confirmPassPF = new JPasswordField();
		confirmPassPF.setBounds(20, 540, 560, 35);
		confirmPassPF.setFont(Common.customFont1Small);
		confirmPassPF.setForeground(Color.BLACK);
		panel.add(confirmPassPF);

		typeLabel = new JLabel("Account Type");
		typeLabel.setBounds(20, 585, 110, 35);
		typeLabel.setFont(Common.customFont1Smaller);
		typeLabel.setForeground(new Color(41,41,41));
		panel.add(typeLabel);

		btnSavings = new JRadioButton("Savings");
		btnSavings.setBounds(180, 585, 100, 35);
		btnSavings.setFont(Common.customFont1Smaller);
		btnSavings.setForeground(new Color(41,41,41));
		btnSavings.setOpaque(false);
		panel.add(btnSavings);

		btnOverdraft = new JRadioButton("Overdraft");
		btnOverdraft.setBounds(300, 585, 100, 35);
		btnOverdraft.setFont(Common.customFont1Smaller);
		btnOverdraft.setForeground(new Color(41,41,41));
		btnOverdraft.setOpaque(false);
		panel.add(btnOverdraft);

		typeGroup = new ButtonGroup();
		typeGroup.add(btnSavings);
		typeGroup.add(btnOverdraft);

		selectImageButton = new JButton("Select Profile Image");
		selectImageButton.setBounds(20, 635, 160, 30);
		selectImageButton.setBackground(new Color(221,221,221));
		selectImageButton.setFont(Common.customFont1SmallestBold);
		selectImageButton.addActionListener(this);
		panel.add(selectImageButton);

		selectedImageLabel = new JLabel(" No image file choosen");
		selectedImageLabel.setBounds(180, 635, 400, 30);
		selectedImageLabel.setOpaque(true);
		selectedImageLabel.setForeground(Color.GRAY);
		selectedImageLabel.setFont(Common.customFont1Smallest);
		panel.add(selectedImageLabel);

		singUpbtn = new JButton(new ImageIcon("frames/res/signup.png"));
		singUpbtn.setBounds(225, 680, 100, 43);
		singUpbtn.setBorderPainted(false);
		singUpbtn.setContentAreaFilled(false);
		singUpbtn.addActionListener(this);
		panel.add(singUpbtn);

		showUID = new JLabel("");
		showUID.setForeground(new Color(0,128,0));
		showUID.setFont(Common.customFont1SmallestBold);
		showUID.setBounds(20, 685, 200, 30);
		panel.add(showUID);

		backBtn = new JButton(new ImageIcon("frames/res/back.png"));
		backBtn.setBounds(245, 745, 60, 60);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		copyright = new JLabel("Copyright © 2019 Amader Bank Limited. All rights reserved.");
		copyright.setFont(Common.customFont1Smallest);
		copyright.setForeground(new Color(95, 95, 95));
		copyright.setBounds(130, 835, 400, 20);
		panel.add(copyright);
		
		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == selectImageButton)
		{
			profilePicture = Common.chooseFile(Common.IMAGE_TYPE, this);
			if(profilePicture != null) selectedImageLabel.setText(" "+profilePicture);
			else selectedImageLabel.setText("No image file selected");
		}
		if(ae.getSource() == singUpbtn)
		{
			String userId, name, nid, birthDate, gender, email, phone, accountType, accountNumber, password;
			Double balance;
			userId = "C-";
			if(btnSavings.isSelected()) userId += 'S';
			else if(btnOverdraft.isSelected()) userId += 'D';

			name = fullNameTF.getText();
			nid = nidTF.getText();

			birthDate = "";
			birthDate += Common.years[birthYCB.getSelectedIndex()];
			birthDate += "-"+(birthMCB.getSelectedIndex()+1)+"-";
			birthDate += Common.days[birthDCB.getSelectedIndex()];

			gender = Common.genders[genderCB.getSelectedIndex()];
			
			email = emailTF.getText().toLowerCase();
			phone = phoneTF.getText();

			password = passPF.getText();

			if(btnSavings.isSelected()) accountType = btnSavings.getText();
			else if(btnOverdraft.isSelected()) accountType = btnOverdraft.getText();
			else accountType = null;

			accountNumber = "";
			balance = 0.0;

			popupMessage = new JOptionPane();

			String nameError = Common.nameValidity(name);
			if(nameError != null)
			{
				popupMessage.showMessageDialog(panel, nameError, "Invalid Name", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			String nidError = Common.nidValidity(nid);
			if(nidError != null)
			{
				popupMessage.showMessageDialog(panel, nidError, "Invalid NID", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			String emailError = Common.emailValidity(email);
			if(emailError != null)
			{
				popupMessage.showMessageDialog(panel, emailError, "Invalid Email", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			String phoneError = Common.phoneValidity(phone);
			if(phoneError != null)
			{
				popupMessage.showMessageDialog(panel, phoneError, "Invalid Mobile No.", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			String passwordError = Common.passwordValidity(passPF.getText(), confirmPassPF.getText());
			if(passwordError != null)
			{
				popupMessage.showMessageDialog(panel, passwordError, "Invalid Password", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			if(accountType == null)
			{
				popupMessage.showMessageDialog(panel, "You must select account type", "Select Account type", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			String imageError = null;
			if(profilePicture != null) Common.profileImageValidity(profilePicture);
			if(imageError != null)
			{
				popupMessage.showMessageDialog(panel, imageError, "Invalid image file", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			
			Customer customer = new Customer(userId, name, nid, birthDate, gender, email, phone, accountType, accountNumber, balance, password);
			
			CustomerRepository customerRepo = new CustomerRepository();
			String[] message = customerRepo.insertCustomer(customer);

			if(message.length > 1)
			{
				boolean imageStored = false;
				if(profilePicture != null)
				{
					try
					{
						File sourceFile = profilePicture;
						File storeFile = new File("others/data/"+message[0]+".jpg");
						Files.copy(sourceFile.toPath(), storeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						imageStored = true;
					}
					catch(Exception ex) {}
				}
				showUID.setText("Your user ID is: "+message[0]);

				String notify = "Welcome, "+name+"!\nRegistered Successfully.\nYour User ID is " + message[0] + ".\nYour Bank Account Number is "+message[1]+".\n";
				if(!imageStored) notify += "Profile image not saved";
				popupMessage.showMessageDialog(panel, notify, "Registered Successfully!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				return;
			}
			else
			{
				popupMessage.showMessageDialog(panel, "Sorry!\n"+message[0], "Registration Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
		}
		else if(ae.getSource() == backBtn)
		{
			Common.goToLogin(this);
		}
	}
}