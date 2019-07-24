package repository;

import java.lang.*;
import java.util.Random;
import interfaces.*;

import entity.*;

public class EmployeeRepository extends UserRepository implements IEmployeeRepository
{
	DatabaseConnection dbc;
	public EmployeeRepository()
	{
		dbc = new DatabaseConnection();
	}
	public Employee getEmployee(String uid)
	{
		uid = uid.toUpperCase();
		User user = getUser(uid);
		if(user == null) return null;

		String employeeQuery = "SELECT * from Employee where userid = '" + uid + "';";
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(employeeQuery);
			while(dbc.result.next())
			{
				return new Employee(user,dbc.result.getString("designation"),dbc.result.getString("joindate"),dbc.result.getDouble("salary"));
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getEmployee() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String insertEmployee(Employee employee)
	{
		String userId = insertUser(employee.getUser());
		if(userId.substring(0, 5).equals("Error")) return userId;
		String queryEmployee = "INSERT into Employee VALUES ('"+userId+"','"+employee.getDesignation()+"','"+employee.getJoinDate()+"','"+employee.getSalary()+"');";
		try
		{
			if(!dbc.openConnection()) return "Error: Database Connection Failed!";
			dbc.statement.execute(queryEmployee);
			return userId;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in insertEmployee() " + ex.getMessage());
			String errorMessage = ex.getMessage();
			for(int i = 0; i < errorMessage.length()-5; i++)
			{
				if(errorMessage.charAt(i)=='E'&&errorMessage.charAt(i+1)=='r'&&errorMessage.charAt(i+2)=='r'&&errorMessage.charAt(i+3)=='o')
					errorMessage = errorMessage.substring(i, errorMessage.length()-1);
			}
			return errorMessage;
		}
		finally
		{
			dbc.closeConnection();
		}
	}

	public String updateEmployee(Employee employee)
	{
		
		String report = updateUser(employee.getUser());
		if(report != null) return report;
		String queryEmployee = "UPDATE Employee SET designation='"+employee.getDesignation()+"', joindate = '"+employee.getJoinDate()+"', salary = "+employee.getSalary()+";";
		try
		{
			dbc.openConnection();
			dbc.statement.executeUpdate(queryEmployee);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in updateEmployee() " + ex.getMessage());
			String errorMessage = ex.getMessage();
			for(int i = 0; i < errorMessage.length()-5; i++)
			{
				if(errorMessage.substring(i,i+4).equals("Erro"))
				{
					errorMessage = errorMessage.substring(i, errorMessage.length()-1);
					break;
				}
			}
			return errorMessage;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String deleteEmployee(String userId)
	{
		String report = deleteUser(userId);
		if(report != null) return report;
		String queryEmployee = "DELETE from employee where userid = '" + userId + "'";
		try
		{
			dbc.openConnection();
			dbc.statement.execute(queryEmployee);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in deleteEmployee() " + ex.getMessage());
			return "Error: Falied to delete employee!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}
}