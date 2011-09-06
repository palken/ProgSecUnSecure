package no.ntnu.tdt4237;

public class Authentication {

	public static User logIn(String username, String password)
	{
		if (Database.login(username, password)) {
			User user = Database.getUser(username);
			if (user != null) {
				return user;
			}
		}
		return null;
	}
}
