package interfaces;

import java.lang.*;
import entity.*;

public interface IEmployeeRepository
{
	Employee getEmployee(String username);
	String insertEmployee(Employee e);
	String updateEmployee(Employee e);
	String deleteEmployee(String userId);
}