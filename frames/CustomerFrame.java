package frames;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import repository.*;
import entity.*;
import others.*;
import frames.customerOptions.*;

public class CustomerFrame extends JFrame implements ActionListener, MouseListener, KeyListener
{
	private JLabel avatarLabel, defaultAvatar, nameLabel, userIdLabel, copyright;
	private JLabel lineLabel, accountNumberLabel, accountTypeLabel, yourACNLabel, noLabel, typeLabel, acnLabel, actLabel;
	private JPanel panel;
	private JButton logoutButton, accountButton, profileButton, settingsButton, refreshButton;
	private JButton transferButton, balanceButton, statementButton, helpButton;
	private ImageIcon profilePicture;
	private Customer loggedInCustomer;
	private JOptionPane report;

	public CustomerFrame(Customer customer)
	{
		super("Welcome | Amader Bank");
		this.setSize(600, 900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));

		loggedInCustomer = customer;

		refreshButton = new JButton(new ImageIcon("frames/res/refresh.png"));
		refreshButton.setBounds(410, 25, 30, 30);
		refreshButton.setBorderPainted(false);
		refreshButton.setContentAreaFilled(false);
		refreshButton.addMouseListener(this);
		refreshButton.addActionListener(this);
		refreshButton.addKeyListener(this);
		panel.add(refreshButton);

		profileButton = new JButton(new ImageIcon("frames/res/profile.png"));
		profileButton.setBounds(455, 25, 30, 30);
		profileButton.setBorderPainted(false);
		profileButton.setContentAreaFilled(false);
		profileButton.addMouseListener(this);
		profileButton.addActionListener(this);
		profileButton.addKeyListener(this);
		panel.add(profileButton);

		settingsButton = new JButton(new ImageIcon("frames/res/setting.png"));
		settingsButton.setBounds(500, 25, 30, 30);
		settingsButton.setBorderPainted(false);
		settingsButton.setContentAreaFilled(false);
		settingsButton.addMouseListener(this);
		settingsButton.addActionListener(this);
		settingsButton.addKeyListener(this);
		panel.add(settingsButton);

		logoutButton = new JButton(new ImageIcon("frames/res/logout.png"));
		logoutButton.setBounds(545, 25, 30, 30);
		logoutButton.setBorderPainted(false);
		logoutButton.setContentAreaFilled(false);
		logoutButton.addMouseListener(this);
		logoutButton.addActionListener(this);
		logoutButton.addKeyListener(this);
		panel.add(logoutButton);

		File profileFile = new File("others/data/"+loggedInCustomer.getUserId()+".jpg");
		if(profileFile.exists())
		{
			ImageIcon avatarImage = Common.getResizedAvatar(profileFile);
			avatarLabel = new JLabel(avatarImage);
			avatarLabel.setBounds(20, 30, 160, 160);
			panel.add(avatarLabel);
		}

		defaultAvatar = new JLabel(new ImageIcon("others/data/0000000.jpg"));
		defaultAvatar.setBounds(20, 30, 160, 160);
		defaultAvatar.setToolTipText("Profile image");
		panel.add(defaultAvatar);

		nameLabel = new JLabel(loggedInCustomer.getName());
		nameLabel.setBounds(190, 120, 400, 60);
		nameLabel.setForeground(new Color(0,0,102));
		nameLabel.setFont(Common.customFont1Bold);
		panel.add(nameLabel);

		userIdLabel = new JLabel(loggedInCustomer.getUserId());
		userIdLabel.setBounds(190, 155, 300, 45);
		userIdLabel.setForeground(new Color(110,110,110));
		userIdLabel.setFont(Common.customFont1Smaller);
		panel.add(userIdLabel);

		lineLabel = new JLabel("____________________________________________________________________");
		lineLabel.setBounds(20, 155, 700, 60);
		lineLabel.setForeground(new Color(71,71,71));
		lineLabel.setFont(Common.customFont1Smaller);
		panel.add(lineLabel);

		balanceButton = new JButton("Check Balance");
		balanceButton.setForeground(new Color(221,221,221));
		balanceButton.setBackground(new Color(0,0,102));
		balanceButton.setFont(Common.customFont1SmallerBold);
		balanceButton.setContentAreaFilled(false);
		balanceButton.setOpaque(true);
		balanceButton.setBounds(420, 280, 150, 40);
		balanceButton.setBorderPainted(false);
		balanceButton.addMouseListener(this);
		balanceButton.addKeyListener(this);
		balanceButton.setToolTipText("Press & Hold");
		panel.add(balanceButton);

		yourACNLabel = new JLabel("Your Bank Account");
		yourACNLabel.setBounds(20, 220, 250, 30);
		yourACNLabel.setForeground(new Color(95,95,95));
		yourACNLabel.setFont(Common.customFont1Bold);
		panel.add(yourACNLabel);

		noLabel = new JLabel("Account Number");
		noLabel.setBounds(20, 260, 250, 60);
		noLabel.setForeground(new Color(110,110,110));
		noLabel.setFont(Common.customFont1Small);
		panel.add(noLabel);

		acnLabel = new JLabel(customer.getAccountNumber());
		acnLabel.setBounds(170, 260, 250, 60);
		acnLabel.setForeground(new Color(0,51,0));
		acnLabel.setFont(Common.customFont1SmallBold);
		panel.add(acnLabel);

		typeLabel = new JLabel("Account Type");
		typeLabel.setBounds(20, 290, 250, 60);
		typeLabel.setForeground(new Color(110,110,110));
		typeLabel.setFont(Common.customFont1Small);
		panel.add(typeLabel);

		actLabel = new JLabel(customer.getAccountType());
		actLabel.setBounds(170, 290, 250, 60);
		actLabel.setForeground(new Color(0,51,0));
		actLabel.setFont(Common.customFont1SmallBold);
		panel.add(actLabel);

		transferButton = new JButton(new ImageIcon("frames/res/customer/transfer.png"));
		transferButton.setBounds(100, 520, 100, 100);
		transferButton.setBorderPainted(false);
		transferButton.setContentAreaFilled(false);
		transferButton.addMouseListener(this);
		transferButton.addActionListener(this);
		transferButton.setToolTipText("P2P Transfer");
		transferButton.addKeyListener(this);
		panel.add(transferButton);

		statementButton = new JButton(new ImageIcon("frames/res/customer/statement.png"));
		statementButton.setBounds(400, 520, 100, 100);
		statementButton.setBorderPainted(false);
		statementButton.setContentAreaFilled(false);
		statementButton.addMouseListener(this);
		statementButton.addActionListener(this);
		statementButton.setToolTipText("Show Statements");
		statementButton.addKeyListener(this);
		panel.add(statementButton);

		helpButton = new JButton(new ImageIcon("frames/res/customer/help.png"));
		helpButton.setBounds(255, 700, 80, 80);
		helpButton.setBorderPainted(false);
		helpButton.setContentAreaFilled(false);
		helpButton.addMouseListener(this);
		helpButton.addActionListener(this);
		helpButton.setToolTipText("Help!");
		helpButton.addKeyListener(this);
		panel.add(helpButton);

		copyright = new JLabel("Copyright © 2019 Amader Bank Limited. All rights reserved.");
		copyright.setFont(Common.customFont1Smallest);
		copyright.setForeground(new Color(95, 95, 95));
		copyright.setBounds(130, 835, 400, 20);
		panel.add(copyright);
		
		this.add(panel);
		
		String pin = (new CustomerRepository()).getAccountPin(loggedInCustomer.getAccountNumber());
		boolean added = true;
		if(pin == null) added = false;
		if(!added)
		{
			report = new JOptionPane();
			report.showMessageDialog(panel, "You haven't added a transaction pin.\nPlease add a pin now!", "No pin added", JOptionPane.WARNING_MESSAGE);
			(new CustomerEssentials()).changePinDialog(this, pin);
		}
	}
	public void refresh()
	{
		CustomerRepository customerRepository = new CustomerRepository();
		loggedInCustomer = customerRepository.getCustomer(loggedInCustomer.getUserId());
		if(loggedInCustomer == null)
		{
			System.out.println("Something wrong");
			report = new JOptionPane();
			report.showMessageDialog(panel, "Sorry\nRefresh failed!", "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
			return;
		}
		this.setVisible(false);
		CustomerFrame cframe = new CustomerFrame(loggedInCustomer);
		cframe.setLocationRelativeTo(null);
		cframe.setVisible(true);
	}
	public Customer getLoggedInCustomer()
	{
		return loggedInCustomer;
	}
	public void setLoggedInCustomer(Customer customer)
	{
		loggedInCustomer = customer;
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == logoutButton)
		{
			Common.goToLogin(this);
		}
		else if(ae.getSource() == settingsButton)
		{
			SettingsFrame sframe = new SettingsFrame(this);
			sframe.setLocationRelativeTo(null);
			sframe.setVisible(true);
		}
		else if(ae.getSource() == profileButton)
		{
			ProfileFrame pframe = new ProfileFrame(this);
			pframe.setLocationRelativeTo(null);
			pframe.setVisible(true);
		}
		else if(ae.getSource() == refreshButton)
		{
			refresh();
		}
		else if(ae.getSource() == transferButton)
		{
			(new CustomerEssentials()).showP2PDialog(this);
		}
		else if(ae.getSource() == statementButton)
		{
			(new CustomerEssentials()).showCustomerStatement(this);
		}
		else if(ae.getSource() == helpButton)
		{
			report = new JOptionPane();
			report.showMessageDialog(panel, "Level #4, House #25, Nikunja 2, Khilkhet, Dhaka 1229\nPhone: +8801714347754 (Hunting number),\n+8801684540188, \nFax: N/A\nEmail: info@amaderbank.com.bd\nSwift: N/A", "Help | Amader Bank", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("frames/res/logo.png"));
		}
	}
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == settingsButton)
		{
			settingsButton.setIcon(new ImageIcon("frames/res/settingentered.png"));
		}
		else if(me.getSource() == logoutButton)
		{
			logoutButton.setIcon(new ImageIcon("frames/res/logoutentered.png"));
		}
		else if(me.getSource() == refreshButton)
		{
			refreshButton.setIcon(new ImageIcon("frames/res/refreshentered.png"));
		}
		else if(me.getSource() == profileButton)
		{
			profileButton.setIcon(new ImageIcon("frames/res/profileentered.png"));
		}
		else if(me.getSource() == balanceButton)
		{
			balanceButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == transferButton)
		{
			transferButton.setIcon(new ImageIcon("frames/res/customer/transferentered.png"));
		}
		else if(me.getSource() == statementButton)
		{
			statementButton.setIcon(new ImageIcon("frames/res/customer/statemententered.png"));
		}
		else if(me.getSource() == helpButton)
		{
			helpButton.setIcon(new ImageIcon("frames/res/customer/helpentered.png"));
		}
	}
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == settingsButton)
		{
			settingsButton.setIcon(new ImageIcon("frames/res/setting.png"));
		}
		else if(me.getSource() == logoutButton)
		{
			logoutButton.setIcon(new ImageIcon("frames/res/logout.png"));
		}
		else if(me.getSource() == refreshButton)
		{
			refreshButton.setIcon(new ImageIcon("frames/res/refresh.png"));
		}
		else if(me.getSource() == profileButton)
		{
			profileButton.setIcon(new ImageIcon("frames/res/profile.png"));
		}
		else if(me.getSource() == balanceButton)
		{
			balanceButton.setBackground(new Color(0,0,102));
		}
		else if(me.getSource() == transferButton)
		{
			transferButton.setIcon(new ImageIcon("frames/res/customer/transfer.png"));
		}
		else if(me.getSource() == statementButton)
		{
			statementButton.setIcon(new ImageIcon("frames/res/customer/statement.png"));
		}
		else if(me.getSource() == helpButton)
		{
			helpButton.setIcon(new ImageIcon("frames/res/customer/help.png"));
		}
	}
	public void mouseClicked(MouseEvent me) {}
	public void mousePressed(MouseEvent me)
	{
		if(me.getSource() == balanceButton)
		{
			balanceButton.setText(""+loggedInCustomer.getBalance());
			balanceButton.setFont(Common.customFont1SmallBold);
		}
	}
	public void mouseReleased(MouseEvent me)
	{
		if(me.getSource() == balanceButton)
		{
			balanceButton.setText("Check Balance");
			balanceButton.setFont(Common.customFont1SmallerBold);
		}
	}
	public void keyTyped(KeyEvent ke)
	{
		int key = (int)ke.getKeyChar();
		if(key == 10 || key == 112 || key == 80)
		{
			ProfileFrame pframe = new ProfileFrame(this);
			pframe.setLocationRelativeTo(null);
			pframe.setVisible(true);
		}
		else if(key == 27) Common.goToLogin(this);
		else if(key == 18) refresh();
		else if(key == 115 || key == 183)
		{
			SettingsFrame sframe = new SettingsFrame(this);
			sframe.setLocationRelativeTo(null);
			sframe.setVisible(true);
		}
	}
  	public void keyPressed(KeyEvent ke)
  	{
  		int key = (int)ke.getKeyChar();
  		if(key == 32)
  		{
  			balanceButton.setText(""+loggedInCustomer.getBalance());
			balanceButton.setFont(Common.customFont1SmallBold);
  		}
  	}
  	public void keyReleased(KeyEvent ke)
  	{
  		balanceButton.setText("Check Balance");
		balanceButton.setFont(Common.customFont1SmallerBold);
  	}
}