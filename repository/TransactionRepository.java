package repository;

import java.lang.*;
import java.util.*;
import interfaces.*;

import entity.*;

public class TransactionRepository implements ITransactionRepository
{
	DatabaseConnection dbc;
	public TransactionRepository()
	{
		dbc = new DatabaseConnection();
	}
	public Transaction getTransaction(int txnid)
	{
		Transaction txn = null;

		String txnQuery = "SELECT * from transaction where transaction_id = " + txnid + ";";
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(txnQuery);
			while(dbc.result.next())
			{
				return new Transaction(dbc.result.getInt("transaction_id"),dbc.result.getString("target_account"),dbc.result.getString("transfer_type"),dbc.result.getString("time"),dbc.result.getDouble("amount"),dbc.result.getString("executing_officer_id"),dbc.result.getString("source_account"));
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getTransaction() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public Transaction[] getAllTransactions(String accountNumber)
	{
		ArrayList txnlist = new ArrayList();
		Transaction txns[] = null;
		String txnQuery = "SELECT * from transaction where target_account = '" + accountNumber + "' or source_account = '" + accountNumber +"' order by time;";
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(txnQuery);
			while(dbc.result.next())
			{
				txnlist.add(new Transaction(dbc.result.getInt("transaction_id"),dbc.result.getString("target_account"),dbc.result.getString("transfer_type"),dbc.result.getString("time"),dbc.result.getDouble("amount"),dbc.result.getString("executing_officer_id"),dbc.result.getString("source_account")));
			}
			txns = new Transaction[txnlist.size()];
			int i = 0;
			for(Object each:txnlist)
			{
				txns[i] = (Transaction)each;
				i++;
			}
			return txns;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getAllTransactions() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public Transaction[] getTransactions(String key)
	{
		key = key.toLowerCase();
		ArrayList txnlist = new ArrayList();
		Transaction txns[] = null;
		String txnQuery = "SELECT * from transaction where LOWER(target_account) like '%" + key + "%' or LOWER(source_account) = '%" + key +"%' or LOWER(transaction_id) like '%" + key +"%' order by time;";
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(txnQuery);
			while(dbc.result.next())
			{
				txnlist.add(new Transaction(dbc.result.getInt("transaction_id"),dbc.result.getString("target_account"),dbc.result.getString("transfer_type"),dbc.result.getString("time"),dbc.result.getDouble("amount"),dbc.result.getString("executing_officer_id"),dbc.result.getString("source_account")));
			}
			txns = new Transaction[txnlist.size()];
			int i = 0;
			for(Object each:txnlist)
			{
				txns[i] = (Transaction)each;
				i++;
			}
			return txns;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getAllTransactions() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String insertTransaction(Transaction txn)
	{
		String targetAccountNumber = txn.getTargetAccount();
		String transactionType = txn.getTransactionType();
		double amount = txn.getAmount();
		String officerId = txn.getOfficerId();
		String sourceAccountNumber = txn.getSourceAccount();
		String queryTransaction = "INSERT into transaction (target_account, transfer_type, amount, executing_officer_id, source_account) VALUES ('"+targetAccountNumber+"','"+transactionType+"',"+amount+",'"+officerId+"','"+sourceAccountNumber+"');";
		try
		{
			if(!dbc.openConnection()) return "Error: Database Connection Failed!";
			dbc.statement.execute(queryTransaction);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in recordTransaction() " + ex.getMessage());
			String errorMessage = ex.getMessage();
			for(int i = 0; i < errorMessage.length()-5; i++)
			{
				if(errorMessage.charAt(i)=='E'&&errorMessage.charAt(i+1)=='r'&&errorMessage.charAt(i+2)=='r'&&errorMessage.charAt(i+3)=='o')
					return errorMessage = errorMessage.substring(i, errorMessage.length()-1);
			}
			return errorMessage;
		}
		finally
		{
			dbc.closeConnection();
		}
	}


	public String updateTransaction(Transaction txn){return null;}
	public String deleteTransaction(String txnId){return null;}
}