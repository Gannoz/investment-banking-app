import java.sql.*;

public class TestDatabase {
	public static void main(String[] args) {
		System.out.println("Running Database Test");
		
		String url 		= "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
		String user 	= "root";
		String password = "testpassword";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement stt = con.createStatement();
			
			// Create and Select DB
			stt.execute("CREATE DATABASE IF NOT EXISTS test");
			stt.execute("USE test");
			
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
			ResultSet res = stt.executeQuery("SELECT * FROM people WHERE lname = 'Bloggs'");
			
			while(res.next()) {
				System.out.println(res.getString("fname") + " " + res.getString("lname"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
