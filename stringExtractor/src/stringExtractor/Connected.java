package stringExtractor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*DoLongBien*/
public class Connected {
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
