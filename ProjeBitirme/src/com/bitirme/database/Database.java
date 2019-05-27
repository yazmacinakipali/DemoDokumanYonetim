package com.bitirme.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.bitirme.pojo.User;

public class Database {

	static Connection conn = null;
	static Statement  stmt = null;
	
	public  Database() {}


	public static void  userEkle(User user) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query = "insert into users(username,password,email,tel,yetki,) values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getTel()+"','"+user.getYetki()+"')";       
			Statement stmt = conn.createStatement();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}
	}
	
	
	public static  boolean userControl(String username,String password) throws SQLException {
		boolean loginStatus = false;
		int total=0;

		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query ="select count(id) as TOTAL from users where username='"+username+"' and password='"+password+"' "; 
			stmt = conn.createStatement();
			

			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				total = rs.getInt("TOTAL");
			}
			
			if(total==1) loginStatus=true;
			query=null;
			
		} 
		
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}
		
		return loginStatus;
	}
		
	public static boolean yetkiKontrol(String username) throws SQLException {
		
		boolean loginStatus = false;
		String yetki="";

		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query ="select yetki as YETKI from users where username='"+username+"'"; 
			stmt = conn.createStatement();
			

			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				yetki = rs.getString("YETKI");
				System.out.println("YETKIIIIIIIIIIIIII="+yetki);
			}
			System.out.println("YETKI==="+yetki);
			if(yetki.equals("hakem")) { loginStatus=true; }
			query=null;
			
		} 
		
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally {
			System.out.println("loginstatus"+loginStatus);
			stmt.close();
			conn.close();
		}
		
		return loginStatus;

	}
	
	
	
	public static String UserIlgiAlani(String username) throws SQLException {
		
		String ilgiAlani = "";

		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query ="select ilgialani from users where username='"+username+"'"; 
			stmt = conn.createStatement();
			

			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				ilgiAlani = rs.getString("ilgialani");
				System.out.println("ILGI ALANI="+ilgiAlani);
			}			
		} 
		
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally {
			System.out.println("ilgiAlani"+ilgiAlani);
			stmt.close();
			conn.close();
		}
		
		return ilgiAlani;

	}
	
	
	public static List<String> hakemList(String ilgialani) throws SQLException {
		System.out.println("here-hakemList()--ilgialani=="+ilgialani);
		List<String> hakemList = new ArrayList<String>();
		String hakemUsername="";
		stmt=null;
		conn=null;
		
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query1 ="select username from users where yetki='hakem' and ilgialani='"+ilgialani+"' ";
			stmt = conn.createStatement();
			

			ResultSet rs = stmt.executeQuery(query1);
			
			while(rs.next()) {
				hakemUsername = rs.getString("username");
				hakemList.add(hakemUsername);
				System.out.println("hakemUsername===="+hakemUsername);
			}
			
		} 
		
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}
			
		
		return hakemList;
	}
	

	public static boolean dokumanEkle(String ilgialani,String username,String dokumanName) throws SQLException {
		boolean status = false;
		List<String> seciliHakemList = new ArrayList<String>();
		List<String> hakemListForAdding = new ArrayList<String>();
		hakemListForAdding = hakemList(ilgialani);
		int listSize = hakemListForAdding.size();
		int rs = 0;
		System.out.println("hs:"+hakemListForAdding.size());
		System.out.println("hs0--:"+hakemListForAdding.get(0)+hakemListForAdding.get(1)+hakemListForAdding.get(2));
		ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int i=0; i<listSize; i++) {
	            list.add(new Integer(i));
	            System.out.println(list.get(i));
	        }
	        Collections.shuffle(list);
	        for (int i=0; i<3; i++) {
	        	seciliHakemList.add(hakemListForAdding.get(list.get(i)));
	        	System.out.println("SECILIHAKEM"+hakemListForAdding.get(list.get(i)));
	        }		
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query2 = "INSERT INTO dokumanlar(dokumanname,dokumanuser,hakem1,hakem2,hakem3,sonuc) values('"+dokumanName+"','"+username+"','"+seciliHakemList.get(0).toString()+"','"+seciliHakemList.get(1).toString()+"','"+seciliHakemList.get(2).toString()+"','hayir');"; //--dokuman eklemenin yapilacagi query
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(query2);
			status=true;
			System.out.println("exec="+rs);			
		}
		
		catch(Exception exp) {
			exp.printStackTrace();
		}
		finally {
			hakemListForAdding=null;
			seciliHakemList=null;
			list=null;
			stmt.close();
			conn.close();
		}

		return status;
	}
	
	public static boolean puanEkleme(String query,String dokumanId) throws SQLException{
		boolean puanStatus = false;
		int did = Integer.parseInt(dokumanId);
		try {
			int rs=0;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(query);
			System.out.println("exec="+rs);	
			puanStatus=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}
		return puanStatus;
	}
	
	public static boolean userAdding(User user) throws SQLException {
		boolean status = false;
		int rs=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query = "INSERT INTO users(username,password,email,tel,yetki,ilgialani) values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getTel()+"','"+user.getYetki()+"','"+user.getIlgialani()+"');"; //--dokuman eklemenin yapilacagi query
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(query);
			status=true;
			System.out.println("exec="+rs);			
		}
		
		catch(Exception exp) {
			exp.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}

		return status;
	}
	
	public static boolean baskanKontrol(String baskanName,String baskanPw) throws SQLException {
		boolean loginStatus = false;
		int total=0;

		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			String query ="select count(id) as TOTAL from users where username='"+baskanName+"' and password='"+baskanPw+"' and yetki='baskan' "; 
			stmt = conn.createStatement();
			

			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				total = rs.getInt("TOTAL");
			}
			
			if(total==1) loginStatus=true;
			query=null;
			
		} 
		
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}
		
		return loginStatus;

	}
	
	
	public static boolean dokumanSonuclandir(String dokumanid,String onayDurum) throws SQLException{
		boolean queryStatus = false;
		int did = Integer.parseInt(dokumanid);
		String query = "update dokumanlar set sonuc='evet' , kabul='"+onayDurum+"' where dokumanid='"+did+"' ";
		try {
			int rs=0;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(query);
			System.out.println("exec="+rs);	
			queryStatus=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
			conn.close();
		}
		return queryStatus;
	}

}
