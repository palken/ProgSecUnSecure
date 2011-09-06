package no.ntnu.tdt4237;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/*
 *  Creates the database tables and add some values 
 *  for testing purpose.
 */
public class DatabaseCreator {

	private Connection createConnection()
	{
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn = DriverManager.getConnection(
					"jdbc:mysql://mysql.stud.ntnu.no/"+Config.SQL_DB,
					Config.SQL_USERNAME, Config.SQL_PASSWORD
					);
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		} 
		
		return conn;
	}
	
	private boolean createUserTable()
	{
		Connection conn = createConnection();
		
		boolean result;
		
		try{
			Statement st = conn.createStatement();

			String query = "CREATE TABLE BlogUser (userid int unsigned AUTO_INCREMENT NOT NULL, username varchar(32), password varchar(32), email varchar(32),fname varchar(32),lname varchar(32), PRIMARY KEY (userid));";

			st.executeUpdate("DROP TABLE IF EXISTS BlogUser");
			st.executeUpdate(query);

			st.close();
			conn.close();
		result = true;
		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	private boolean createUsers()
	{
		Connection conn = createConnection();
		
		boolean result;
		
		try{
			Statement st = conn.createStatement();

			st.executeUpdate("TRUNCATE TABLE BlogUser;");
			
			String query = "INSERT INTO BlogUser(username,password,email, fname, lname) VALUES('Arne','passord','arne@arnesmailboks.no', 'Arne', 'Olsen');";
			st.executeUpdate(query);
			query = "INSERT INTO BlogUser(username,password,email, fname, lname) VALUES('Ole','passord','oleidole@hotmail.no', 'Ole', 'Dole');";
			st.executeUpdate(query);
			query = "INSERT INTO BlogUser(username,password,email, fname, lname) VALUES('Lise','passord','lisesmail@yahoo.no', 'Lise', 'Hansen');";
			st.executeUpdate(query);
			query = "INSERT INTO BlogUser(username,password,email, fname, lname) VALUES('Tone','passord','tone@post.no', 'Tone', 'Trulsen');";
			st.executeUpdate(query);

			st.close();
			conn.close();
		result = true;
		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	private boolean createBlogTable()
	{
		Connection conn = createConnection();
		
		boolean result;
		
		try{
			Statement st = conn.createStatement();

			String query = "CREATE TABLE BlogPost (blogid int unsigned AUTO_INCREMENT NOT NULL, title varchar(32), text varchar(512), date date, owner varchar(32), PRIMARY KEY (blogid));";

			st.executeUpdate("DROP TABLE IF EXISTS BlogPost");
			st.executeUpdate(query);

			st.close();
			conn.close();
		result = true;
		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	private boolean createPosts()
	{
		Connection conn = createConnection();
		
		boolean result;
		
		try{
			Statement st = conn.createStatement();

			st.executeUpdate("TRUNCATE TABLE BlogPost;");
			
			String query = "INSERT INTO BlogPost(title,text,date,owner) VALUES('Fint v�r i dag!','Skikkelig bra v�r p� gl�shaugen i dag :)','2010-08-27','arne');";
			st.executeUpdate(query);
			query = "INSERT INTO BlogPost(title,text,date,owner) VALUES('Jeg er t�ff!','Woho!!','2010-08-26','arne');";
			st.executeUpdate(query);

			st.close();
			conn.close();
		result = true;
		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	private boolean createCommentTable()
	{
		Connection conn = createConnection();
		
		boolean result;
		
		try{
			Statement st = conn.createStatement();

			String query = "CREATE TABLE BlogComment (commentid int unsigned AUTO_INCREMENT NOT NULL, owner varchar(32), text varchar(512), date date, postid int, PRIMARY KEY (commentid));";

			st.executeUpdate("DROP TABLE IF EXISTS BlogComment");
			st.executeUpdate(query);

			st.close();
			conn.close();
		result = true;
		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	private boolean createComments()
	{
		Connection conn = createConnection();
		
		boolean result;
		
		try{
			Statement st = conn.createStatement();

			st.executeUpdate("TRUNCATE TABLE BlogComment;");
			
			String query = "INSERT INTO BlogComment(owner,text,date,postid) VALUES('Tone','Helt enig!','2010-08-28','1');";
			st.executeUpdate(query);
			query = "INSERT INTO BlogComment(owner,text,date,postid) VALUES('Lise','Ikke p� dragvoll!','2010-08-28','1');";
			st.executeUpdate(query);

			st.close();
			conn.close();
		result = true;
		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	public void createDatabase()
	{
		createUserTable();
		createUsers();
		createBlogTable();
		createPosts();
		createCommentTable();
		createComments();
	}
	
}
