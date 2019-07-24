package entity;
import java.lang.*;

import repository.*;

public class Employee extends User
{
	private String designation, joinDate;
	private double salary;

	public Employee() {}
	public Employee(String userId, String name, String nid, String birthDate, String gender, String email, String phone, String designation, String joinDate, double salary, String password)
	{
		super(userId, name, nid, birthDate, gender, email, phone, password);
		this.designation = designation;
		this.joinDate = joinDate;
		this.salary = salary;
	}
	public Employee(User user, String designation, String joinDate, double salary)
	{
		super(user);
		this.designation = designation;
		this.joinDate = joinDate;
		this.salary = salary;
	}
	public void setDesignation(String designation)
	{
		this.designation = designation;
	}
	public void setJoinDate(String joinDate)
	{
		this.joinDate = joinDate;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	public String getDesignation()
	{
		return designation;
	}
	public String getJoinDate()
	{
		return joinDate;
	}
	public double getSalary()
	{
		return salary;
	}
}