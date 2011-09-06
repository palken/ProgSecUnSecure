package no.ntnu.tdt4237;

import java.sql.*;
import java.util.*;
import java.util.Date;

import no.ntnu.tdt4237.helperactions.DateHelpers;

/*
 *  Layer for interaction between the model classes 
 *  and the database
 */
public class Database {

	private static Connection createConnection()
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

	public static boolean login(String username, String password) {
		
		System.out.println(username);
		System.out.println(password);
		Connection conn = createConnection();

		boolean authenticated = false;

		try {
			Statement statement = conn.createStatement();

			String query = "SELECT * FROM BlogUser WHERE username='" + username + "' AND password='" + password + "';";
			ResultSet resultSet  = statement.executeQuery(query);
			
			if (resultSet.next()) {
				authenticated = true;
			}
			
			resultSet.close();
			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return authenticated;
	}

	public static boolean saveUser(User u)
	{
		Connection conn = createConnection();

		boolean result;

		if( u != null)
		{
			if(!userExist(u))
			{
				try{
					Statement st = conn.createStatement();

					String query = "INSERT INTO BlogUser(username,password,email,fname,lname) VALUES('"+ u.getUserName() +"','"+u.getPassword()+"','"+u.getEmail()+"','"+u.getFirstName()+"','"+u.getLastName()+"');";
					st.executeUpdate(query);

					st.close();
					conn.close();
					result = true;

				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				} 
			}
			else
			{
				try{
					Statement st = conn.createStatement();

					String query = "UPDATE BlogUser SET password='"+u.getPassword()+"', email='"+u.getEmail()+"', fname='"+u.getFirstName()+"', lname='"+u.getLastName()+"' WHERE username='"+u.getUserName()+"';";
					st.executeUpdate(query);

					st.close();
					conn.close();
					result = true;

				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				}
			}
		}
		else
		{
			result = false;
		}

		return result;
	}

	private static boolean userExist(User u) {
		ArrayList<User> users = getUsers();

		boolean result = false;

		for(User user: users)
		{
			if(user.getUserName().equals(u.getUserName())) result = true;
		}

		return result;
	}

	public static ArrayList<User> getUsers()
	{
		ArrayList<User> list = new ArrayList<User>();

		Connection conn = createConnection();

		try{
			Statement st = conn.createStatement();

			String query = "SELECT * FROM BlogUser;";
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				list.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("fname"),rs.getString("lname")));
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return list;
	}

	public static User getUser(String username)
	{
		ArrayList<User> users =getUsers();
		User result = null;

		for(User u: users)
		{
			if(u.getUserName().equals(username)) result = u;
		}

		return result;
	}


	public static boolean savePost(BlogPost p)
	{
		Connection conn = createConnection();

		boolean result;

		if( p != null)
		{
			if(!postExist(p))
			{
				try{
					Statement st = conn.createStatement();

					String query = "INSERT INTO BlogPost(title,text,date,owner,picture) " +
							"VALUES('"+p.getTitle()+"','"+p.getText()+"','"+DateHelpers.utilDateToSqlDate(p.getDate())+
							"','"+p.getOwner().getUserName()+"','"+p.getPictureName()+"');";
					st.executeUpdate(query);

					st.close();
					conn.close();
					result = true;

				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				} 
			}
			else
			{
				try{
					Statement st = conn.createStatement();

					String query = "UPDATE BlogPost SET text='"+p.getText()+"', date='"+DateHelpers.utilDateToSqlDate(p.getDate())+"'  WHERE blogid='"+getPostId(p)+"';";
					st.executeUpdate(query);

					st.close();
					conn.close();
					result = true;

				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				}
			}
		}
		else
		{
			result = false;
		}

		return result;
	}

	public static boolean editPost(BlogPost oldPos, BlogPost newPost)
	{
		if (oldPos !=null && newPost != null)
		{
			int postId = getPostId(oldPos);
			if (postId >= 0)
			{
				Connection conn = createConnection();
				boolean result = false;
				try
				{
					Statement st = conn.createStatement();

					String query = "UPDATE BlogPost SET text='"+newPost.getText()+"', date='"+DateHelpers.utilDateToSqlDate(newPost.getDate())+"'" +
							"  WHERE blogid='"+postId+"';";
					st.executeUpdate(query);

					st.close();
					conn.close();
					result = true;

				} 
				catch (Exception e) {
					e.printStackTrace();
					result = false;
				}
				return result;
			}

		}
		return false;
	}

	public static boolean deletePost(BlogPost p)
	{
		Connection conn = createConnection();

		boolean result;

		if( p != null)
		{
			if(postExist(p))
			{
				try{
					Statement st = conn.createStatement();

					String query = "DELETE FROM `BlogPost` WHERE `title` = '"+p.getTitle()+"' AND " +
							"`date` = '"+DateHelpers.utilDateToSqlDate(p.getDate())+"' AND `owner` = '"+p.getOwner().getUserName()+"' LIMIT 1;";
					st.execute(query);
					result = st.getUpdateCount() == 1;
					st.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				} 
			}
			else
			{
				result = false;
			}
		}
		else
		{
			result = false;
		}

		return result;
	}

	public static boolean deleteComment(Comment c)
	{
		Connection conn = createConnection();

		boolean result;

		if( c != null)
		{
			int postId = getPostId(c.getPost());
			if (postId >= 0)
			{
				try{
					Statement st = conn.createStatement();

					String query = "DELETE FROM `BlogComment` WHERE `text` = '"+c.getText()+"' AND " +
							"`date` = '"+DateHelpers.utilDateToSqlDate(c.getDate())+"' AND " +
							"`owner` = '"+c.getOwner().getUserName()+"' AND `postid` = '"+postId+"' LIMIT 1;";
					st.execute(query);
					result = st.getUpdateCount() == 1;
					st.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				} 
			}
			else
			{
				result = false;
			}
		}
		else
		{
			result = false;
		}

		return result;
	}

	public static ArrayList<BlogPost> getPostForUser(User u)
	{
		ArrayList<BlogPost> list = new ArrayList<BlogPost>();

		Connection conn = createConnection();

		try{
			Statement st = conn.createStatement();

			String query = "SELECT * FROM BlogPost WHERE owner='"+u.getUserName()+"';";
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				list.add(new BlogPost(rs.getString("title"),rs.getString("text"),rs.getDate("date"),getUser(rs.getString("owner")),rs.getString("picture")));
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return list;
	}

	public static boolean saveComment(Comment c)
	{
		Connection conn = createConnection();

		boolean result;

		if( c != null)
		{

			try{
				Statement st = conn.createStatement();

				String query = "INSERT INTO BlogComment(owner,text,date,postid) VALUES('"+c.getOwner().getUserName()+"','"+c.getText()+"','"+DateHelpers.utilDateToSqlDate(c.getDate())+"','"+getPostId(c.getPost())+"');";
				st.executeUpdate(query);

				st.close();
				conn.close();
				result = true;

			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			} 
		}
		else
		{
			result = false;
		}

		return result;
	}


	private static int getPostId(BlogPost p) {

		int res = -1;
		Connection conn = createConnection();

		try{
			Statement st = conn.createStatement();

			String query = "SELECT * FROM BlogPost WHERE owner='"+p.getOwner().getUserName()+"';";
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				int id = rs.getInt("blogid");
				String title = rs.getString("title");
				Date date = rs.getDate("date");
				String username = rs.getString("owner");

				boolean titleMatch = title.equals(p.getTitle());
				boolean dateMatch = DateHelpers.compareDates(DateHelpers.utilDateToSqlDate(p.getDate()), date);
				boolean userMatch = username.equals(p.getOwner().getUserName());

				if(titleMatch && dateMatch && userMatch)
					res = id;
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return res;
	}


	private static boolean postExist(BlogPost p) {
		boolean res = false;

		Connection conn = createConnection();

		try{
			Statement st = conn.createStatement();

			String query = "SELECT * FROM BlogPost WHERE owner='"+p.getOwner().getUserName()+"';";
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				String title = rs.getString("title");
				Date date = rs.getDate("date");
				String username = rs.getString("owner");

				boolean titleMatch = title.equals(p.getTitle());
				boolean dateMatch = DateHelpers.compareDates(DateHelpers.utilDateToSqlDate(p.getDate()), date);
				boolean userMatch = username.equals(p.getOwner().getUserName());

				if(titleMatch && dateMatch && userMatch)
					res = true;
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return res;

	}

	//Not tested
	public static ArrayList<Comment> getPostComments(BlogPost p)
	{
		ArrayList<Comment> list = new ArrayList<Comment>();

		Connection conn = createConnection();

		try{
			Statement st = conn.createStatement();

			String query = "SELECT * FROM BlogComment WHERE postid='"+getPostId(p)+"';";
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				list.add(new Comment(getUser(rs.getString("owner")),rs.getString("text"),rs.getDate("date"),p));
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return list;
	}

	public static BlogPost getPost(User u , String title, Date date)
	{
		ArrayList<BlogPost> list = new ArrayList<BlogPost>();

		Connection conn = createConnection();

		try{
			Statement st = conn.createStatement();

			String query = "SELECT * FROM BlogPost WHERE owner='"+u.getUserName()+"' AND title='"+title+"' AND date='"+DateHelpers.utilDateToSqlDate(date)+"';";
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				list.add(new BlogPost(rs.getString("title"),rs.getString("text"),rs.getDate("date"),getUser(rs.getString("owner")), rs.getString("picture")));
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		if(list.size() > 0)
			return list.get(0);
		return null;

	}


}
