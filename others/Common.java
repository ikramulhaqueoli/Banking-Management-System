package others;

import java.lang.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import frames.*;
import repository.*;

public class Common
{
	public static Font customFont1, customFont1Big, customFont1Small, customFont1Smaller, customFont1Smallest;
	public static Font customFont1Bold, customFont1BigBold, customFont1SmallBold, customFont1SmallerBold, customFont1SmallestBold;
	public static String months[], days[], years[], genders[];
	final public static ImageIcon errorIcon = new ImageIcon("frames/res/error.png");
	final public static ImageIcon successIcon = new ImageIcon("frames/res/success.png");
	public static FileFilter imgFilter = new FileNameExtensionFilter("Image File", new String[]{"jpg","png","jpeg","JPG","PNG","JPEG"});
	final public static int IMAGE_TYPE = 786;
	public static void initialize()
	{
		initializeFonts();
		initilizeDates();
		genders = new String[] {"Male","Female"};
		try
		{
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch(Exception ex)
	    {
	        System.out.println("Exception in UIManager.setLookAndFeel() "+ ex.getMessage());
	    }
	}
	static void initializeFonts()
	{
		String fontName = "Arial";
		customFont1 = new Font(fontName, Font.PLAIN, 25);
		customFont1Big = new Font(fontName, Font.PLAIN, 35);
		customFont1Small = new Font(fontName, Font.PLAIN, 18);
		customFont1Smaller = new Font(fontName, Font.PLAIN, 16);
		customFont1Smallest = new Font(fontName, Font.PLAIN, 12);

		customFont1Bold = new Font(fontName, Font.BOLD, 25);
		customFont1BigBold = new Font(fontName, Font.BOLD, 35);
		customFont1SmallBold = new Font(fontName, Font.BOLD, 18);
		customFont1SmallerBold = new Font(fontName, Font.BOLD, 16);
		customFont1SmallestBold = new Font(fontName, Font.BOLD, 12);
	}
	static void initilizeDates()
	{
		months = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		days = new String[31];
		for(int i = 1; i <= 31; i++) days[i-1] = (""+i);
		years = new String[51];
		for(int i = 1960, idx = 0; i <= 2010; i++, idx++) years[idx] = (""+i);
	}
	public static void goToLogin(JFrame frame)
	{
		if(frame != null) frame.setVisible(false);
		LoginFrame lgf = new LoginFrame();
		lgf.setLocationRelativeTo(null);
		lgf.setVisible(true);
	}
	public static String nameValidity(String name)
	{
		try
		{
			if(name == null || name.length() < 8 || name.length() > 30) return "Name must contain 10 to 30 characters";
			name = name.toUpperCase();
			boolean wordOk = false;
			for(int i = 1; i < name.length()-1; i++)
			{
				if(name.charAt(i-1) >= 'A' && name.charAt(i-1) <= 'Z' && name.charAt(i+1) >= 'A' && name.charAt(i+1) <= 'Z' && name.charAt(i) == ' ')
					wordOk = true;
			}
			if(!wordOk) return "Name must contain at least two words";
			if(name.charAt(0) == ' ' || name.charAt(name.length()-1) == ' ') return "Name contains unnecessary space(s)";
			for(int i = 0; i < name.length()-1; i++) if(name.charAt(i) == ' ' && name.charAt(i+1) == ' ') return "Name contains unnecessary space(s)";
			for(int i = 0; i < name.length(); i++) if(!(name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') && name.charAt(i) != ' ')  return "Name contains unnecessary symbol(s)";
			return null;
		}
		catch(Exception ex)
		{
			return "Invalid name!";
		}
	}
	public static String nidValidity(String nid)
	{
		try
		{
			if(nid.length() != 13 && nid.length() != 17 && nid.length() != 10) return "NId number must contain 10, 13 or 17 digits";
			for(int i = 0; i < nid.length(); i++) if(!(nid.charAt(i) >= '0' && nid.charAt(i) <= '9')) return "NId number contains invalid characters!";
			return null;
		}
		catch(Exception ex)
		{
			return "Invalid nid number!";
		}
	}
	public static String emailValidity(String email)
	{
		try
		{
			email = email.toLowerCase();
			int atSymbol = 0, dotAfterAt = 0, charAfterAt = 0;
			if(email.charAt(0) == '@') return "Invalid email address!";
			for(int i = 0; i < email.length()-1; i++)
			{
				if(!((email.charAt(i) >= 'a' && email.charAt(i) <= 'z') || email.charAt(i) == '_' || email.charAt(i) == '.' || email.charAt(i) == '+' || email.charAt(i) == '@' || (email.charAt(i) >= '0' && email.charAt(i) <= '9')))
					return "Email contains invalid character(s)";
				if(email.charAt(i) == '.' && email.charAt(i+1) == '.') return "Invalid email address!";
				if(email.charAt(i) == '@') atSymbol++;
				if(email.charAt(i) == '.' && atSymbol > 0) dotAfterAt++;
				if(email.charAt(i) >= 'a' && email.charAt(i) <= 'z' && atSymbol > 0) charAfterAt++;
			}
			if(atSymbol > 1 || dotAfterAt == 0 || charAfterAt == 0) return "Invalid email address!";
			for(int i = 0; i < email.length(); i++)
			{
				if(email.charAt(i) == '@')
				{
					for(int j = i+1; j < email.length()-1; j++)
					{
						if(email.charAt(j) == '.' && email.charAt(j+1) == '.') return "Invalid email address!";
						if(email.charAt(j-1) == '@' && email.charAt(j) == '.') return "Invalid email address!";
					}
					if(!(email.charAt(email.length()-1) >= 'a' && email.charAt(email.length()-1) <= 'z')) return "Invalid email address!";
					return null;
				}
			}
			return null;
		}
		catch(Exception ex)
		{
			return "Invalid email address!";
		}
	}
	public static String phoneValidity(String phone)
	{
		try
		{
			if(phone.length() != 11) return "Mobile number must contain exactly 11 digits";
			if(phone.charAt(0) != '0' || phone.charAt(1) != '1') return "Mobile number not valid";
			for(int i = 0; i < phone.length(); i++) if(!(phone.charAt(i) >= '0' && phone.charAt(i) <= '9')) return "Mobile number contains invalid characters!";
			return null;
		}
		catch(Exception ex)
		{
			return "Invalid Mobile Number!";
		}
	}
	public static String passwordValidity(String password, String conf)
	{
		try
		{
			if(!password.equals(conf)) return "Password mismatched";
			if(password.length() > 30 || password.length() < 8) return "Password must contain 8 to 30 characters";
			int symbols = 0, upper = 0, lower = 0, digit = 0;
			for(int i = 0; i < password.length(); i++)
			{
				if(password.charAt(i) == ' ') return "Password must not contain any space";
				if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') upper++;
				else if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z') lower++;
				else if(password.charAt(i) >= '0' && password.charAt(i) <= '9') digit++;
				else symbols++;
			}
			if(upper == 0) return "Password must contain at least 1 upper case letter";
			if(lower == 0) return "Password must contain at least 1 lower case letter";
			if(digit == 0) return "Password must contain at least 1 numerical character";
			if(symbols == 0) return "Password must contain at least 1 special character";
			return null;
		}
		catch(Exception ex)
		{
			return "Invalid Password!";
		}
	}
	public static File chooseFile(int fileType, JFrame parent)
	{
		JFileChooser filechooser = new JFileChooser();
		if(fileType == IMAGE_TYPE) filechooser.setFileFilter(Common.imgFilter);
		int report = filechooser.showDialog(parent, "Select Image File");
		if(report == JFileChooser.APPROVE_OPTION)
		{
			return filechooser.getSelectedFile();
		}
		return null;
	}
	public static String profileImageValidity(File file)
	{
		try
		{
			double imageSize = file.length();
			if(imageSize / 1048576.0 > 2.0) return "File size more than 2MB not accepted";
			return null;
		}
		catch(Exception ex)
		{
			return "Image file error";
		}
	}
	public static String pinValidity(String newPin, String confirmPin)
	{
		try
		{
			if(newPin.length() > 5 || newPin.length() < 5) return "Pin must be exactly of 5 digits only!";
			for(int i = 0; i < 5; i++) if(!(newPin.charAt(i) >= '0' && newPin.charAt(i) <= '9')) return "Pin Can Contain Only Digits!";
			if(!newPin.equals(confirmPin)) return "Pin Mismatched";
			return null;
		}
		catch(Exception ex)
		{
			return "Pin Error";
		}
	}
	public static ImageIcon getResizedAvatar(File imageFile)
	{
		try
		{
			Image resized = new ImageIcon(imageFile+"").getImage();
			return new ImageIcon(resized.getScaledInstance(160, 160, Image.SCALE_DEFAULT));
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getResizedAvatar() " + ex.getMessage());
			return new ImageIcon("others/data/0000000.jpg");
		}
	}
	public static ImageIcon getResizedImage(ImageIcon image, int width, int height)
	{
		try
		{
			Image resized = image.getImage();
			return new ImageIcon(resized.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getResizedAvatar() " + ex.getMessage());
			Image resized = (new ImageIcon("others/data/0000000.jpg")).getImage();
			return new ImageIcon(resized.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		}
	}
	public static int[] getBirthIndex(String date)
	{
		int year = -1, month = -1, day = -1;
		try
		{
			String yearString = "", monthString = "", dayString = "";
			int i = 0;
			for(; i < date.length() && date.charAt(i) != '-'; i++) yearString += date.charAt(i);
			for(i++; i < date.length() && date.charAt(i) != '-'; i++) monthString += date.charAt(i);
			for(i++; i < date.length() && date.charAt(i) != '-'; i++) dayString += date.charAt(i);
			year = Integer.parseInt(yearString);
			month = Integer.parseInt(monthString);
			day = Integer.parseInt(dayString);
		}
		catch(Exception ex)
		{
			System.out.println("Exception found in getBirthIndex() " + ex.getMessage());
		}
		finally
		{
			System.out.println(year + " " + month + " " + day);
			return new int[] {year, month, day};
		}
	}
	public static String getExtendedDate(String date)
	{
		int index[] = getBirthIndex(date);
		if(index[0] == -1 || index[1] == -1 || index[2] == -1)
		{
			return "Date parsing error";
		}
		String extended = "";
		extended += index[2]+" ";
		extended += months[index[1]-1]+" ";
		extended += ""+index[0];
		return extended;
	}
	public static String getTodayDate()
	{
		UserRepository dummy = new UserRepository();
		return dummy.getTodayDate();
	}
}