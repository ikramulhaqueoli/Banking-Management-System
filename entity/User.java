package entity;
import java.lang.*;

import repository.*;

public class User
{
	private String userId, name, nid, birthDate, gender, email, phone, password;
	public User() {}
	public User(String userId, String name, String nid, String birthDate, String gender, String email, String phone, String password)
	{
		this.userId = userId;
		this.name = name;
		this.nid = nid;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	public User(User user)
	{
		this(user.userId, user.name, user.nid, user.birthDate, user.gender, user.email, user.phone, user.password);
	}
	public void setUser(User user)
	{
		this.userId = user.userId;
		this.name = user.name;
		this.nid = user.nid;
		this.birthDate = user.birthDate;
		this.gender = user.gender;
		this.email = user.email;
		this.phone = user.phone;
		this.password = user.password;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setNid(String nid)
	{
		this.nid = nid;
	}
	public void setBirthdate(String birthDate)
	{
		this.birthDate = birthDate;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public User getUser()
	{
		return this;
	}
	public String getUserId()
	{
		return userId;
	}
	public String getName()
	{
		return name;
	}
	public String getNid()
	{
		return nid;
	}
	public String getBirthdate()
	{
		return birthDate;
	}
	public String getGender()
	{
		return gender;
	}
	public String getEmail()
	{
		return email;
	}
	public String getPhone()
	{
		return phone;
	}
	public String getPassword()
	{
		return password;
	}
}