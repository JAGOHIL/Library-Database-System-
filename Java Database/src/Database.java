import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	
	public Database(String driver,String database, String username, String password) throws SQLException,ClassNotFoundException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(database,username,password);
		
		
	}
	
	public void query(String query) throws SQLException{
		this.stmt = this.conn.createStatement();
		this.rs = stmt.executeQuery(query);
		this.rsmd = this.rs.getMetaData();
	}
	
	public ResultSet getResults() throws SQLException{
		return this.rs;
	}
	
	public void printResults() throws SQLException{
		int totalFields = this.rsmd.getColumnCount();
		while (rs.next()) {
			for(int i = 1; i<totalFields;i++) {
				if(i>1)System.out.print(",");
				String fieldValue = rs.getString(i);
				System.out.print(fieldValue);
			}
		}
	}
	

}
