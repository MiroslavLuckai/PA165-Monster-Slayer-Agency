package cz.muni.fi.pa165.monsterslayeragency.entities;

import java.util.Objects;

/**
 * @author Ludovit Kopcsanyi
 */
public class User {

	private String email;
	private String password;
	private String userName;
	private String image;

	public User(String email, String password, String userName, String image) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.image = image;
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
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(getEmail(), user.getEmail()) &&
				Objects.equals(getPassword(), user.getPassword()) &&
				Objects.equals(getUserName(), user.getUserName()) &&
				Objects.equals(getImage(), user.getImage());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getPassword(), getUserName(), getImage());
	}
}
