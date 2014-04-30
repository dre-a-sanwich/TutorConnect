package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestKinnect {

	private static Connection c;
	
	public  void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:tutorConnect.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			e.printStackTrace(System.out);
			c = null;
			System.exit(0);
		}
	}

}
