package no.ntnu.tdt4237;

public class User {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String username;
	
	public User() {
		
	}
	
	public User(String username, String password, String email, String firstName, String LastName) {
		this(username, password, email);
		setFirstName(firstName);
		setLastName(LastName);
	}
	
	public User(String username, String password, String email) {
		setUserName(username);
		setPassword(password);
		setEmail(email);
	}
	
	public User(String firstName) {
		setFirstName(firstName);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(this.getClass()) && 
				this.getUserName() != null && ((User)obj).getUserName() != null &&
				this.getUserName().equals(((User)obj).getUserName()))
		{
			return true;
		}
		return super.equals(obj);
	}

	
}
