package stringExtractor;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
/*DoLongBien*/
public class CommitData {
	
	Connection conn = CommitData.createConnect();
	public void commitData(String str) {
		String[] splitString = str.split("\\|");
		String ten = splitString[0];
		String kinhdo = splitString[1];
		String vido = splitString[2];
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO DULIEU (ten,kinhdo,vido) VALUES (" + "\'" + ten + "\'" + "," +  "\'" + kinhdo  + "\'" + ","  + "\'" + vido + "\'" + ");");
			System.out.println("Saved data to database successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			 if (conn != null) try { conn.close(); } catch(Exception e) {} 
			 }
	}
	public static Connection createConnect() {
		Connection conn=null;
		String dbURL = "jdbc:sqlserver://localhost;databaseName=mangmaytinh;user=mangmaytinh;password=dolongbien";
	 try {
	    conn = DriverManager.getConnection(dbURL);
	    if (conn != null) {
	      System.out.println("Connected");

	    }
	   }
	 catch (SQLException ex) {
	     System.err.println("Cannot connect database, " + ex);
	   }
	 
	 return conn;
	}
	
	
}
