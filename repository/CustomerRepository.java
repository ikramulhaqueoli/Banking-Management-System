package repository;

import java.lang.*;
import java.util.*;
import interfaces.*;

import entity.*;

public class CustomerRepository extends UserRepository implements ICustomerRepository
{
	DatabaseConnection dbc;
	public CustomerRepository()
	{
		dbc = new DatabaseConnection();
	}
	public Customer getCustomer(String uid)
	{
		User user = getUser(uid);
		if(user == null) return null;
		//System.out.println(uid + " " +)
		String customerQuery = "SELECT * from Customer where userid = '" + uid + "';";
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(customerQuery);
			while(dbc.result.next())
			{
				return new Customer(user, dbc.result.getString("account_type"),dbc.result.getString("account_number"),dbc.result.getDouble("balance"));
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getCustomer() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public Customer[] getCustomers(String key)
	{
		key = key.toLowerCase();
		String customerQuery = "SELECT userid from Customer where LOWER(userid) like '%" + key + "%' or account_number like '%" + key +"%';";
		String userQuery = "SELECT userid from User where LOWER(name) like '%" + key + "%' and UPPER(userid) like 'C%';";
		Set<String> uidList = new HashSet<String>();
		
		Customer fetchedCustomers[] = null;
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(customerQuery);
			while(dbc.result.next())
			{
				uidList.add(dbc.result.getString("userid"));
			}
			dbc.result = dbc.statement.executeQuery(userQuery);
			while(dbc.result.next())
			{
				uidList.add(dbc.result.getString("userid"));
			}
			if(uidList.size() == 0) return null;
			fetchedCustomers = new Customer[uidList.size()];
			int i = 0;
			//System.out.println(uidList);
			for(String eachId : uidList)
			{
				Customer found = getCustomer(eachId);
				if(found != null)
				{
					System.out.println(i+" "+found.getName());
					fetchedCustomers[i] = found;
					i++;
				}
			}
			return fetchedCustomers;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getCustomer() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String[] insertCustomer(Customer customer)
	{
		String accountNumber = generateAccountNumber();
		String userId = insertUser(customer.getUser());
		if(userId.substring(0, 5).equals("Error")) return new String[] {userId};
		String queryCustomer = "INSERT into Customer (userid, account_number, balance, account_type) VALUES ('"+userId+"','"+accountNumber+"','"+customer.getBalance()+"','"+customer.getAccountType()+"');";
		try
		{
			if(!dbc.openConnection()) return new String[]{"Error: Database Connection Failed!"};
			dbc.statement.execute(queryCustomer);
			return new String[]{userId, accountNumber};
		}
		catch(Exception ex)
		{
			System.out.println("Exception in insertCustomer() " + ex.getMessage());
			String errorMessage = ex.getMessage();
			for(int i = 0; i < errorMessage.length()-5; i++)
			{
				if(errorMessage.charAt(i)=='E'&&errorMessage.charAt(i+1)=='r'&&errorMessage.charAt(i+2)=='r'&&errorMessage.charAt(i+3)=='o')
					errorMessage = errorMessage.substring(i, errorMessage.length()-1);
			}
			return new String[]{errorMessage};
		}
		finally
		{
			dbc.closeConnection();
		}
	}

	public String updateCustomer(Customer customer){return null;}
	public String deleteCustomer(String userId)
	{
		String report = deleteUser(userId);
		if(report != null) return report;
		String queryCustomer = "DELETE from customer where userid = '" + userId + "'";
		try
		{
			dbc.openConnection();
			dbc.statement.execute(queryCustomer);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in deleteCustomer() " + ex.getMessage());
			return "Error: Falied to delete Customer!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}

	String generateAccountNumber()
	{
		int totalUser = calculateTotalUser()+1;
		if(totalUser == 0) return null;
		String accountNumber = "" + totalUser;
		while(accountNumber.length() < 8)
		{
			accountNumber = '0' + accountNumber;
		}
		return accountNumber;
	}
	public String getAccountPin(String accountNumber)
	{
		String pinQuery = "SELECT pin from Customer where account_number = '" + accountNumber + "';";

		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(pinQuery);
			while(dbc.result.next())
			{
				return dbc.result.getString("pin");
			}
			return "Error: Unknown";
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getAccountPin() " + ex.getMessage());
			return "Error: Database connection failed!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String setAccountPin(String accountNumber, String newPin)
	{
		String pinQuery = "UPDATE customer SET pin='"+newPin+"' WHERE account_number = '"+accountNumber+"';";
		try
		{
			dbc.openConnection();
			dbc.statement.executeUpdate(pinQuery);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in setAccountPin() " + ex.getMessage());
			return "Error: Database connection failed!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public double getAccountBalance(String accountNumber)
	{
		String balanceQuery = "SELECT balance from Customer where account_number = '" + accountNumber + "';";

		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(balanceQuery);
			while(dbc.result.next())
			{
				return dbc.result.getDouble("balance");
			}
			return -1;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getAccountBalance() " + ex.getMessage());
			return -2;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String setAccountBalance(String accountNumber, double amount)
	{
		String amountQuery = "UPDATE customer SET balance="+amount+" WHERE account_number = '"+accountNumber+"';";
		try
		{
			dbc.openConnection();
			dbc.statement.executeUpdate(amountQuery);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in setAccountBalance() " + ex.getMessage());
			return "Error: Database connection failed!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String depositAmount(String officerId, String accountNumber, double amount)
	{
		if(amount < 1) return "Error: Invalid transaction amount!";
		double currentBalance = getAccountBalance(accountNumber);
		if(currentBalance == -1) return "Error: Customer not found!";
		if(currentBalance == -2) return "Error: Database connection failed!";
		currentBalance += amount;
		String report = setAccountBalance(accountNumber, currentBalance);
		if(report != null) return report;
		Transaction txn = new Transaction(accountNumber, "Deposit", amount, officerId, "N/A");
		return (new TransactionRepository()).insertTransaction(txn);
	}
	public String withdrawAmount(String officerId, String accountNumber, double amount)
	{
		if(amount < 1) return "Error: Invalid transaction amount!";
		double currentBalance = getAccountBalance(accountNumber);
		if(currentBalance == -1) return "Error: Customer not found!";
		if(currentBalance == -2) return "Error: Database connection failed!";
		if(currentBalance < amount) return "Error: Insufficient Balance!";
		currentBalance -= amount;
		String report = setAccountBalance(accountNumber, currentBalance);
		Transaction txn = new Transaction("N/A", "Withdraw", amount, officerId, accountNumber);
		return (new TransactionRepository()).insertTransaction(txn);
	}
	public String transferAmount(String sourceAccountNumber, String targetAccountNumber, double amount)
	{
		if(amount < 1) return "Error: Invalid transaction amount!";
		double sourceAccountBalance = getAccountBalance(sourceAccountNumber);
		double targetAccountBalance = getAccountBalance(targetAccountNumber);
		if(sourceAccountBalance == -1) return "Error: Source Customer not found!";
		if(targetAccountBalance == -1) return "Error: Target Customer not found!";
		if(sourceAccountBalance == -2 || targetAccountBalance == -2) return "Error: Database connection failed!";
		if(sourceAccountBalance < amount) return "Error: Insufficient Balance!";
		sourceAccountBalance -= amount;
		targetAccountBalance += amount;
		String report = setAccountBalance(sourceAccountNumber, sourceAccountBalance);
		if(report != null) return report;
		report = setAccountBalance(targetAccountNumber, targetAccountBalance);
		Transaction txn = new Transaction(targetAccountNumber, "P2P_Transfer", amount, "N/A", sourceAccountNumber);
		return (new TransactionRepository()).insertTransaction(txn);
	}
}