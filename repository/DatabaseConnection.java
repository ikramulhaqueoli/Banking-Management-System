package repository;
import java.sql.*;

public class DatabaseConnection
{
	public Connection connection;
	public Statement statement;
	public ResultSet result;
	
	public boolean openConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/b02", "root", "");
			statement = connection.createStatement();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in openConnection() "+e.getMessage());
			return false;
		}
	}
	public void closeConnection()
	{
		try
		{
			if(connection!=null){connection.close();}
			if(statement!=null){statement.close();}
			if(result!=null){result.close();}
		}
		catch(Exception e){}
	}
	
}
