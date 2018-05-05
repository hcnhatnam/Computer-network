package stringExtractor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getData {
	
	public static List<GPS> getDatabaseData(String name){
	    String SQL = "SELECT kinhdo,vido FROM DULIEU where ten =  '" + name + "'" ;
	    Connection conn=Connected.createConnect();
		ResultSet rs=null;
		 Statement stmt = null;
        List<GPS> lst=new ArrayList<GPS>();
        try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                lst.add(new GPS(Double.parseDouble(rs.getString("kinhdo")),Double.parseDouble(rs.getString("vido"))));
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
            if (conn != null) try { conn.close(); } catch(Exception e) {}  
            
         }


        return lst;
    }


}
        
		
		
		
		
		
		
	
