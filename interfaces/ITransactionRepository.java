package interfaces;

import java.lang.*;
import entity.*;

public interface ITransactionRepository
{
	Transaction getTransaction(int txnid);
	String insertTransaction(Transaction txn);
	String updateTransaction(Transaction txn);
	String deleteTransaction(String txnid);
}