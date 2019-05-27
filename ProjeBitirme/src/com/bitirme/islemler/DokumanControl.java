package com.bitirme.islemler;


import java.sql.SQLException;

import com.bitirme.database.Database;
public class DokumanControl {
	
	public static boolean YetkiKontrol(String username) throws SQLException {
		if(Database.yetkiKontrol(username)) {
			return true;
		}
		else return false;
	}
	
	public static boolean dokumanEkle(String ilgialani,String username,String dokumanName) throws SQLException {
		if(Database.dokumanEkle(ilgialani, username, dokumanName)) {
			return true;
		}
		return false;
	}
	public static boolean dokumanSonuclandir(String dokumanid,String onayDurum) throws SQLException {
		if(Database.dokumanSonuclandir(dokumanid, onayDurum)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
