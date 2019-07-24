package frames.customerOptions;

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

public class CustomerEssentials implements MouseListener
{
	private JPanel panel;
	private JLabel headerLabel, targetAccountLabel, amountLabel, pinLabel, newPinLabel, confirmPinLabel;
	private JTextField targetAccountTF, amountTF;
	private JPasswordField pinPF, newPinPF, confirmPinPF;
	private JButton changePinButton, cancelButton, transferButton;
	private JDialog thisDialog;
	private CustomerFrame parent;
	private String oldPin, accountNumber;
	private double amount;
	private Customer customer;
	private JOptionPane warning;
	private JTable table;
	private JScrollPane scrollablePane;
	public void showP2PDialog(CustomerFrame cframe)
	{
		this.parent = cframe;
		this.customer = cframe.getLoggedInCustomer();
		this.accountNumber = customer.getAccountNumber();

		oldPin = (new CustomerRepository()).getAccountPin(accountNumber);

		panel = new JPanel();
		panel.setLayout(null);

		headerLabel = new JLabel("P2P Transfer");
		headerLabel.setBounds(90, 10, 170, 30);
		headerLabel.setFont(Common.customFont1Bold);
		headerLabel.setForeground(new Color(0,0,102));
		panel.add(headerLabel);

		targetAccountLabel = new JLabel("Target Account Number");
		targetAccountLabel.setBounds(20, 70, 200, 30);
		targetAccountLabel.setFont(Common.customFont1Small);
		panel.add(targetAccountLabel);

		targetAccountTF = new JTextField();
		targetAccountTF.setBounds(20, 110, 300, 30);
		targetAccountTF.setFont(Common.customFont1Small);
		panel.add(targetAccountTF);

		amountLabel = new JLabel("Amount");
		amountLabel.setBounds(20, 160, 200, 30);
		amountLabel.setFont(Common.customFont1Small);
		panel.add(amountLabel);

		amountTF = new JTextField();
		amountTF.setBounds(20, 200, 300, 30);
		amountTF.setFont(Common.customFont1Small);
		panel.add(amountTF);

		pinLabel = new JLabel("Transaction Pin");
		pinLabel.setBounds(20, 270, 200, 30);
		pinLabel.setFont(Common.customFont1Small);
		panel.add(pinLabel);

		pinPF = new JPasswordField();
		pinPF.setBounds(220, 270, 100, 30);
		pinPF.setFont(Common.customFont1Small);
		if(oldPin == null)
		{
			pinPF.setText("null");
			pinPF.setEchoChar((char)0);
			pinPF.setEnabled(false);
		}
		panel.add(pinPF);

		transferButton = new JButton("Transfer");
		transferButton.setForeground(new Color(221,221,221));
		transferButton.setBackground(new Color(0,51,0));
		transferButton.setFont(Common.customFont1SmallestBold);
		transferButton.setContentAreaFilled(false);
		transferButton.setOpaque(true);
		transferButton.setBorderPainted(false);
		transferButton.setBounds(145, 330, 90, 25);
		transferButton.addMouseListener(this);
		panel.add(transferButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(new Color(221,221,221));
		cancelButton.setBackground(new Color(100,100,100));
		cancelButton.setFont(Common.customFont1SmallestBold);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setOpaque(true);
		cancelButton.setBorderPainted(false);
		cancelButton.setBounds(240, 330, 80, 25);
		cancelButton.addMouseListener(this);
		panel.add(cancelButton);

		thisDialog = new JDialog(parent);
		thisDialog.setSize(360,420);
		thisDialog.add(panel);
		thisDialog.setLocationRelativeTo(null);
		thisDialog.setVisible(true);
		thisDialog.setAlwaysOnTop(true);
		thisDialog.setResizable(false);
	}
	public void changePinDialog(CustomerFrame cframe, String oldPin)
	{
		this.parent = cframe;
		this.oldPin = oldPin;
		this.customer = cframe.getLoggedInCustomer();
		this.accountNumber = customer.getAccountNumber();

		panel = new JPanel();
		panel.setLayout(null);

		headerLabel = new JLabel("Change Pin");
		if(oldPin == null) headerLabel.setText("Add Pin");
		headerLabel.setBounds(20, 10, 170, 30);
		headerLabel.setFont(Common.customFont1Bold);
		headerLabel.setForeground(new Color(0,0,102));
		panel.add(headerLabel);

		pinLabel = new JLabel("Old Pin");
		pinLabel.setBounds(20, 50, 200, 30);
		pinLabel.setFont(Common.customFont1Small);
		panel.add(pinLabel);

		pinPF = new JPasswordField();
		pinPF.setBounds(20, 90, 200, 30);
		pinPF.setFont(Common.customFont1Small);
		if(oldPin == null)
		{
			pinPF.setText("null");
			pinPF.setEchoChar((char)0);
			pinPF.setEnabled(false);
		}
		panel.add(pinPF);

		newPinLabel = new JLabel("New Pin");
		newPinLabel.setBounds(20, 140, 200, 30);
		newPinLabel.setFont(Common.customFont1Small);
		panel.add(newPinLabel);

		newPinPF = new JPasswordField();
		newPinPF.setBounds(20, 180, 200, 30);
		newPinPF.setFont(Common.customFont1Small);
		panel.add(newPinPF);

		confirmPinLabel = new JLabel("Confirm Pin");
		confirmPinLabel.setBounds(20, 230, 200, 30);
		confirmPinLabel.setFont(Common.customFont1Small);
		panel.add(confirmPinLabel);

		confirmPinPF = new JPasswordField();
		confirmPinPF.setBounds(20, 270, 200, 30);
		confirmPinPF.setFont(Common.customFont1Small);
		panel.add(confirmPinPF);

		changePinButton = new JButton("Change Pin");
		if(oldPin == null) changePinButton.setText("Add Pin");
		changePinButton.setForeground(new Color(221,221,221));
		changePinButton.setBackground(new Color(0,0,102));
		changePinButton.setFont(Common.customFont1SmallestBold);
		changePinButton.setContentAreaFilled(false);
		changePinButton.setOpaque(true);
		changePinButton.setBorderPainted(false);
		changePinButton.setBounds(20, 330, 110, 25);
		changePinButton.addMouseListener(this);
		panel.add(changePinButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(new Color(221,221,221));
		cancelButton.setBackground(new Color(100,100,100));
		cancelButton.setFont(Common.customFont1SmallestBold);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setOpaque(true);
		cancelButton.setBorderPainted(false);
		cancelButton.setBounds(135, 330, 80, 25);
		cancelButton.addMouseListener(this);
		panel.add(cancelButton);

		thisDialog = new JDialog(parent);
		thisDialog.setSize(260,420);
		thisDialog.add(panel);
		thisDialog.setLocationRelativeTo(null);
		thisDialog.setVisible(true);
		thisDialog.setAlwaysOnTop(true);
		thisDialog.setResizable(false);
	}
	public void showCustomerStatement(CustomerFrame cframe)
	{
		this.parent = cframe;
		this.customer = cframe.getLoggedInCustomer();
		this.accountNumber = customer.getAccountNumber();
		Transaction []txns = (new TransactionRepository()).getAllTransactions(accountNumber);
		String statements[][] = new String[txns.length][7];
		String columnNames[] = new String[] {"Txn ID","Source","Target","Amount","Txn Type","Time","Executed By"};
		for(int i = 0; i < txns.length; i++)
		{
			statements[i][0] = txns[i].getTxnIdExntended();
			statements[i][1] = txns[i].getSourceAccount();
			statements[i][2] = txns[i].getTargetAccount();
			statements[i][3] = ""+txns[i].getAmount();
			statements[i][4] = txns[i].getTransactionType();
			statements[i][5] = txns[i].getTime();
			statements[i][6] = txns[i].getOfficerId();
		}
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222,227,234));
		headerLabel = new JLabel("Your Statements");
		headerLabel.setBounds(20,20,150,30);
		headerLabel.setFont(Common.customFont1SmallBold);
		panel.add(headerLabel);
		table = new JTable(statements, columnNames);
		table.setFont(Common.customFont1Smaller);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(170);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		scrollablePane = new JScrollPane(table);
		scrollablePane.setBounds(15,60,705,390);
		scrollablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollablePane);

        cancelButton = new JButton("Back");
        cancelButton.setForeground(new Color(221,221,221));
		cancelButton.setBackground(new Color(100,100,100));
		cancelButton.setFont(Common.customFont1SmallestBold);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setOpaque(true);
		cancelButton.setBorderPainted(false);
		cancelButton.setBounds(650, 475, 70, 25);
		cancelButton.addMouseListener(this);
		panel.add(cancelButton);

        thisDialog = new JDialog(parent);
		thisDialog.setSize(745,545);
		thisDialog.add(panel);
		thisDialog.setLocationRelativeTo(null);
		thisDialog.setVisible(true);
		thisDialog.setAlwaysOnTop(true);
		thisDialog.setResizable(false);
	}
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource() == changePinButton)
		{
			String currentPin = (new CustomerRepository()).getAccountPin(accountNumber);
			if(oldPin != null && !currentPin.equals(pinPF.getText()))
			{
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Sorry!\nOld Pin doesn't match your record", "Invalid Old Pin!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			String report = Common.pinValidity(newPinPF.getText(), confirmPinPF.getText());
			if(report != null)
			{
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Sorry!\n"+report, "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			report = (new CustomerRepository()).setAccountPin(accountNumber, newPinPF.getText());
			if(report != null)
			{
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Sorry!\n"+report, "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			warning = new JOptionPane();
			warning.showMessageDialog(panel, "New Pin Added Successfully!", "Success!",JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
			thisDialog.setVisible(false);
		}
		else if(me.getSource() == transferButton)
		{
			if(oldPin.substring(0,5).equals("Error"))
			{
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Sorry!\n"+oldPin, "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			if(!pinPF.getText().equals(oldPin))
			{
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Sorry!\nIncorrect Pin Entered!", "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}
			amount = 0.0;
			String targetAccount = targetAccountTF.getText();
			try
			{
				String sourceAccountNumber = accountNumber;
				String targetAccountNumber = targetAccount;
				if(sourceAccountNumber.equals(targetAccountNumber))
				{
					warning = new JOptionPane();
					warning.showMessageDialog(panel, "Sorry!\nYou can not transfer balance to your own account!", "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					targetAccountTF.setText("");
					return;
				}
				amount = Double.parseDouble(amountTF.getText());
				String report = (new CustomerRepository()).transferAmount(sourceAccountNumber, targetAccountNumber, amount);
				if(report != null)
				{
					warning = new JOptionPane();
					warning.showMessageDialog(panel, "Sorry!\n"+report, "Failed!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
					return;
				}
				double currentBalace = customer.getBalance()-amount;
				customer.setBalance(currentBalace);
				parent.setLoggedInCustomer(customer);
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Transaction Successfull!\nYour current balance is: "+currentBalace, "Success!",JOptionPane.INFORMATION_MESSAGE, Common.successIcon);
				thisDialog.setVisible(false);
				parent.refresh();
			}
			catch(Exception ex)
			{
				warning = new JOptionPane();
				warning.showMessageDialog(panel, "Sorry!\n"+"Invalid amount input!", "Invalid Input!", JOptionPane.ERROR_MESSAGE, Common.errorIcon);
				return;
			}

		}
		else if(me.getSource() == cancelButton)
		{
			thisDialog.setVisible(false);
		}
	}
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == changePinButton)
		{
			changePinButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == cancelButton)
		{
			cancelButton.setBackground(Color.BLACK);
		}
		else if(me.getSource() == transferButton)
		{
			transferButton.setBackground(Color.BLACK);
		}
	}
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == changePinButton)
		{
			changePinButton.setBackground(new Color(0,0,102));
		}
		else if(me.getSource() == cancelButton)
		{
			cancelButton.setBackground(new Color(100,100,100));
		}
		else if(me.getSource() == transferButton)
		{
			transferButton.setBackground(new Color(0,51,0));
		}
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
}