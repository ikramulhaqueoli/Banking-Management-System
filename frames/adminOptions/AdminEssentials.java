package frames.adminOptions;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.nio.file.*;

import repository.*;
import entity.*;
import others.*;
import frames.*;

public class AdminEssentials implements MouseListener, KeyListener
{
	private JLabel fullNameLabel, nidLabel, birthLabel, emailLabel, phoneLabel, passLabel, searchTipLabel, resultsLabel[];
	private JLabel copyright, selectedImageLabel, genderLabel, confirmPassLabel, designationLabel, salaryLabel, showUID;
	private JTextField fullNameTF, nidTF, emailTF, phoneTF, salaryTF, userSearchTF, statementTF;
	private JTextField empIdTF, modifyingEmpNameTF, modifyingEmailTF, modifyingMobileTF, modifyingSalaryTF;
	private JPasswordField passPF, confirmPassPF;
	private JComboBox birthDCB, birthMCB, birthYCB, genderCB;
	private JRadioButton btnAdmin, btnBanker;
	private ButtonGroup typeGroup;
	private JButton addBtn, selectImageButton, searchUserButton, loadButton, refreshButton, updateEmpButton, deleteButton, searchStatementButton;
	private File profilePicture;
	private JOptionPane popupMessage;
	private JPanel panel;
	private JFrame parentFrame;
	private String inputKey;
	private Employee loggedInEmployee, modifyingEmployee;
	public JPanel getSignupFormOnPanel(JPanel panel, JFrame parentFrame)
	{
		panel.setPreferredSize(new Dimension(600, 730));

		this.panel = panel;
		this.parentFrame = parentFrame;
		inputKey = "";
		
		fullNameLabel = new JLabel("Full Name");
		fullNameLabel.setBounds(20, 5, 200, 30);
		fullNameLabel.setFont(Common.customFont1Small);
		fullNameLabel.setForeground(new Color(41,41,41));
		panel.add(fullNameLabel);
		
		fullNameTF = new JTextField();
		fullNameTF.setBounds(20, 35, 495, 30);
		fullNameTF.setFont(Common.customFont1Small);
		fullNameTF.setForeground(Color.BLACK);
		panel.add(fullNameTF);
		
		nidLabel = new JLabel("National ID");
		nidLabel.setBounds(20, 70, 200, 30);
		nidLabel.setFont(Common.customFont1Small);
		nidLabel.setForeground(new Color(41,41,41));
		panel.add(nidLabel);
		
		nidTF = new JTextField();
		nidTF.setBounds(20, 100, 495, 30);
		nidTF.setFont(Common.customFont1Small);
		nidTF.setForeground(Color.BLACK);
		panel.add(nidTF);
		
		birthLabel = new JLabel("Date of Birth");
		birthLabel.setBounds(20, 135, 200, 30);
		birthLabel.setFont(Common.customFont1Small);
		birthLabel.setForeground(new Color(41,41,41));
		panel.add(birthLabel);

		birthDCB = new JComboBox(Common.days);
		birthDCB.setBounds(20, 165, 55, 30);
		birthDCB.setFont(Common.customFont1Small);
		birthDCB.setForeground(Color.BLACK);
		panel.add(birthDCB);

		birthMCB = new JComboBox(Common.months);
		birthMCB.setBounds(80, 165, 125, 30);
		birthMCB.setFont(Common.customFont1Small);
		birthMCB.setForeground(Color.BLACK);
		panel.add(birthMCB);

		birthYCB = new JComboBox(Common.years);
		birthYCB.setBounds(210, 165, 100, 30);
		birthYCB.setFont(Common.customFont1Small);
		birthYCB.setForeground(Color.BLACK);
		panel.add(birthYCB);

		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(350, 135, 190, 30);
		genderLabel.setFont(Common.customFont1Small);
		genderLabel.setForeground(new Color(41,41,41));
		panel.add(genderLabel);

		genderCB = new JComboBox(Common.genders);
		genderCB.setBounds(350, 165, 165, 30);
		genderCB.setFont(Common.customFont1Small);
		genderCB.setForeground(Color.BLACK);
		panel.add(genderCB);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 200, 200, 30);
		emailLabel.setFont(Common.customFont1Small);
		emailLabel.setForeground(new Color(41,41,41));
		panel.add(emailLabel);
		
		emailTF = new JTextField();
		emailTF.setBounds(20, 230, 495, 30);
		emailTF.setFont(Common.customFont1Small);
		emailTF.setForeground(Color.BLACK);
		panel.add(emailTF);
		
		phoneLabel = new JLabel("Mobile No.");
		phoneLabel.setBounds(20, 265, 200, 30);
		phoneLabel.setFont(Common.customFont1Small);
		phoneLabel.setForeground(new Color(41,41,41));
		panel.add(phoneLabel);
		
		phoneTF = new JTextField();
		phoneTF.setBounds(20, 295, 495, 30);
		phoneTF.setFont(Common.customFont1Small);
		phoneTF.setForeground(Color.BLACK);
		panel.add(phoneTF);
		
		passLabel = new JLabel("Password");
		passLabel.setBounds(20, 330, 200, 30);
		passLabel.setFont(Common.customFont1Small);
		passLabel.setForeground(new Color(41,41,41));
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(20, 360, 495, 30);
		passPF.setFont(Common.customFont1Small);
		passPF.setForeground(Color.BLACK);
		panel.add(passPF);
		
		confirmPassLabel = new JLabel("Confirm Password");
		confirmPassLabel.setBounds(20, 400, 200, 30);
		confirmPassLabel.setFont(Common.customFont1Small);
		confirmPassLabel.setForeground(new Color(41,41,41));
		panel.add(confirmPassLabel);
		
		confirmPassPF = new JPasswordField();
		confirmPassPF.setBounds(20, 430, 495, 30);
		confirmPassPF.setFont(Common.customFont1Small);
		confirmPassPF.setForeground(Color.BLACK);
		panel.add(confirmPassPF);

		designationLabel = new JLabel("Designation");
		designationLabel.setBounds(20, 480, 110, 30);
		designationLabel.setFont(Common.customFont1Small);
		designationLabel.setForeground(new Color(41,41,41));
		panel.add(designationLabel);

		btnAdmin = new JRadioButton("Administrator");
		btnAdmin.setBounds(150, 480, 130, 30);
		btnAdmin.setFont(Common.customFont1Small);
		btnAdmin.setForeground(new Color(41,41,41));
		btnAdmin.setOpaque(false);
		panel.add(btnAdmin);

		btnBanker = new JRadioButton("Banker");
		btnBanker.setBounds(310, 480, 130, 30);
		btnBanker.setFont(Common.customFont1Small);
		btnBanker.setForeground(new Color(41,41,41));
		btnBanker.setOpaque(false);
		panel.add(btnBanker);

		typeGroup = new ButtonGroup();
		typeGroup.add(btnAdmin);
		typeGroup.add(btnBanker);

		salaryLabel = new JLabel("Assign salary");
		salaryLabel.setBounds(20, 525, 120, 25);
		salaryLabel.setFont(Common.customFont1Small);
		salaryLabel.setForeground(new Color(41,41,41));
		panel.add(salaryLabel);

		salaryTF = new JTextField();
		salaryTF.setBounds(150, 525, 110, 25);
		salaryTF.setFont(Common.customFont1Smaller);
		salaryTF.setForeground(new Color(41,41,41));
		panel.add(salaryTF);

		selectImageButton = new JButton("Select Profile Image");
		selectImageButton.setBounds(20, 580, 160, 30);
		selectImageButton.setBackground(new Color(221,221,221));
		selectImageButton.setFont(Common.customFont1SmallestBold);
		selectImageButton.addMouseListener(this);
		panel.add(selectImageButton);

		selectedImageLabel = new JLabel(" No image file choosen");
		selectedImageLabel.setBounds(180, 580, 333, 30);
		selectedImageLabel.setOpaque(true);
		selectedImageLabel.setForeground(Color.GRAY);
		selectedImageLabel.setFont(Common.customFont1Smallest);
		panel.add(selectedImageLabel);

		addBtn = new JButton(new ImageIcon("frames/res/add.png"));
		addBtn.setBounds(210, 655, 100, 43);
		addBtn.setBorderPainted(false);
		addBtn.setContentAreaFilled(false);
		addBtn.addMouseListener(this);
		panel.add(addBtn);

		showUID = new JLabel("");
		showUID.setForeground(new Color(0,51,0));
		showUID.setFont(Common.customFont1SmallestBold);
		showUID.setBounds(20, 625, 200, 30);
		panel.add(showUID);

		return panel;
	}

	public void getViewUserOnPanel(JPanel panel, JFrame parentFrame)
	{
		panel.setPreferredSize(new Dimension(530, 30));

		this.panel = panel;
		this.parentFrame = parentFrame;
		inputKey = "";

		userSearchTF = new JTextField();
		userSearchTF.setBounds(15, 20, 450, 27);
		userSearchTF.setFont(Common.customFont1Small);
		userSearchTF.setForeground(new Color(41,41,41));
		userSearchTF.addKeyListener(this);
		panel.add(userSearchTF);

		searchUserButton = new JButton(new ImageIcon("frames/res/search.png"));
		searchUserButton.setBounds(470, 20, 60, 25);
		searchUserButton.setForeground(new Color(41,41,41));
		searchUserButton.setToolTipText("Search Customer by ID/Name/Account Number");
		searchUserButton.setBorderPainted(false);
		searchUserButton.addMouseListener(this);
		searchUserButton.setOpaque(true);
		panel.add(searchUserButton);

		searchTipLabel = new JLabel("Search by User User ID, Name, Phone or Email", SwingConstants.RIGHT);
		searchTipLabel.setBounds(200,2,330,20);
		searchTipLabel.setForeground(new Color(77,77,77));
		searchTipLabel.setFont(Common.customFont1Smallest);
		panel.add(searchTipLabel);

		resultsLabel = new JLabel[1000];
		int colorSwitch = 1;
		int upperY = 55;
		for(int i = 0; i < 1000; i++)
		{
			resultsLabel[i] = new JLabel("", SwingConstants.LEFT);
			resultsLabel[i].setFont(Common.customFont1Smaller);
			resultsLabel[i].setForeground(new Color(0,0,102));
			resultsLabel[i].setOpaque(true);
			//resultsLabel[i].addMouseListener(this);
			if(colorSwitch == 1) resultsLabel[i].setBackground(new Color(204,236,255));
			else resultsLabel[i].setBackground(new Color(151,206,225));
			resultsLabel[i].setToolTipText("");
			colorSwitch *= (-1);
			resultsLabel[i].setBounds(15,upperY,515,27);
			upperY += 27;
			panel.add(resultsLabel[i]);
		}
		showUsers();
	}

	public void getUpdateEmpOnPanel(JPanel panel, JFrame parentFrame)
	{
		JLabel headerLabel, empIdLabel, amountLabel;
		panel.setPreferredSize(new Dimension(530, 300));

		this.panel = panel;
		this.parentFrame = parentFrame;
		this.loggedInEmployee = ((AdministratorFrame)parentFrame).getLoggedInEmployee();

		headerLabel = new JLabel("Manage/Modify Employee");
		headerLabel.setBounds(140, 5, 280, 50);
		headerLabel.setFont(Common.customFont1SmallBold);
		headerLabel.setForeground(new Color(0,0,102));
		panel.add(headerLabel);

		empIdLabel = new JLabel("Employee User ID");
		empIdLabel.setBounds(20, 70, 260, 20);
		empIdLabel.setFont(Common.customFont1SmallerBold);
		panel.add(empIdLabel);

		empIdTF = new JTextField();
		empIdTF.setBounds(20, 100, 140, 25);
		empIdTF.setFont(Common.customFont1SmallerBold);
		panel.add(empIdTF);

		loadButton = new JButton("Load");
		loadButton.setForeground(new Color(221,221,221));
		loadButton.setBackground(new Color(0,51,0));
		loadButton.setFont(Common.customFont1SmallestBold);
		loadButton.setContentAreaFilled(false);
		loadButton.setOpaque(true);
		loadButton.setBorderPainted(false);
		loadButton.setBounds(20, 135, 90, 25);
		loadButton.addMouseListener(this);
		panel.add(loadButton);

		refreshButton = new JButton("Refresh");
		refreshButton.setForeground(new Color(221,221,221));
		refreshButton.setBackground(Color.GRAY);
		refreshButton.setFont(Common.customFont1SmallestBold);
		refreshButton.setContentAreaFilled(false);
		refreshButton.setOpaque(true);
		refreshButton.setBorderPainted(false);
		refreshButton.setBounds(20, 165, 90, 25);
		refreshButton.addMouseListener(this);
		panel.add(refreshButton);

		JLabel modNameLabel = new JLabel("Name");
		modNameLabel.setBounds(170,70,90,20);
		modNameLabel.setForeground(new Color(51,51,51));
		modNameLabel.setFont(Common.customFont1Smaller);
		panel.add(modNameLabel);

		modifyingEmpNameTF = new JTextField();
		modifyingEmpNameTF.setBounds(170,95,300,25);
		modifyingEmpNameTF.setFont(Common.customFont1SmallerBold);
		modifyingEmpNameTF.setEnabled(false);
		panel.add(modifyingEmpNameTF);

		JLabel modEmailLabel = new JLabel("Email");
		modEmailLabel.setBounds(170,130,90,20);
		modEmailLabel.setFont(Common.customFont1Smaller);
		panel.add(modEmailLabel);

		modifyingEmailTF = new JTextField();
		modifyingEmailTF.setBounds(170,155,300,25);
		modifyingEmailTF.setFont(Common.customFont1SmallerBold);
		modifyingEmailTF.setEnabled(false);
		panel.add(modifyingEmailTF);

		JLabel modMobileLabel = new JLabel("Mobile");
		modMobileLabel.setBounds(170,190,90,20);
		modMobileLabel.setForeground(new Color(51,51,51));
		modMobileLabel.setFont(Common.customFont1Smaller);
		panel.add(modMobileLabel);

		modifyingMobileTF = new JTextField();
		modifyingMobileTF.setBounds(170,215,300,25);
		modifyingMobileTF.setFont(Common.customFont1SmallerBold);
		modifyingMobileTF.setEnabled(false);
		panel.add(modifyingMobileTF);

		JLabel modSalaryLabel = new JLabel("New Salary");
		modSalaryLabel.setBounds(170,250,90,20);
		modSalaryLabel.setForeground(new Color(51,51,51));
		modSalaryLabel.setFont(Common.customFont1Smaller);
		panel.add(modSalaryLabel);

		modifyingSalaryTF = new JTextField();
		modifyingSalaryTF.setBounds(170,275,300,25);
		modifyingSalaryTF.setFont(Common.customFont1SmallerBold);
		panel.add(modifyingSalaryTF);

		JLabel modEmpTypeLabel = new JLabel("Modify Employee Type");
		modEmpTypeLabel.setBounds(170,310,180,20);
		modEmpTypeLabel.setForeground(new Color(51,51,51));
		modEmpTypeLabel.setFont(Common.customFont1Smaller);
		panel.add(modEmpTypeLabel);

		btnAdmin = new JRadioButton("Administrator");
		btnAdmin.setBounds(170,335,150,20);
		btnAdmin.setFont(Common.customFont1Smaller);
		btnAdmin.setOpaque(false);
		panel.add(btnAdmin);

		btnBanker = new JRadioButton("Banker");
		btnBanker.setBounds(330,335,150,20);
		btnBanker.setFont(Common.customFont1Smaller);
		btnBanker.setOpaque(false);
		panel.add(btnBanker);

		typeGroup = new ButtonGroup();
		typeGroup.add(btnAdmin);
		typeGroup.add(btnBanker);

		updateEmpButton = new JButton("Update Employee");
		updateEmpButton.setForeground(new Color(221,221,221));
		updateEmpButton.setBackground(new Color(0,51,0));
		updateEmpButton.setFont(Common.customFont1SmallestBold);
		updateEmpButton.setContentAreaFilled(false);
		updateEmpButton.setOpaque(true);
		updateEmpButton.setBorderPainted(false);
		updateEmpButton.setBounds(170, 375, 130, 30);
		updateEmpButton.addMouseListener(this);
		panel.add(updateEmpButton);

		deleteButton = new JButton("Delete Employee");
		deleteButton.setForeground(new Color(221,221,221));
		deleteButton.setBackground(new Color(153,0,51));
		deleteButton.setFont(Common.customFont1SmallestBold);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setOpaque(true);
		deleteButton.setBorderPainted(false);
		deleteButton.setBounds(310, 375, 130, 30);
		deleteButton.addMouseListener(this);
		panel.add(deleteButton);
	}

	public void showUsers()
	{
		for(int i = 0; i < 500; i++)
		{
			resultsLabel[i].setText("");
			resultsLabel[i].setToolTipText("");
		}
		User foundUsers[] = (new UserRepository()).getUsers(inputKey);
		if(foundUsers == null) return;
		int dimY = 54;
		for(int i = 0, j = 0; i < foundUsers.length && i < 500; i++, j+=2)
		{
			if(foundUsers[i] == null) continue;
			dimY += (27<<1);
			panel.setPreferredSize(new Dimension(800, dimY));
			resultsLabel[j].setText("  ID: "+foundUsers[i].getUserId()+"   |   Name: "+foundUsers[i].getName()+"   |   M: "+foundUsers[i].getPhone());
			resultsLabel[j].setToolTipText(foundUsers[i].getUserId());
		}
	}

	public void getStatementOnPanel(JPanel panel, JFrame parentFrame)
	{
		this.panel = panel;
		this.parentFrame = parentFrame;
		inputKey = "";
		this.panel.setPreferredSize(new Dimension(900, 300));

		statementTF = new JTextField();
		statementTF.setBounds(25, 20, 440, 27);
		statementTF.setFont(Common.customFont1Small);
		statementTF.setForeground(new Color(41,41,41));
		statementTF.addKeyListener(this);
		panel.add(statementTF);

		searchStatementButton = new JButton(new ImageIcon("frames/res/search.png"));
		searchStatementButton.setBounds(470, 20, 60, 25);
		searchStatementButton.setForeground(new Color(41,41,41));
		searchStatementButton.setToolTipText("Search Statement by Account Number/Transaction ID");
		searchStatementButton.setBorderPainted(false);
		searchStatementButton.addMouseListener(this);
		searchStatementButton.setOpaque(true);
		panel.add(searchStatementButton);

		searchTipLabel = new JLabel("Search Statements by Account Number", SwingConstants.RIGHT);
		searchTipLabel.setBounds(200,2,330,20);
		searchTipLabel.setForeground(new Color(77,77,77));
		searchTipLabel.setFont(Common.customFont1Smallest);
		panel.add(searchTipLabel);

		resultsLabel = new JLabel[1000];
		int colorSwitch = 1;
		int upperY = 55;

		for(int i = 0; i < 1000; i++)
		{
			resultsLabel[i] = new JLabel("", SwingConstants.LEFT);
			resultsLabel[i].setFont(Common.customFont1Smaller);
			resultsLabel[i].setForeground(new Color(0,0,102));
			resultsLabel[i].setOpaque(true);
			resultsLabel[i].addMouseListener(this);
			if(colorSwitch == 1) resultsLabel[i].setBackground(new Color(204,236,255));
			else resultsLabel[i].setBackground(new Color(151,206,225));
			resultsLabel[i].setToolTipText("");
			colorSwitch *= (-1);
			resultsLabel[i].setBounds(25,upperY,750,27);
			upperY += 27;
			panel.add(resultsLabel[i]);
		}
		showStatements();
	}
	public void showStatements()
	{
		for(int i = 0; i < 500; i++)
		{
			resultsLabel[i].setText("");
			resultsLabel[i].setToolTipText("");
		}
		Transaction foundTransactions[] = (new TransactionRepository()).getTransactions(inputKey);
		if(foundTransactions == null) return;
		int dimY = 54;
		for(int i = 0, j = 0; j < foundTransactions.length && i < 500; i++, j+=2)
		{
			if(foundTransactions[j] == null) continue;
			dimY += (27<<1);
			resultsLabel[j].setText("   txnID: "+foundTransactions[i].getTxnId()+" | From Acc: "+foundTransactions[i].getTargetAccount()+" | To Acc: "+foundTransactions[i].getSourceAccount()+" | Type: "+foundTransactions[i].getTransactionType()+" | Amount: "+foundTransactions[i].getAmount()+" | Officer: "+foundTransactions[i].getOfficerId());
			panel.setPreferredSize(new Dimension(800, dimY));
		}
	}
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource() == addBtn)
		{
			String report, userId, name, nid, birthDate, gender, email, mobile, password, designation;
			Double salary;
			File profileImage;

			name = fullNameTF.getText();
			nid = nidTF.getText();

			birthDate = "";
			birthDate += Common.years[birthYCB.getSelectedIndex()];
			birthDate += "-"+(birthMCB.getSelectedIndex()+1)+"-";
			birthDate += Common.days[birthDCB.getSelectedIndex()];

			gender = Common.genders[genderCB.getSelectedIndex()];
			email = emailTF.getText();
			mobile = phoneTF.getText();
			password = passPF.getText();

			if(btnAdmin.isSelected()) designation = btnAdmin.getText();
			else if(btnBanker.isSelected()) designation = btnBanker.getText();
			else designation = null;

			popupMessage = new JOptionPane();

			try
			{
				salary = Double.parseDouble(salaryTF.getText());
			}
			catch(Exception ex)
			{
				popupMessage.showMessageDialog(panel, "Invalid Salary Input!", "Salary Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}

			report = Common.nameValidity(name);
			if(report != null)
			{
				popupMessage.showMessageDialog(panel, report, "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			report = Common.nidValidity(nid);
			if(report != null)
			{
				popupMessage.showMessageDialog(panel, report, "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			report = Common.emailValidity(email);
			if(report != null)
			{
				popupMessage.showMessageDialog(panel, report, "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			report = Common.phoneValidity(mobile);
			if(report != null)
			{
				popupMessage.showMessageDialog(panel, report, "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			report = Common.nameValidity(name);
			if(report != null)
			{
				popupMessage.showMessageDialog(panel, report, "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			if(designation == null)
			{
				popupMessage.showMessageDialog(panel, "Sorry!\nYou must select a designation", "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			report = Common.passwordValidity(password, confirmPassPF.getText());
			if(report != null)
			{
				popupMessage.showMessageDialog(panel, report, "Input Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}

			userId = "E-";
			if(btnAdmin.isSelected()) userId += 'A';
			else if(btnBanker.isSelected()) userId += 'B';
			String joinDate = Common.getTodayDate();
			if(joinDate == null)
			{
				popupMessage.showMessageDialog(panel, "Sorry\nDatabase Connection Failed!", "Error!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			Employee emp = new Employee(userId, name, nid, birthDate, gender, email, mobile, designation, joinDate, salary, password);
				
			EmployeeRepository empRepo = new EmployeeRepository();
			String message = empRepo.insertEmployee(emp);

			if(!(message.substring(0,3).equals("Err")))
			{
				boolean imageStored = false;
				if(profilePicture != null)
				{
					try
					{
						File sourceFile = profilePicture;
						File storeFile = new File("others/data/"+message+".jpg");
						Files.copy(sourceFile.toPath(), storeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						imageStored = true;
					}
					catch(Exception ex) {}
				}
				showUID.setText("Employee's user ID is: "+message);

				String notify = "Welcome, new member "+name+"!\nRegistered Successfully.\nYour User ID is " + message+".\n";
				if(!imageStored) notify += "Profile image not saved";
				popupMessage.showMessageDialog(panel, notify, "Registered Successfully!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				return;
			}
			else
			{
				popupMessage.showMessageDialog(panel, "Sorry!\n"+message, "Registration Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
		}
		else if(me.getSource() == selectImageButton)
		{
			System.out.println("Clicked on selectImageButton");
			profilePicture = Common.chooseFile(Common.IMAGE_TYPE, parentFrame);
			if(profilePicture != null) selectedImageLabel.setText(" "+profilePicture);
			else selectedImageLabel.setText("No image file selected");
		}
		else if(me.getSource() == searchUserButton)
		{
			inputKey = userSearchTF.getText();
			showUsers();
		}
		else if(me.getSource() == loadButton)
		{
			modifyingEmployee = (new EmployeeRepository()).getEmployee(empIdTF.getText());
			if(modifyingEmployee == null)
			{
				popupMessage = new JOptionPane();
				popupMessage.showMessageDialog(panel, "Error: Employee not found!", "Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			modifyingEmpNameTF.setText(modifyingEmployee.getName());
			modifyingEmailTF.setText(modifyingEmployee.getEmail());
			modifyingMobileTF.setText(modifyingEmployee.getPhone());
			modifyingSalaryTF.setText(""+modifyingEmployee.getSalary());
			if(modifyingEmployee.getDesignation().equals("Administrator")) btnAdmin.setSelected(true);
			else if(modifyingEmployee.getDesignation().equals("Banker")) btnBanker.setSelected(true);
		}
		else if(me.getSource() == refreshButton)
		{
			empIdTF.setText("");
			modifyingEmpNameTF.setText("");
			modifyingEmailTF.setText("");
			modifyingMobileTF.setText("");
			modifyingSalaryTF.setText("");
			typeGroup.clearSelection();
		}
		else if(me.getSource() == updateEmpButton)
		{
			try
			{
				if(modifyingSalaryTF.getText() == null || modifyingSalaryTF.getText().equals(""))
				{
					popupMessage = new JOptionPane();
					popupMessage.showMessageDialog(panel, "Invalid Salary Input!", "Invalid salary!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				double salary = Double.parseDouble(modifyingSalaryTF.getText());
				if(salary < 1.0)
				{
					popupMessage = new JOptionPane();
					popupMessage.showMessageDialog(panel, "Invalid Salary Input!", "Invalid salary!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				modifyingEmployee.setSalary(salary);
				if(btnAdmin.isSelected()) modifyingEmployee.setDesignation(btnAdmin.getText());
				else if(btnBanker.isSelected()) modifyingEmployee.setDesignation(btnBanker.getText());
				else
				{
					popupMessage = new JOptionPane();
					popupMessage.showMessageDialog(panel, "Designation must be choosen!", "Designation not choosed", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				String report = (new EmployeeRepository()).updateEmployee(modifyingEmployee);
				if(report != null)
				{
					popupMessage = new JOptionPane();
					popupMessage.showMessageDialog(panel, "Sorry!\n"+report, "Failed to update!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				popupMessage = new JOptionPane();
				popupMessage.showMessageDialog(panel, "Updated Employee informations successfully! ", "Updated Successfully!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				return;
			}
			catch(Exception ex)
			{
				System.out.println("Exception in updateEmployee() " + ex.getMessage());
			}
		}
		else if(me.getSource() == deleteButton)
		{
			String uid = empIdTF.getText();
			if(uid == null || uid.equals(""))
			{
				popupMessage = new JOptionPane();
				popupMessage.showMessageDialog(panel, "No user id found!", "Deletion unsuccessful!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			modifyingEmployee = (new EmployeeRepository()).getEmployee(uid);
			String report = (new EmployeeRepository()).deleteEmployee(modifyingEmployee.getUserId());
			if(report != null)
			{
				popupMessage = new JOptionPane();
				popupMessage.showMessageDialog(panel, "Sorry!\n"+report, "Failed to delete!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			popupMessage = new JOptionPane();
			popupMessage.showMessageDialog(panel, "Employee has been deleted from the System", "Deleted Successfully!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
			return;
		}
		else if(me.getSource() == statementTF)
		{
			inputKey = statementTF.getText();
			showStatements();
		}
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == searchUserButton)
		{
			searchUserButton.setIcon(new ImageIcon("frames/res/searchentered.png"));
		}
		else if(me.getSource() == loadButton)
		{
			loadButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == refreshButton)
		{
			refreshButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == updateEmpButton)
		{
			updateEmpButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == deleteButton)
		{
			deleteButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == searchStatementButton)
		{
			searchStatementButton.setIcon(new ImageIcon("frames/res/searchentered.png"));
		}
	}
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == searchUserButton)
		{
			searchUserButton.setIcon(new ImageIcon("frames/res/search.png"));
		}
		else if(me.getSource() == loadButton)
		{
			loadButton.setBackground(new Color(0,51,0));
		}
		else if(me.getSource() == refreshButton)
		{
			refreshButton.setBackground(Color.GRAY);
		}
		else if(me.getSource() == updateEmpButton)
		{
			updateEmpButton.setBackground(new Color(0,51,0));
		}
		else if(me.getSource() == deleteButton)
		{
			deleteButton.setBackground(new Color(153,0,51));
		}
		else if(me.getSource() == searchStatementButton)
		{
			searchStatementButton.setIcon(new ImageIcon("frames/res/search.png"));
		}
	}
	public void keyTyped(KeyEvent ke)
	{
		if(ke.getSource() == statementTF)
		{
			inputKey = statementTF.getText();
			showStatements();
		}
	}
	public void keyPressed(KeyEvent ke) {}
	public void keyReleased(KeyEvent ke) {}
}