package frames;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import repository.*;
import entity.*;
import others.*;
import frames.bankerOptions.*;

public class BankerFrame extends JFrame implements ActionListener, MouseListener, KeyListener
{
	private JLabel avatarLabel, defaultAvatar, nameLabel, userIdLabel, copyright;
	private JLabel lineLabel, adminPanelLabel, designationLabel, empDesignation, actLabel;
	private JPanel panel, bankerEmpPanel;
	private JButton logoutButton, accountButton, profileButton, settingsButton, refreshButton;
	private JButton searchCustomerButton, depositButton, withdrawButton, statementsButton, nowClicked;
	private ImageIcon profilePicture;
	private Employee loggedInEmployee;
	private JOptionPane dialog;
	private JScrollPane scrollablePane;

	public BankerFrame(Employee emp)
	{
		super("Banker Panel | Amader Bank");
		this.setSize(600, 900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));

		loggedInEmployee = emp;

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

		File profileFile = new File("others/data/"+loggedInEmployee.getUserId()+".jpg");
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

		nameLabel = new JLabel(loggedInEmployee.getName());
		nameLabel.setBounds(190, 120, 400, 60);
		nameLabel.setForeground(new Color(0,0,102));
		nameLabel.setFont(Common.customFont1Bold);
		panel.add(nameLabel);

		userIdLabel = new JLabel(loggedInEmployee.getUserId());
		userIdLabel.setBounds(190, 155, 300, 45);
		userIdLabel.setForeground(new Color(110,110,110));
		userIdLabel.setFont(Common.customFont1Smaller);
		panel.add(userIdLabel);

		lineLabel = new JLabel("____________________________________________________________________");
		lineLabel.setBounds(20, 155, 700, 60);
		lineLabel.setForeground(new Color(71,71,71));
		lineLabel.setFont(Common.customFont1Smaller);
		panel.add(lineLabel);

		adminPanelLabel = new JLabel("Banker's Panel");
		adminPanelLabel.setBounds(20, 220, 250, 30);
		adminPanelLabel.setForeground(new Color(95,95,95));
		adminPanelLabel.setFont(Common.customFont1Bold);
		panel.add(adminPanelLabel);

		designationLabel = new JLabel("Designation");
		designationLabel.setBounds(20, 260, 250, 60);
		designationLabel.setForeground(new Color(110,110,110));
		designationLabel.setFont(Common.customFont1Small);
		panel.add(designationLabel);

		empDesignation = new JLabel(loggedInEmployee.getDesignation());
		empDesignation.setBounds(150, 260, 250, 60);
		empDesignation.setForeground(new Color(0,51,0));
		empDesignation.setFont(Common.customFont1SmallBold);
		panel.add(empDesignation);

		searchCustomerButton = new JButton("View Customer");
		searchCustomerButton.setForeground(new Color(221,221,221));
		searchCustomerButton.setBackground(new Color(0,0,102));
		searchCustomerButton.setFont(Common.customFont1SmallestBold);
		searchCustomerButton.setContentAreaFilled(false);
		searchCustomerButton.setOpaque(true);
		searchCustomerButton.setBounds(20, 380, 135, 30);
		searchCustomerButton.setBorderPainted(false);
		searchCustomerButton.addMouseListener(this);
		panel.add(searchCustomerButton);

		depositButton = new JButton("Deposit");
		depositButton.setForeground(new Color(221,221,221));
		depositButton.setBackground(new Color(0,0,102));
		depositButton.setFont(Common.customFont1SmallestBold);
		depositButton.setContentAreaFilled(false);
		depositButton.setOpaque(true);
		depositButton.setBounds(160, 380, 135, 30);
		depositButton.setBorderPainted(false);
		depositButton.addMouseListener(this);
		panel.add(depositButton);

		withdrawButton = new JButton("Withdraw");
		withdrawButton.setForeground(new Color(221,221,221));
		withdrawButton.setBackground(new Color(0,0,102));
		withdrawButton.setFont(Common.customFont1SmallestBold);
		withdrawButton.setContentAreaFilled(false);
		withdrawButton.setOpaque(true);
		withdrawButton.setBounds(300, 380, 135, 30);
		withdrawButton.setBorderPainted(false);
		withdrawButton.addMouseListener(this);
		panel.add(withdrawButton);

		statementsButton = new JButton("Statements");
		statementsButton.setForeground(new Color(221,221,221));
		statementsButton.setBackground(new Color(0,0,102));
		statementsButton.setFont(Common.customFont1SmallestBold);
		statementsButton.setContentAreaFilled(false);
		statementsButton.setOpaque(true);
		statementsButton.setBounds(440, 380, 135, 30);
		statementsButton.setBorderPainted(false);
		statementsButton.addMouseListener(this);
		panel.add(statementsButton);

		nowClicked = null;

		bankerEmpPanel = new JPanel();
		bankerEmpPanel.setBackground(new Color(178,178,178));
		bankerEmpPanel.setLayout(null);

		scrollablePane = new JScrollPane();
		scrollablePane.setBounds(20, 410, 555, 420);
		scrollablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollablePane.getVerticalScrollBar().setUnitIncrement(20);
        scrollablePane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollablePane.setVisible(false);
		panel.add(scrollablePane);

		copyright = new JLabel("Copyright © 2019 Amader Bank Limited. All rights reserved.");
		copyright.setFont(Common.customFont1Smallest);
		copyright.setForeground(new Color(95, 95, 95));
		copyright.setBounds(130, 835, 400, 20);
		panel.add(copyright);
		
		this.add(panel);
		
	}
	public void refresh()
	{
		EmployeeRepository employeeRepository = new EmployeeRepository();
		loggedInEmployee = employeeRepository.getEmployee(loggedInEmployee.getUserId());
		if(loggedInEmployee == null)
		{
			System.out.println("Something wrong");
			dialog = new JOptionPane();
			dialog.showMessageDialog(panel, "Sorry\nRefresh failed!", "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
			return;
		}
		this.setVisible(false);
		BankerFrame bframe = new BankerFrame(loggedInEmployee);
		bframe.setLocationRelativeTo(null);
		bframe.setVisible(true);
	}
	public Employee getLoggedInEmployee()
	{
		return loggedInEmployee;
	}
	public void setloggedInEmployee(Employee employee)
	{
		loggedInEmployee = employee;
	}
	public void showPartialPanel(JButton clicked)
	{
		bankerEmpPanel.removeAll();
		if(clicked == searchCustomerButton)
		{
			scrollablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	scrollablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			(new BankerEssentials()).getViewCustomerOnPanel(bankerEmpPanel, this);
			scrollablePane.getViewport().add(bankerEmpPanel);
		}
		else if(clicked == depositButton)
		{
			scrollablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	scrollablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			(new BankerEssentials()).getDepositOnPanel(bankerEmpPanel, this);
			scrollablePane.getViewport().add(bankerEmpPanel);
		}
		else if(clicked == withdrawButton)
		{
			scrollablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	scrollablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			(new BankerEssentials()).getWithdrawOnPanel(bankerEmpPanel, this);
			scrollablePane.getViewport().add(bankerEmpPanel);
		}
		else if(clicked == statementsButton)
		{
			scrollablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        	scrollablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			(new BankerEssentials()).getStatementOnPanel(bankerEmpPanel, this);
			scrollablePane.getViewport().add(bankerEmpPanel);
		}
		scrollablePane.setVisible(true);
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
		else if(me.getSource() == searchCustomerButton)
		{
			searchCustomerButton.setBackground(new Color(178,178,178));
			searchCustomerButton.setForeground(new Color(8,8,8));
		}
		else if(me.getSource() == depositButton)
		{
			depositButton.setBackground(new Color(178,178,178));
			depositButton.setForeground(new Color(8,8,8));
		}
		else if(me.getSource() == withdrawButton)
		{
			withdrawButton.setBackground(new Color(178,178,178));
			withdrawButton.setForeground(new Color(8,8,8));
		}
		else if(me.getSource() == statementsButton)
		{
			statementsButton.setBackground(new Color(178,178,178));
			statementsButton.setForeground(new Color(8,8,8));
		}
	}
	public void mouseExited(MouseEvent me)
	{
		if(nowClicked == me.getSource()) return;
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
		else if(me.getSource() == searchCustomerButton)
		{
			searchCustomerButton.setForeground(new Color(221,221,221));
			searchCustomerButton.setBackground(new Color(0,0,102));
		}
		else if(me.getSource() == depositButton)
		{
			depositButton.setForeground(new Color(221,221,221));
			depositButton.setBackground(new Color(0,0,102));
		}
		else if(me.getSource() == withdrawButton)
		{
			withdrawButton.setForeground(new Color(221,221,221));
			withdrawButton.setBackground(new Color(0,0,102));
		}
		else if(me.getSource() == statementsButton)
		{
			statementsButton.setForeground(new Color(221,221,221));
			statementsButton.setBackground(new Color(0,0,102));
		}
	}
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource() != searchCustomerButton && me.getSource() != depositButton && me.getSource() != withdrawButton && me.getSource() != statementsButton) return;
		if(nowClicked != null)
		{
			nowClicked.setForeground(new Color(221,221,221));
			nowClicked.setBackground(new Color(0,0,102));
			if(me.getSource() == nowClicked)
			{
				scrollablePane.setVisible(false);
				nowClicked = null;
				return;
			}
		}
		scrollablePane.setVisible(false);
		nowClicked = (JButton)me.getSource();
		nowClicked.setBackground(new Color(178,178,178));
		nowClicked.setForeground(new Color(8,8,8));
		showPartialPanel(nowClicked);
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
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
  	}
  	public void keyReleased(KeyEvent ke)
  	{
  		
  	}
}