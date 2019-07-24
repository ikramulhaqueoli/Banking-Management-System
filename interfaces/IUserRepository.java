package interfaces;

import java.lang.*;
import entity.*;

public interface IUserRepository
{
	User getUser(String username);
	String insertUser(User u);
	String updateUser(User u);
	String deleteUser(String userId);
}