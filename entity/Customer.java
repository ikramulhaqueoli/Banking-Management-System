package entity;
import java.lang.*;

public class Customer extends User
{
	private String accountType, accountNumber, pin;
	private double balance;

	public Customer(String userId, String name, String nid, String birthDate, String gender, String email, String phone, String accountType, String accountNumber, double balance, String password)
	{
		super(userId, name, nid, birthDate, gender, email, phone, password);
		pin = null;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}
	public Customer(User user, String accountType, String accountNumber, double balance)
	{
		super(user);
		pin = null;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}
	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	public void setPin(String pin)
	{
		this.pin = pin;
	}
	public String getAccountType()
	{
		return accountType;
	}
	public String getAccountNumber()
	{
		return accountNumber;
	}
	public double getBalance()
	{
		return balance;
	}
	public String getPin()
	{
		return pin;
	}
}