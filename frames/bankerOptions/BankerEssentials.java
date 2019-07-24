package frames.bankerOptions;

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

public class BankerEssentials implements MouseListener, KeyListener
{
	private JPanel panel;
	private JLabel searchTipLabel, uidLabel, emailLabel, phoneLabel, nid, birthdate, gender, email, mobile, resultsLabel[];
	private JTextField userSearchTF, statementTF, depositTargetAccountTF, depositAmountTF, withdrawAmountTF, withdrawSourceAccountTF;
	private JButton searchCustomerButton, searchStatementButton, changeAvatarButton, backButton, viewSalary;
	private JButton dialogBackButton, showBalanceButton;
	private JButton depositNowButton, withdrawNowButton;
	private Employee employee;
	private BankerFrame bframe;
	private JOptionPane warning;
	private String inputKey;
	private JFrame parentFrame;
	private JDialog profileDialog;
	private Customer showingCustomer;

	public void getViewCustomerOnPanel(JPanel panel, JFrame parentFrame)
	{
		panel.setPreferredSize(new Dimension(530, 30));

		this.panel = panel;
		this.parentFrame = parentFrame;
		inputKey = "";

		userSearchTF = new JTextField();
		userSearchTF.setBounds(25, 20, 440, 27);
		userSearchTF.setFont(Common.customFont1Small);
		userSearchTF.setForeground(new Color(41,41,41));
		userSearchTF.addKeyListener(this);
		panel.add(userSearchTF);

		searchCustomerButton = new JButton(new ImageIcon("frames/res/search.png"));
		searchCustomerButton.setBounds(470, 20, 60, 25);
		searchCustomerButton.setForeground(new Color(41,41,41));
		searchCustomerButton.setToolTipText("Search Customer by ID/Name/Account Number");
		searchCustomerButton.setBorderPainted(false);
		searchCustomerButton.addMouseListener(this);
		searchCustomerButton.setOpaque(true);
		panel.add(searchCustomerButton);

		searchTipLabel = new JLabel("Search by Customer User ID, Name or Account Number", SwingConstants.RIGHT);
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
			resultsLabel[i].setBounds(25,upperY,505,27);
			upperY += 27;
			panel.add(resultsLabel[i]);
		}
		showCustomers();
	}

	public void getDepositOnPanel(JPanel panel, JFrame parentFrame)
	{
		JLabel headerLabel, targetAccountLabel, amountLabel;
		panel.setPreferredSize(new Dimension(530, 300));

		this.panel = panel;
		this.parentFrame = parentFrame;
		this.employee = ((BankerFrame)parentFrame).getLoggedInEmployee();

		headerLabel = new JLabel("Customer Deposit");
		headerLabel.setBounds(165, 10, 260, 50);
		headerLabel.setFont(Common.customFont1Bold);
		headerLabel.setForeground(new Color(0,0,102));
		panel.add(headerLabel);

		targetAccountLabel = new JLabel("Customer Account Number");
		targetAccountLabel.setBounds(20, 70, 260, 30);
		targetAccountLabel.setFont(Common.customFont1SmallBold);
		panel.add(targetAccountLabel);

		depositTargetAccountTF = new JTextField();
		depositTargetAccountTF.setBounds(20, 110, 505, 40);
		depositTargetAccountTF.setFont(Common.customFont1SmallBold);
		panel.add(depositTargetAccountTF);

		amountLabel = new JLabel("Amount");
		amountLabel.setBounds(20, 160, 200, 30);
		amountLabel.setFont(Common.customFont1SmallBold);
		panel.add(amountLabel);

		depositAmountTF = new JTextField();
		depositAmountTF.setBounds(20, 200, 505, 40);
		depositAmountTF.setFont(Common.customFont1Small);
		panel.add(depositAmountTF);

		depositNowButton = new JButton("Deposit");
		depositNowButton.setForeground(new Color(221,221,221));
		depositNowButton.setBackground(new Color(0,51,0));
		depositNowButton.setFont(Common.customFont1SmallBold);
		depositNowButton.setContentAreaFilled(false);
		depositNowButton.setOpaque(true);
		depositNowButton.setBorderPainted(false);
		depositNowButton.setBounds(200, 330, 120, 40);
		depositNowButton.addMouseListener(this);
		panel.add(depositNowButton);
	}

	public void getWithdrawOnPanel(JPanel panel, JFrame parentFrame)
	{
		JLabel headerLabel, targetAccountLabel, amountLabel;
		panel.setPreferredSize(new Dimension(530, 300));

		this.panel = panel;
		this.parentFrame = parentFrame;
		this.employee = ((BankerFrame)parentFrame).getLoggedInEmployee();

		headerLabel = new JLabel("Customer Withdrawal");
		headerLabel.setBounds(150, 10, 280, 50);
		headerLabel.setFont(Common.customFont1Bold);
		headerLabel.setForeground(new Color(0,0,102));
		panel.add(headerLabel);

		targetAccountLabel = new JLabel("Customer Account Number");
		targetAccountLabel.setBounds(20, 70, 260, 30);
		targetAccountLabel.setFont(Common.customFont1SmallBold);
		panel.add(targetAccountLabel);

		withdrawSourceAccountTF = new JTextField();
		withdrawSourceAccountTF.setBounds(20, 110, 505, 40);
		withdrawSourceAccountTF.setFont(Common.customFont1SmallBold);
		panel.add(withdrawSourceAccountTF);

		amountLabel = new JLabel("Amount");
		amountLabel.setBounds(20, 160, 200, 30);
		amountLabel.setFont(Common.customFont1SmallBold);
		panel.add(amountLabel);

		withdrawAmountTF = new JTextField();
		withdrawAmountTF.setBounds(20, 200, 505, 40);
		withdrawAmountTF.setFont(Common.customFont1Small);
		panel.add(withdrawAmountTF);

		withdrawNowButton = new JButton("Withdraw");
		withdrawNowButton.setForeground(new Color(221,221,221));
		withdrawNowButton.setBackground(new Color(0,51,0));
		withdrawNowButton.setFont(Common.customFont1SmallBold);
		withdrawNowButton.setContentAreaFilled(false);
		withdrawNowButton.setOpaque(true);
		withdrawNowButton.setBorderPainted(false);
		withdrawNowButton.setBounds(200, 330, 120, 40);
		withdrawNowButton.addMouseListener(this);
		panel.add(withdrawNowButton);
	}

	public void getStatementOnPanel(JPanel panel, JFrame parentFrame)
	{
		this.panel = panel;
		this.parentFrame = parentFrame;
		inputKey = "";
		this.panel.setPreferredSize(new Dimension(800, 300));

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

	public void showCustomers()
	{
		for(int i = 0; i < 500; i++)
		{
			resultsLabel[i].setText("");
			resultsLabel[i].setToolTipText("");
		}
		Customer foundCustomers[] = (new CustomerRepository()).getCustomers(inputKey);
		if(foundCustomers == null) return;
		int dimY = 54;
		for(int i = 0, j = 0; i < foundCustomers.length && i < 500; i++, j+=2)
		{
			if(foundCustomers[i] == null) continue;
			dimY += (27<<1);
			panel.setPreferredSize(new Dimension(800, dimY));
			resultsLabel[j].setText("  ID: "+foundCustomers[i].getUserId()+"   |   Account No: "+foundCustomers[i].getAccountNumber()+"   |   Name: "+foundCustomers[i].getName());
			resultsLabel[j].setToolTipText(foundCustomers[i].getUserId());
		}
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

	public void showCustomerDetailDialog(String uid)
	{
		JPanel profilePanel;
		JLabel profileLabel, uidLabel, emailLabel, phoneLabel, nid, birthdate, gender, email, mobile;
		JLabel avatarLabel, defaultAvatar, nameLabel, nidLabel, birthLabel, genderLabel, acnLabel, actLabel, typeLabel, noLabel;
		JButton changeAvatarButton, backButton, viewSalary;
		BankerFrame bframe;
		String days[], months[], years[], genders[];
		profileDialog = new JDialog();

		profileDialog.setSize(400, 620);
		profileDialog.setAlwaysOnTop(true);
		profileDialog.setResizable(false);

		profilePanel = new JPanel();
		profilePanel.setLayout(null);
		profilePanel.setBackground(new Color(222,227,234));

		showingCustomer = (new CustomerRepository()).getCustomer(uid);

		profileLabel = new JLabel("Customer Profile");
		profileLabel.setBounds(120, 10, 250, 30);
		profileLabel.setFont(Common.customFont1Bold);
		profilePanel.add(profileLabel);

		File profileFile = new File("others/data/"+showingCustomer.getUserId()+".jpg");
		if(profileFile.exists())
		{
			ImageIcon avatarImage = new ImageIcon(""+profileFile);
			avatarLabel = new JLabel(Common.getResizedImage(avatarImage,120,120));
			avatarLabel.setBounds(20, 50, 120, 120);
			profilePanel.add(avatarLabel);
		}

		defaultAvatar = new JLabel(Common.getResizedImage(new ImageIcon("others/data/0000000.jpg"),120,120));
		defaultAvatar.setBounds(20, 50, 120, 120);
		defaultAvatar.setToolTipText("Profile image");
		profilePanel.add(defaultAvatar);

		showBalanceButton = new JButton("Show Balance");
		showBalanceButton.setForeground(new Color(221,221,221));
		showBalanceButton.setBackground(new Color(0,51,0));
		showBalanceButton.setFont(Common.customFont1SmallestBold);
		showBalanceButton.setContentAreaFilled(false);
		showBalanceButton.setOpaque(true);
		showBalanceButton.setBounds(240, 50, 130, 30);
		showBalanceButton.setBorderPainted(false);
		showBalanceButton.addMouseListener(this);
		profilePanel.add(showBalanceButton);

		nameLabel = new JLabel(showingCustomer.getName());
		nameLabel.setBounds(150, 115, 400, 40);
		nameLabel.setForeground(new Color(0,0,102));
		nameLabel.setFont(Common.customFont1SmallBold);
		profilePanel.add(nameLabel);

		uidLabel = new JLabel(showingCustomer.getUserId());
		uidLabel.setBounds(150, 140, 300, 45);
		uidLabel.setForeground(new Color(110,110,110));
		uidLabel.setFont(Common.customFont1Smaller);
		uidLabel.setForeground(new Color(77,77,77));
		profilePanel.add(uidLabel);

		actLabel = new JLabel(showingCustomer.getAccountType() + " Account", SwingConstants.RIGHT);
		actLabel.setBounds(140, 190, 230, 30);
		actLabel.setForeground(new Color(0,51,0));
		actLabel.setFont(Common.customFont1SmallBold);
		profilePanel.add(actLabel);

		noLabel = new JLabel("Account No", SwingConstants.RIGHT);
		noLabel.setBounds(130, 220, 150, 30);
		noLabel.setForeground(new Color(110,110,110));
		noLabel.setFont(Common.customFont1Small);
		profilePanel.add(noLabel);

		acnLabel = new JLabel(showingCustomer.getAccountNumber(), SwingConstants.RIGHT);
		acnLabel.setBounds(230, 220, 140, 30);
		acnLabel.setForeground(new Color(0,51,0));
		acnLabel.setFont(Common.customFont1SmallBold);
		profilePanel.add(acnLabel);

		nidLabel = new JLabel("National ID");
		nidLabel.setBounds(20, 250, 150, 30);
		nidLabel.setFont(Common.customFont1Small);
		nidLabel.setForeground(new Color(77,77,77));
		profilePanel.add(nidLabel);

		nid = new JLabel(showingCustomer.getNid());
		nid.setBounds(20, 280, 250, 30);
		nid.setFont(Common.customFont1SmallBold);
		nid.setForeground(new Color(0,51,0));
		profilePanel.add(nid);

		birthLabel = new JLabel("Birth Date");
		birthLabel.setBounds(20, 310, 150, 30);
		birthLabel.setFont(Common.customFont1Small);
		birthLabel.setForeground(new Color(77,77,77));
		profilePanel.add(birthLabel);

		String extendedBirthdate = Common.getExtendedDate(showingCustomer.getBirthdate());
		birthdate = new JLabel(extendedBirthdate);
		birthdate.setBounds(20, 340, 300, 30);
		birthdate.setFont(Common.customFont1SmallBold);
		birthdate.setForeground(new Color(0,51,0));
		profilePanel.add(birthdate);

		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(20, 370, 150, 30);
		genderLabel.setFont(Common.customFont1Small);
		genderLabel.setForeground(new Color(77,77,77));
		profilePanel.add(genderLabel);

		gender = new JLabel(showingCustomer.getGender());
		gender.setBounds(20, 400, 200, 30);
		gender.setFont(Common.customFont1SmallBold);
		gender.setForeground(new Color(0,51,0));
		profilePanel.add(gender);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 430, 150, 30);
		emailLabel.setFont(Common.customFont1Small);
		emailLabel.setForeground(new Color(77,77,77));
		profilePanel.add(emailLabel);

		email = new JLabel(showingCustomer.getEmail());
		email.setBounds(20, 460, 350, 30);
		email.setFont(Common.customFont1SmallBold);
		email.setForeground(new Color(0,51,0));
		profilePanel.add(email);

		phoneLabel = new JLabel("Mobile No");
		phoneLabel.setBounds(20, 490, 150, 30);
		phoneLabel.setFont(Common.customFont1Small);
		phoneLabel.setForeground(new Color(77,77,77));
		profilePanel.add(phoneLabel);

		mobile = new JLabel(showingCustomer.getPhone());
		mobile.setBounds(20, 520, 200, 30);
		mobile.setFont(Common.customFont1SmallBold);
		mobile.setForeground(new Color(0,51,0));
		profilePanel.add(mobile);

		dialogBackButton = new JButton("Back");
		dialogBackButton.setBounds(290, 540, 80, 30);
		dialogBackButton.setFont(Common.customFont1Smaller);
		dialogBackButton.addMouseListener(this);
		profilePanel.add(dialogBackButton);

		profileDialog.add(profilePanel);
		profileDialog.setLocationRelativeTo(null);
		profileDialog.setSize(400, 620);
		profileDialog.setVisible(true);
	}

	Color prevColor = null;
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == searchCustomerButton)
		{
			searchCustomerButton.setIcon(new ImageIcon("frames/res/searchentered.png"));
		}
		else if(me.getSource() == searchStatementButton)
		{
			searchStatementButton.setIcon(new ImageIcon("frames/res/searchentered.png"));
		}
		else if(me.getSource() == showBalanceButton)
		{
			showBalanceButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == depositNowButton)
		{
			depositNowButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == withdrawNowButton)
		{
			withdrawNowButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() != dialogBackButton)
		{
			prevColor = ((JLabel)me.getSource()).getBackground();
			((JLabel)me.getSource()).setBackground(new Color(93,159,255));
		}
	}
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == searchCustomerButton)
		{
			searchCustomerButton.setIcon(new ImageIcon("frames/res/search.png"));
		}
		else if(me.getSource() == searchStatementButton)
		{
			searchStatementButton.setIcon(new ImageIcon("frames/res/search.png"));
		}
		else if(me.getSource() == showBalanceButton)
		{
			showBalanceButton.setBackground(new Color(0,51,0));
		}
		else if(me.getSource() == depositNowButton)
		{
			depositNowButton.setBackground(new Color(0,51,0));
		}
		else if(me.getSource() == withdrawNowButton)
		{
			withdrawNowButton.setBackground(new Color(0,51,0));
		}
		else if(me.getSource() != dialogBackButton)
		{
			if(prevColor != null)
			{
				((JLabel)me.getSource()).setBackground(prevColor);
				prevColor = null;
			}
		}
	}
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource() == searchCustomerButton)
		{
			inputKey = userSearchTF.getText();
			showCustomers();
		}
		else if(me.getSource() == dialogBackButton)
		{
			profileDialog.setVisible(false);
		}
		else if(me.getSource() == depositNowButton)
		{
			Double amount = 0.0;
			try
			{
				amount = Double.parseDouble(depositAmountTF.getText());
				String targetAccountNumber = depositTargetAccountTF.getText();
				String report = (new CustomerRepository()).depositAmount(employee.getUserId(), targetAccountNumber, amount);
				if(report != null)
				{
					warning.showMessageDialog(panel, report, "Deposit failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				double currentBalance = (new CustomerRepository()).getAccountBalance(targetAccountNumber);
				if(currentBalance == -1)
				{
					warning.showMessageDialog(panel, "Error!\nCustomer not found!", "Deposit failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				else if(currentBalance == -2)
				{
					warning.showMessageDialog(panel, "Error!\nDatabase Connection Failed!", "Deposit failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				warning.showMessageDialog(panel, "Deposition successfull!\nCustomer's Current Balance: "+currentBalance, "Deposit Success!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				return;
			}
			catch(Exception ex)
			{
				warning.showMessageDialog(panel, "Invalid amount input!", "Deposit failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
		}
		else if(me.getSource() == withdrawNowButton)
		{
			Double amount = 0.0;
			try
			{
				amount = Double.parseDouble(withdrawAmountTF.getText());
				String sourceAccountNumber = withdrawSourceAccountTF.getText();
				String report = (new CustomerRepository()).withdrawAmount(employee.getUserId(), sourceAccountNumber, amount);
				if(report != null)
				{
					warning.showMessageDialog(panel, report, "Deposit failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				double currentBalance = (new CustomerRepository()).getAccountBalance(sourceAccountNumber);
				if(currentBalance == -1)
				{
					warning.showMessageDialog(panel, "Error!\nCustomer not found!", "Withdraw failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				else if(currentBalance == -2)
				{
					warning.showMessageDialog(panel, "Error!\nDatabase Connection Failed!", "Withdraw failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				warning.showMessageDialog(panel, "Withdrawal successfull!\nCustomer's Current Balance: "+currentBalance, "Deposit Success!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				return;
			}
			catch(Exception ex)
			{
				warning.showMessageDialog(panel, "Invalid amount input!", "Deposit failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
		}
		else if(me.getSource() == searchStatementButton)
		{
			inputKey = statementTF.getText().toUpperCase();
			showStatements();
		}
		else if(me.getSource() != showBalanceButton)
		{
			String uid = ((JLabel)me.getSource()).getToolTipText();
			if(!(uid.equals("")))
			{
				showCustomerDetailDialog(uid);
			}
		}
	}
	public void mousePressed(MouseEvent me)
	{
		if(me.getSource() == showBalanceButton)
		{
			showBalanceButton.setText(""+showingCustomer.getBalance());
		}
	}
	public void mouseReleased(MouseEvent me)
	{
		if(me.getSource() == showBalanceButton)
		{
			showBalanceButton.setText("Show Balance");
		}
	}
	public void keyPressed(KeyEvent ke) {}
	public void keyReleased(KeyEvent ke) {}
	public void keyTyped(KeyEvent ke)
	{
		if(ke.getSource() == userSearchTF)
		{
			inputKey = userSearchTF.getText();
			showCustomers();
		}
		else if(ke.getSource() == statementTF)
		{
			inputKey = statementTF.getText().toUpperCase();
			showStatements();
		}
	}
}