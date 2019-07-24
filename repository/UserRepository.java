package repository;

import java.lang.*;
import java.util.*;
import interfaces.*;

import entity.*;

public class UserRepository implements IUserRepository
{
	DatabaseConnection dbc;
	public UserRepository()
	{
		dbc = new DatabaseConnection();
	}
	public String insertUser(User user)
	{
		String userId = generateUserId(user.getUserId());
		String queryUser = "INSERT into User VALUES ('"+userId+"','"+user.getName()+"','"+user.getNid()+"','"+user.getBirthdate()+"','"+user.getGender()+"','"+user.getEmail()+"','"+user.getPhone()+"');";
		String queryLogin = "INSERT into Login VALUES ('"+userId+"','"+user.getPassword()+"');";
		
		try
		{
			if(!dbc.openConnection()) return "Error: Database Connection Failed!";
			dbc.statement.execute(queryUser);
			dbc.statement.execute(queryLogin);
			return userId;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in insertUser() " + ex.getMessage());
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

	public User[] getUsers(String key)
	{
		key = key.toLowerCase();
		String userQuery = "SELECT * from User where LOWER(name) like '%" + key + "%' or LOWER(userid) like '%" + key + "%' or LOWER(phone) like '%" + key + "%' or LOWER(email) like '%"+key+"%';";
		
		User fetchedUsers[] = null;
		Set<String> uidList = new HashSet<String>();
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(userQuery);
			while(dbc.result.next())
			{
				uidList.add(dbc.result.getString("userid"));
			}
			if(uidList.size() == 0) return null;
			fetchedUsers = new User[uidList.size()];
			int i = 0;
			//System.out.println(uidList);
			for(String eachId : uidList)
			{
				User found = getUser(eachId);
				if(found != null)
				{
					System.out.println(i+" "+found.getName());
					fetchedUsers[i] = found;
					i++;
				}
			}
			return fetchedUsers;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getUsers() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	
	public String updateUser(User user)
	{
		String queryUser = "UPDATE User SET name='"+user.getName()+"', nid = '"+user.getNid()+"', birth_date = '"+user.getBirthdate()+"', gender = '"+user.getGender()+"', email = '"+user.getEmail()+"', phone = '"+user.getPhone()+"' WHERE userId='"+user.getUserId()+"';";
		String queryPassword = "UPDATE Login SET password='"+user.getPassword()+"' WHERE userId='"+user.getUserId()+"';";
		System.out.println(queryUser+"\n"+queryPassword);
		try
		{
			dbc.openConnection();
			dbc.statement.executeUpdate(queryUser);
			dbc.statement.executeUpdate(queryPassword);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in updateUser() " + ex.getMessage());
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
	public String deleteUser(String userId)
	{
		String queryUser = "DELETE FROM user where userid = '" + userId + "';";
		String queryLogin = "DELETE FROM login where userid = '" + userId + "';";
		try
		{
			dbc.openConnection();
			dbc.statement.execute(queryUser);
			dbc.statement.execute(queryLogin);
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception found in deleteUser() "+ex.getMessage());
			return "Error: Deleting User Unsuccessful!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public User getUser(String userId)
	{
		String userQuery = "SELECT * from User where userid = '" + userId + "';";
		String passwordQuery = "SELECT password from Login where userid = '" + userId + "';";

		try
		{
			dbc.openConnection();

			String password = null;
			dbc.result = dbc.statement.executeQuery(passwordQuery);

			while(dbc.result.next())
			{
				password = dbc.result.getString("password");
				break;
			}

			dbc.result = dbc.statement.executeQuery(userQuery);
			while(dbc.result.next())
			{
				return new User(dbc.result.getString("userid"),dbc.result.getString("name"),dbc.result.getString("nid"),dbc.result.getString("birth_date"),dbc.result.getString("gender"),dbc.result.getString("email"),dbc.result.getString("phone"), password);
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getUser() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public String getTodayDate()
	{
		String query = "SELECT current_date from User;";

		try
		{
			dbc.openConnection();

			String password = null;
			dbc.result = dbc.statement.executeQuery(query);

			while(dbc.result.next())
			{
				return dbc.result.getString("current_date");
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getUser() " + ex.getMessage());
			return null;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	String generateUserId(String prefix)
	{
		int totalUser = calculateTotalUser()+1;
		if(totalUser == 0) return null;
		String uid = "" + totalUser;
		while(uid.length() < 6)
		{
			uid = '0' + uid;
		}
		return prefix + uid;
	}
	public int calculateTotalUser()
	{
		String query = "SELECT COUNT(*) 'total' FROM Login";
		int totalUser = 0;
		try
		{
			dbc.openConnection();
			dbc.result = dbc.statement.executeQuery(query);
			while(dbc.result.next())
			{
				totalUser = dbc.result.getInt("total");
				break;
			}
			return totalUser;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in generateUserId() " + ex.getMessage());
			return -1;
		}
		finally
		{
			dbc.closeConnection();
		}
	}
	public static String verifyUserLogin(String userId, String password)
	{
		boolean found = false;
		DatabaseConnection dbc = new DatabaseConnection();
		try
		{
			dbc.openConnection();
			String query = "SELECT userid, password from Login where userId = '" + userId + "';";
			dbc.result = dbc.statement.executeQuery(query);
			while(dbc.result.next())
			{
				if(userId.equals(dbc.result.getString("userid")) && password.equals(dbc.result.getString("password")))
				{
					return null;
				}
			}
			return "Invalid Username or Password";
		}
		catch(Exception ex)
		{
			System.out.println("Exception in verifyUserLogin() " + ex.getMessage());
			return "Error: Database Connection Failed!";
		}
		finally
		{
			dbc.closeConnection();
		}
	}
}