import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDatabase {
	public static void main(String[] args) {
		System.out.println("Running Database Test");
		
		String url 		= "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
		String user 	= "root";
		String password = "testpassword";
		
		Connection con = null;
		Statement stt = null;
		ResultSet res = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
				
			stt = con.createStatement();
			
			// Create and Select DB
			
			stt.execute("CREATE DATABASE IF NOT EXISTS InvestmentBankingApp");
			stt.execute("USE InvestmentBankingApp");
			
			// CREATE TABLE
			stt.execute("DROP TABLE IF EXISTS people");
			stt.execute("CREATE TABLE people("
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "fname VARCHAR(25),"
					+ "lname VARCHAR(25),"
					+ "PRIMARY KEY(id)"
					+ ")");
			
			// Add some entries
			stt.execute("INSERT INTO people (fname,lname) VALUES"
					+ "('Jill', 'Hill'),('Joe', 'Bloggs'),('Mary', 'Bloggs')");
			
			// Get people with surname Bloggs
			res = stt.executeQuery("SELECT * FROM people WHERE lname = 'Bloggs'");
			
			while(res.next()) {
				System.out.println(res.getString("fname") + " " + res.getString("lname"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { res.close(); } catch (Exception e) { /* ignored */ };
	     	try { stt.close(); } catch (Exception e) { /* ignored */ };
		    try { con.close(); } catch (Exception e) { /* ignored */ };
		}
	}
}
