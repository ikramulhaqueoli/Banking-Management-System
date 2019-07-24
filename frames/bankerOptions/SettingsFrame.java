package frames.bankerOptions;

import java.lang.*;
import javax.swing.*;
import others.*;
import java.awt.event.*;
import java.awt.*;

import entity.*;
import repository.*;
import frames.*;

public class SettingsFrame extends JFrame implements ActionListener
{
	private JPanel panel;
	private JLabel userSettingLabel, uidLabel, emailLabel, phoneLabel, oldPasswordLabel, newPasswordLabel, confirmPasswordLabel;
	private JLabel nameLabel, nidLabel, birthLabel, genderLabel;
	private JTextField uidTF, nameTF, emailTF, phoneTF, nidTF;
	private JPasswordField oldPasswordPF, newPasswordPF, confirmPasswordPF;
	private JButton changeButton, cancelButton;
	private JComboBox genderCB, birthDCB, birthMCB, birthYCB;
	private Employee employee;
	private BankerFrame bframe;
	private JOptionPane report;

	public SettingsFrame(BankerFrame bframe)
	{
		super("User Settings");
		this.setSize(400, 620);
		this.setAlwaysOnTop(true);
		this.setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));

		this.bframe = bframe;
		this.employee = bframe.getLoggedInEmployee();

		userSettingLabel = new JLabel("User Settings");
		userSettingLabel.setBounds(120, 10, 250, 30);
		userSettingLabel.setFont(Common.customFont1Bold);
		panel.add(userSettingLabel);

		uidLabel = new JLabel("User ID");
		uidLabel.setBounds(20, 60, 200, 15);
		uidLabel.setFont(Common.customFont1SmallerBold);
		panel.add(uidLabel);

		uidTF = new JTextField(employee.getUserId());
		uidTF.setBounds(20, 80, 150, 25);
		uidTF.setEnabled(false);
		uidTF.setFont(Common.customFont1Smaller);
		panel.add(uidTF);

		uidLabel = new JLabel("National ID");
		uidLabel.setBounds(190, 60, 200, 15);
		uidLabel.setFont(Common.customFont1SmallerBold);
		panel.add(uidLabel);

		nidTF = new JTextField(employee.getNid());
		nidTF.setBounds(190, 80, 190, 25);
		nidTF.setEnabled(false);
		nidTF.setFont(Common.customFont1Smaller);
		panel.add(nidTF);

		nameLabel = new JLabel("Name");
		nameLabel.setBounds(20, 120, 200, 15);
		nameLabel.setFont(Common.customFont1SmallerBold);
		panel.add(nameLabel);

		nameTF = new JTextField(employee.getName());
		nameTF.setBounds(20, 140, 360, 25);
		nameTF.setFont(Common.customFont1Smaller);
		panel.add(nameTF);

		birthLabel = new JLabel("Date of Birth");
		birthLabel.setBounds(20, 180, 200, 15);
		birthLabel.setFont(Common.customFont1SmallerBold);
		birthLabel.setForeground(new Color(41,41,41));
		panel.add(birthLabel);

		int birthIndex[] = Common.getBirthIndex(employee.getBirthdate());
		System.out.println(employee.getBirthdate());
		birthDCB = new JComboBox(Common.days);
		birthDCB.setBounds(20, 200, 50, 25);
		birthDCB.setForeground(Color.BLACK);
		birthDCB.setFont(Common.customFont1Smaller);
		if(birthIndex[2] != -1) birthDCB.setSelectedIndex(birthIndex[2]-1);
		panel.add(birthDCB);

		birthMCB = new JComboBox(Common.months);
		birthMCB.setBounds(75, 200, 110, 25);
		birthMCB.setForeground(Color.BLACK);
		birthMCB.setFont(Common.customFont1Smaller);
		if(birthIndex[1] != -1) birthMCB.setSelectedIndex(birthIndex[1]-1);
		panel.add(birthMCB);

		birthYCB = new JComboBox(Common.years);
		birthYCB.setBounds(190, 200, 70, 25);
		birthYCB.setForeground(Color.BLACK);
		birthYCB.setFont(Common.customFont1Smaller);
		if(birthIndex[0] != -1) birthYCB.setSelectedIndex(birthIndex[0]-1960);
		panel.add(birthYCB);

		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(285, 180, 95, 15);
		genderLabel.setFont(Common.customFont1SmallerBold);
		genderLabel.setForeground(new Color(41,41,41));
		panel.add(genderLabel);

		genderCB = new JComboBox(Common.genders);
		genderCB.setBounds(285, 200, 95, 25);
		genderCB.setFont(Common.customFont1Smaller);
		genderCB.setForeground(Color.BLACK);
		if(employee.getGender().equals("Female")) genderCB.setSelectedIndex(1);
		else  genderCB.setSelectedIndex(0);
		panel.add(genderCB);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 240, 200, 15);
		emailLabel.setFont(Common.customFont1SmallerBold);
		panel.add(emailLabel);

		emailTF = new JTextField(employee.getEmail());
		emailTF.setBounds(20, 260, 360, 25);
		emailTF.setFont(Common.customFont1Smaller);
		panel.add(emailTF);

		phoneLabel = new JLabel("Nobile No");
		phoneLabel.setBounds(20, 300, 200, 15);
		phoneLabel.setFont(Common.customFont1SmallerBold);
		panel.add(phoneLabel);

		phoneTF = new JTextField(employee.getPhone());
		phoneTF.setBounds(20, 320, 360, 25);
		phoneTF.setFont(Common.customFont1Smaller);
		panel.add(phoneTF);

		newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setBounds(20, 360, 200, 15);
		newPasswordLabel.setFont(Common.customFont1SmallerBold);
		panel.add(newPasswordLabel);

		newPasswordPF = new JPasswordField();
		newPasswordPF.setBounds(20, 380, 360, 25);
		newPasswordPF.setFont(Common.customFont1Smaller);
		panel.add(newPasswordPF);

		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(20, 420, 200, 15);
		confirmPasswordLabel.setFont(Common.customFont1SmallerBold);
		panel.add(confirmPasswordLabel);

		confirmPasswordPF = new JPasswordField();
		confirmPasswordPF.setBounds(20, 440, 360, 25);
		confirmPasswordPF.setFont(Common.customFont1Smaller);
		panel.add(confirmPasswordPF);

		oldPasswordLabel = new JLabel("Old Password *");
		oldPasswordLabel.setBounds(20, 480, 200, 15);
		oldPasswordLabel.setFont(Common.customFont1SmallerBold);
		panel.add(oldPasswordLabel);

		oldPasswordPF = new JPasswordField();
		oldPasswordPF.setBounds(20, 500, 360, 25);
		oldPasswordPF.setFont(Common.customFont1Smaller);
		panel.add(oldPasswordPF);

		changeButton = new JButton("Change");
		changeButton.setBounds(20, 545, 90, 30);
		changeButton.setFont(Common.customFont1Smaller);
		changeButton.addActionListener(this);
		panel.add(changeButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(115, 545, 85, 30);
		cancelButton.setFont(Common.customFont1Smaller);
		cancelButton.addActionListener(this);
		panel.add(cancelButton);

		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == changeButton)
		{
			String password = oldPasswordPF.getText();
			if(password != null && password.equals(employee.getPassword()))
			{
				String validityReport;
				validityReport = Common.nameValidity(nameTF.getText());
				if(validityReport!=null)
				{
					report = new JOptionPane();
					report.showMessageDialog(panel, validityReport, "Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				validityReport = Common.emailValidity(emailTF.getText());
				if(validityReport!=null)
				{
					report = new JOptionPane();
					report.showMessageDialog(panel, validityReport, "Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				validityReport = Common.phoneValidity(phoneTF.getText());
				if(validityReport!=null)
				{
					report = new JOptionPane();
					report.showMessageDialog(panel, validityReport, "Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				if(newPasswordPF.getText().length()>0)
				{
					validityReport = Common.passwordValidity(newPasswordPF.getText(), confirmPasswordPF.getText());
					if(validityReport!=null)
					{
						report = new JOptionPane();
						report.showMessageDialog(panel, validityReport, "Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
						return;
					}
					employee.setPassword(newPasswordPF.getText());
				}
				employee.setName(nameTF.getText());

				String birthDate = "";
				birthDate += Common.years[birthYCB.getSelectedIndex()];
				birthDate += "-"+(birthMCB.getSelectedIndex()+1)+"-";
				birthDate += Common.days[birthDCB.getSelectedIndex()];

				employee.setBirthdate(birthDate);
				employee.setGender(Common.genders[genderCB.getSelectedIndex()]);
				employee.setEmail(emailTF.getText());
				employee.setPhone(phoneTF.getText());

				UserRepository userRepository = new UserRepository();
				String successReport = userRepository.updateUser(employee.getUser());
				if(successReport == null)
				{
					report = new JOptionPane();
					report.showMessageDialog(panel, "Informations Successfully Updated!", "Success!", JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
					bframe.refresh();
					this.setVisible(false);
					return;
				}
				else
				{
					report = new JOptionPane();
					report.showMessageDialog(panel, "Sorry!\n"+successReport, "Falied!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
			}
			else
			{
				oldPasswordPF.setText("");
				report = new JOptionPane();
				report.showMessageDialog(panel, "Incorrect Old password", "Error", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
		}
		else if(ae.getSource() == cancelButton)
		{
			this.setVisible(false);
		}
	}
}