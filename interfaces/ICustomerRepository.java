package interfaces;

import java.lang.*;
import entity.*;

public interface ICustomerRepository
{
	Customer getCustomer(String username);
	String[] insertCustomer(Customer u);
	String updateCustomer(Customer u);
	String deleteCustomer(String userId);
}