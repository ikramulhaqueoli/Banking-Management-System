package frames;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import others.*;
import repository.*;
import entity.*;

public class LoginFrame extends JFrame implements ActionListener, MouseListener, KeyListener
{
	private JLabel logoLabel, userLabel, passwordLabel, copyright;
	private JTextField usernameTF;
	private JPasswordField passwordPF;
	private JButton loginButton, signupButton, forgotPassword, eyeButton;
	private ImageIcon logoIcon;
	private JPanel panel;
	private JOptionPane popupMessage;

	public LoginFrame()
	{
		super("Login | Amader Bank");
		this.setSize(600, 900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));

		logoIcon = new ImageIcon("frames/res/logo.png");
		logoLabel = new JLabel(logoIcon);
		logoLabel.setBounds(200, 70, 184, 54);
		panel.add(logoLabel);

		userLabel = new JLabel("User ID");
		userLabel.setBounds(235, 220, 200, 35);
		userLabel.setFont(Common.customFont1Bold);
		userLabel.setForeground(new Color(41,41,41));
		panel.add(userLabel);

		usernameTF = new JTextField();
		usernameTF.setBounds(150, 265, 270, 35);
		usernameTF.setFont(Common.customFont1);
		usernameTF.setForeground(Color.BLACK);
		usernameTF.addKeyListener(this);
		panel.add(usernameTF);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(225, 330, 200, 35);
		passwordLabel.setFont(Common.customFont1Bold);
		passwordLabel.setForeground(new Color(41,41,41));
		panel.add(passwordLabel);

		eyeButton = new JButton(new ImageIcon("frames/res/eye_tr.png"));
		eyeButton.setBounds(388, 377, 30, 26);
		eyeButton.setBorderPainted(false);
		eyeButton.setContentAreaFilled(false);
		eyeButton.addMouseListener(this);
		panel.add(eyeButton);

		passwordPF = new JPasswordField();
		passwordPF.setBounds(150, 375, 270, 35);
		passwordPF.setFont(Common.customFont1);
		passwordPF.setForeground(Color.BLACK);
		passwordPF.addMouseListener(this);
		passwordPF.addKeyListener(this);
		panel.add(passwordPF);

		loginButton = new JButton(new ImageIcon("frames/res/login.png"));
		loginButton.setBounds(230, 440, 100, 43);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.addActionListener(this);
		loginButton.addKeyListener(this);
		panel.add(loginButton);

		signupButton = new JButton(new ImageIcon("frames/res/signup.png"));
		signupButton.setBounds(230, 505, 100, 43);
		signupButton.setBorderPainted(false);
		signupButton.setContentAreaFilled(false);
		signupButton.addActionListener(this);
		panel.add(signupButton);

		forgotPassword = new JButton("Forgot password?");
		forgotPassword.setBounds(185, 560, 200, 43);
		forgotPassword.setForeground(new Color(0,51,102));
		forgotPassword.setFont(Common.customFont1Smaller);
		forgotPassword.addActionListener(this);
		forgotPassword.setBorderPainted(false);
		forgotPassword.setContentAreaFilled(false);
		panel.add(forgotPassword);

		copyright = new JLabel("Copyright © 2019 Amader Bank Limited. All rights reserved.");
		copyright.setFont(Common.customFont1Smallest);
		copyright.setForeground(new Color(95, 95, 95));
		copyright.setBounds(130, 835, 400, 20);
		panel.add(copyright);

		this.add(panel);
	}

	public void login()
	{
		String userId = usernameTF.getText().toUpperCase(), password = passwordPF.getText();
		if(userId.length() != 9)
		{
			popupMessage = new JOptionPane();
			popupMessage.showMessageDialog(panel, "User ID must contain exactly 9 characters", "Invalid User Id", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
			return;
		}
		if(password.length() < 8)
		{
			popupMessage = new JOptionPane();
			popupMessage.showMessageDialog(panel, "Password must contain atleast 8 characters", "Invalid Password", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
			return;
		}
		String loginError = UserRepository.verifyUserLogin(userId, password);
		if(loginError != null)
		{
			popupMessage = new JOptionPane();
			popupMessage.showMessageDialog(panel, "Sorry\n"+loginError, "Invalid Login", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
			passwordPF.setText("");
			return;
		}
		userId = userId.toUpperCase();
		if(userId.charAt(0) == 'C')
		{
			CustomerRepository customerRepo = new CustomerRepository();
			Customer customer = customerRepo.getCustomer(userId);
			if(customer == null)
			{
				System.out.println("Something wrong");
				popupMessage = new JOptionPane();
				popupMessage.showMessageDialog(panel, "Sorry\nError in Fetching Customer!", "Login Failed", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			CustomerFrame cFrame = new CustomerFrame(customer);
			cFrame.setLocationRelativeTo(null);
			cFrame.setVisible(true);
			this.setVisible(false);
		}
		else if(userId.charAt(0) == 'E')
		{
			Employee employee = (new EmployeeRepository()).getEmployee(userId);
			if(employee == null)
			{
				System.out.println("Something wrong");
				popupMessage = new JOptionPane();
				popupMessage.showMessageDialog(panel, "Sorry\nError in Fetching Employee!", "Login Failed", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			if(userId.charAt(2) == 'A')
			{
				AdministratorFrame aFrame = new AdministratorFrame(employee);
				aFrame.setLocationRelativeTo(null);
				aFrame.setVisible(true);
				this.setVisible(false);
			}
			else if(userId.charAt(2) == 'B')
			{
				BankerFrame bFrame = new BankerFrame(employee);
				bFrame.setLocationRelativeTo(null);
				bFrame.setVisible(true);
				this.setVisible(false);
			}
			
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == forgotPassword)
		{
			JOptionPane report = new JOptionPane();
			report.showMessageDialog(panel, "Contact us:\nLevel #4, House #25, Nikunja 2, Khilkhet, Dhaka 1229\nPhone: +8801714347754 (Hunting number),\n+8801684540188, \nFax: N/A\nEmail: info@amaderbank.com.bd\nSwift: N/A", "Help | Amader Bank", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("frames/res/customer/help.png"));
		}
		else if(ae.getSource() == signupButton)
		{
			SignupFrame sFrame = new SignupFrame(this);
			this.setVisible(false);
			sFrame.setLocationRelativeTo(null);
			sFrame.setVisible(true);
		}
		else if(ae.getSource() == loginButton)
		{
			login();
		}
	}
	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == passwordPF || me.getSource() == eyeButton) eyeButton.setIcon(new ImageIcon("frames/res/eye.png"));
	}
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == passwordPF || me.getSource() == eyeButton) eyeButton.setIcon(new ImageIcon("frames/res/eye_tr.png"));
	}
	public void mousePressed(MouseEvent me)
	{
		if(me.getSource() == eyeButton)
		{
			passwordPF.setEchoChar((char)0);
		}
	}
	public void mouseReleased(MouseEvent me)
	{
		if(me.getSource() == eyeButton)
		{
			passwordPF.setEchoChar((char)9679);
		}
	}

	public void keyTyped(KeyEvent ke)
	{
		if(ke.getSource() == usernameTF)
		{
			String input = usernameTF.getText();
			if(input.length() > 8)
			{
				usernameTF.setText(input.substring(0,8));
			}
			else if(input.length() == 1 && ((int)ke.getKeyChar()) != 8)
			{
				usernameTF.setText(input+'-');
			}
			if(input.length() == 3 && input.charAt(2) == '-')
			{
				usernameTF.setText(input.substring(0,2));
			}
		}

		int key = (int)ke.getKeyChar();
		if(key == 10) login();
		else if(key == 27) System.exit(0);
	}
  	public void keyPressed(KeyEvent ke)
  	{
  		int key = (int)ke.getKeyChar();
		if(key == 101 || key == 69) passwordPF.setEchoChar((char)0);
  	}
  	public void keyReleased(KeyEvent ke)
  	{
  		passwordPF.setEchoChar((char)9679);
  	}
}