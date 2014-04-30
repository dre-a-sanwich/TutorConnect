package databaseHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
		private String name;
		private String password;
		
		public User(String name, String password){
			this.name = name.toUpperCase();
			this.password = password;
		}
		
		public User(String input){
			String[] split = input.split("#!");
			
			this.setName(split[0]);
			this.setPassword(split[1]);;
		}
		
		public User(){
			this(null, null);
		}
		
		public String getName() {
			return name;
		}
		
		protected void setName(String name) {
			this.name = name.toUpperCase();
		}
		
		protected String getPassword() {
			return password;
		}
		
		protected void setPassword(String password) {
			this.password = password;
		}
		
		/**
		 * Adds a new user to the table
		 **/
		protected static boolean addUser(Connection c, User user){
			
			Statement stmt = null;
			try {
				String sql = "";
				stmt = c.createStatement();
				sql = "SELECT * FROM USER WHERE NAME IS '" + user.getName() + "';" ;
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					return false;
				}
				sql = "INSERT INTO USER (NAME, Password)"
						+ "VALUES ('" + user.getName()
						+ "', '" + user.getPassword() + "');"; 	
				System.out.println(sql);
				stmt.executeUpdate(sql);
				stmt.close();
					  
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				e.getStackTrace();
				return false;
				//System.exit(0);
			}
			System.out.println("Entry created successfully");
			return true;
		}
		
		/**
		 * Adds a new user to the table
		 **/
		protected static boolean checkUser(Connection c, User user){
			
			Statement stmt = null;
			try {
				stmt = c.createStatement();
				String sql = "SELECT * FROM USER WHERE " 
						+ "NAME = '" + user.getName() + "'";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					if(rs.getString("PASSWORD").equals(user.getPassword()))
						return true;
				}
				stmt.close();
					  
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				e.getStackTrace();
				System.exit(0);
			}
			System.out.println("Entry created successfully");
			return false;
		}
		
		protected static String getUser(User user){
			return user.getName();
		}
		
}
