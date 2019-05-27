package com.bitirme.islemler;

import com.bitirme.database.Database;
import com.bitirme.pojo.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserControl {
	
	public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	public static  boolean UserMatching(String username,String password) throws SQLException {

		//DB YE USER'I GONDERIP KONTROL EDECEGIMIZ YER
		if(Database.userControl(username, password)) {
			System.out.println(getMd5("Nakipali21"));
			return true;
		}
		else return false;
	}
	
	public static boolean UserAdding(User user) throws SQLException {
		User user1 = new User();
		user1=user;
		System.out.println(user1.getEmail());
		if(Database.userAdding(user1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String IlgiAlaniGetir(String username) throws SQLException {
		String ilgialani="";
		ilgialani = Database.UserIlgiAlani(username);
		return ilgialani;
	}
	
	public static boolean baskanKontrol(String baskanName,String baskanPw) throws SQLException {
		if(Database.baskanKontrol(baskanName, baskanPw)) {
			return true;
		}
		else return false;
	}
		
}
