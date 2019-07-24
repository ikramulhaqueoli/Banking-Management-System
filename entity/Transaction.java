package entity;
import java.lang.*;

public class Transaction
{
	private int txnId;
	private String txnIdExntended, targetAccount, sourceAccount, transactionType, time, officerId;
	private double amount;

	public Transaction(int txnId, String targetAccount, String transactionType, String time, double amount, String officerId, String sourceAccount)
	{
		this.txnId = txnId;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.sourceAccount = sourceAccount;
		this.transactionType = transactionType;
		this.time = time;
		this.officerId = officerId;
		this.txnIdExntended = getTxnId8Digit(txnId);
	}
	public Transaction(String targetAccount, String transactionType, double amount, String officerId, String sourceAccount)
	{
		this.targetAccount = targetAccount;
		this.transactionType = transactionType;
		this.amount = amount;
		this.time = null;
		this.officerId = officerId;
		this.sourceAccount = sourceAccount;
		this.txnIdExntended = getTxnId8Digit(txnId);
	}
	public void setTxnId(int txnId)
	{
		this.txnId = txnId;
		this.txnIdExntended = getTxnId8Digit(txnId);
	}
	public void setTargetAccount(String targetAccount)
	{
		this.targetAccount = targetAccount;
	}
	public void setSourceAccount(String sourceAccount)
	{
		this.sourceAccount = sourceAccount;
	}
	public void setTransferType(String transactionType)
	{
		this.transactionType = transactionType;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public void setOfficerId(String officerId)
	{
		this.officerId = officerId;
	}
	public int getTxnId()
	{
		return txnId;
	}
	public String getTxnIdExntended()
	{
		return txnIdExntended;
	}
	public String getTargetAccount()
	{
		return targetAccount;
	}
	public String getSourceAccount()
	{
		return sourceAccount;
	}
	public String getTransactionType()
	{
		return transactionType;
	}
	public String getTime()
	{
		return time;
	}
	public double getAmount()
	{
		return amount;
	}
	public String getOfficerId()
	{
		return officerId;
	}
	public String getTxnId8Digit(int txnid)
	{
		String tid = ""+txnid;
		while(tid.length() < 5) tid = '0' + tid;
		return transactionType.substring(0,3).toUpperCase()+tid;
	}
}