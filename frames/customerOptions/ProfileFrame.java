package frames.customerOptions;

import java.lang.*;
import javax.swing.*;
import others.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

import entity.*;
import repository.*;
import frames.*;

public class ProfileFrame extends JFrame implements ActionListener, MouseListener
{
	private JPanel panel;
	private JLabel yourProfileLabel, uidLabel, emailLabel, phoneLabel, nid, birthdate, gender, email, mobile;
	private JLabel avatarLabel, defaultAvatar, nameLabel, nidLabel, birthLabel, genderLabel, acnLabel, actLabel, typeLabel, noLabel;
	private JButton changeAvatarButton, changePinButton, backButton;
	private Customer customer;
	private CustomerFrame cframe;
	private JOptionPane report;
	private String days[], months[], years[], genders[];

	public ProfileFrame(CustomerFrame cframe)
	{
		super("Your Profile");
		this.setSize(400, 620);
		this.setAlwaysOnTop(true);
		this.setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));

		this.cframe = cframe;
		this.customer = cframe.getLoggedInCustomer();

		yourProfileLabel = new JLabel("Your Profile");
		yourProfileLabel.setBounds(120, 10, 250, 30);
		yourProfileLabel.setFont(Common.customFont1Bold);
		panel.add(yourProfileLabel);

		File profileFile = new File("others/data/"+customer.getUserId()+".jpg");
		if(profileFile.exists())
		{
			ImageIcon avatarImage = new ImageIcon(""+profileFile);
			avatarLabel = new JLabel(Common.getResizedImage(avatarImage,120,120));
			avatarLabel.setBounds(20, 50, 120, 120);
			panel.add(avatarLabel);
		}

		defaultAvatar = new JLabel(Common.getResizedImage(new ImageIcon("others/data/0000000.jpg"),120,120));
		defaultAvatar.setBounds(20, 50, 120, 120);
		defaultAvatar.setToolTipText("Profile image");
		panel.add(defaultAvatar);

		changePinButton = new JButton("Change Pin");
		changePinButton.setForeground(new Color(221,221,221));
		changePinButton.setBackground(new Color(0,51,0));
		changePinButton.setFont(Common.customFont1SmallestBold);
		changePinButton.setContentAreaFilled(false);
		changePinButton.setOpaque(true);
		changePinButton.setBounds(270, 50, 100, 30);
		changePinButton.setBorderPainted(false);
		changePinButton.addMouseListener(this);
		changePinButton.addActionListener(this);
		panel.add(changePinButton);

		changeAvatarButton = new JButton("Change Avatar");
		changeAvatarButton.setBounds(20, 180, 120, 25);
		changeAvatarButton.setFont(Common.customFont1Smallest);
		changeAvatarButton.addActionListener(this);
		panel.add(changeAvatarButton);

		nameLabel = new JLabel(customer.getName());
		nameLabel.setBounds(150, 115, 400, 40);
		nameLabel.setForeground(new Color(0,0,102));
		nameLabel.setFont(Common.customFont1SmallBold);
		panel.add(nameLabel);

		uidLabel = new JLabel(customer.getUserId());
		uidLabel.setBounds(150, 140, 300, 45);
		uidLabel.setForeground(new Color(110,110,110));
		uidLabel.setFont(Common.customFont1Smaller);
		uidLabel.setForeground(new Color(77,77,77));
		panel.add(uidLabel);

		actLabel = new JLabel(customer.getAccountType() + " Account", SwingConstants.RIGHT);
		actLabel.setBounds(140, 190, 230, 30);
		actLabel.setForeground(new Color(0,51,0));
		actLabel.setFont(Common.customFont1SmallBold);
		panel.add(actLabel);

		noLabel = new JLabel("Account No", SwingConstants.RIGHT);
		noLabel.setBounds(130, 220, 150, 30);
		noLabel.setForeground(new Color(110,110,110));
		noLabel.setFont(Common.customFont1Small);
		panel.add(noLabel);

		acnLabel = new JLabel(customer.getAccountNumber(), SwingConstants.RIGHT);
		acnLabel.setBounds(230, 220, 140, 30);
		acnLabel.setForeground(new Color(0,51,0));
		acnLabel.setFont(Common.customFont1SmallBold);
		panel.add(acnLabel);

		nidLabel = new JLabel("National ID");
		nidLabel.setBounds(20, 250, 150, 30);
		nidLabel.setFont(Common.customFont1Small);
		nidLabel.setForeground(new Color(77,77,77));
		panel.add(nidLabel);

		nid = new JLabel(customer.getNid());
		nid.setBounds(20, 280, 250, 30);
		nid.setFont(Common.customFont1SmallBold);
		nid.setForeground(new Color(0,51,0));
		panel.add(nid);

		birthLabel = new JLabel("Birth Date");
		birthLabel.setBounds(20, 310, 150, 30);
		birthLabel.setFont(Common.customFont1Small);
		birthLabel.setForeground(new Color(77,77,77));
		panel.add(birthLabel);

		String extendedBirthdate = Common.getExtendedDate(customer.getBirthdate());
		birthdate = new JLabel(extendedBirthdate);
		birthdate.setBounds(20, 340, 300, 30);
		birthdate.setFont(Common.customFont1SmallBold);
		birthdate.setForeground(new Color(0,51,0));
		panel.add(birthdate);

		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(20, 370, 150, 30);
		genderLabel.setFont(Common.customFont1Small);
		genderLabel.setForeground(new Color(77,77,77));
		panel.add(genderLabel);

		gender = new JLabel(customer.getGender());
		gender.setBounds(20, 400, 200, 30);
		gender.setFont(Common.customFont1SmallBold);
		gender.setForeground(new Color(0,51,0));
		panel.add(gender);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 430, 150, 30);
		emailLabel.setFont(Common.customFont1Small);
		emailLabel.setForeground(new Color(77,77,77));
		panel.add(emailLabel);

		email = new JLabel(customer.getEmail());
		email.setBounds(20, 460, 350, 30);
		email.setFont(Common.customFont1SmallBold);
		email.setForeground(new Color(0,51,0));
		panel.add(email);

		phoneLabel = new JLabel("Mobile No");
		phoneLabel.setBounds(20, 490, 150, 30);
		phoneLabel.setFont(Common.customFont1Small);
		phoneLabel.setForeground(new Color(77,77,77));
		panel.add(phoneLabel);

		mobile = new JLabel(customer.getPhone());
		mobile.setBounds(20, 520, 200, 30);
		mobile.setFont(Common.customFont1SmallBold);
		mobile.setForeground(new Color(0,51,0));
		panel.add(mobile);

		backButton = new JButton("Back");
		backButton.setBounds(290, 540, 80, 30);
		backButton.setFont(Common.customFont1Smaller);
		backButton.addActionListener(this);
		panel.add(backButton);

		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == changeAvatarButton)
		{
			try
			{
				File sourceFile = Common.chooseFile(Common.IMAGE_TYPE, this);
				File destinationFile = new File("others/data/"+customer.getUserId()+".jpg");
				Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				report.showMessageDialog(panel, "Image changed successfully!", "Avatar Changed!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				this.setVisible(false);
				cframe.refresh();
				ProfileFrame pframe = new ProfileFrame(cframe);
				pframe.setLocationRelativeTo(null);
				pframe.setVisible(true);
			}
			catch(Exception ex)
			{
				report.showMessageDialog(panel, "Image change request unsuccessful!", "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
			}
		}
		else if(ae.getSource() == changePinButton)
		{
			String pin = (new CustomerRepository()).getAccountPin(customer.getAccountNumber());
			(new CustomerEssentials()).changePinDialog(cframe, pin);
		}
		else if(ae.getSource() == backButton)
		{
			this.setVisible(false);
		}
	}
	public void mouseClicked(MouseEvent me) {}
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == changePinButton)
		{
			changePinButton.setBackground(Color.BLACK);
		}
	}
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == changePinButton)
		{
			changePinButton.setBackground(new Color(0,51,0));
		}
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
}