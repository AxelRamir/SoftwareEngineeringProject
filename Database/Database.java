package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rmd;
	
	public Database() {
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("Database/db.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		

		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	public ArrayList<String>query(String query){
		ArrayList<String>list = new ArrayList<String>();
		try {
			//1. Create a statement from connection object
			stmt = conn.createStatement();
			
			//2. Execute query on statement
			rs = stmt.executeQuery(query);
			
			//3. get the # of columns from the ResultSet
			rmd = rs.getMetaData();
			int noOfColumns = rmd.getColumnCount();
			
			while(rs.next()) {
				String record = "";
				
				for(int i = 0; i < noOfColumns; i++) {
					String field = rs.getString(i + 1);
					record += field;
					if(i < noOfColumns - 1) {
						record += ",";
					}
					list.add(record);
				}
			}
			if(list.size() > 0) {
				return list;
			}
			else{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean executeDML(String dml) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(dml);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
