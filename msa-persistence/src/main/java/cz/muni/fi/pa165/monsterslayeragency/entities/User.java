package cz.muni.fi.pa165.monsterslayeragency.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ludovit Kopcsanyi
 */
@Entity(name = "User")
@Table(name = "users")
public class User extends AbstractEntity{

	@Column(unique = true, nullable = false, name = "email")
	private String email;

    @Column(nullable = false, name = "password")
	private String password;

    @Column(unique = true, nullable = false, name = "user_name")
	private String userName;

    @Column(name = "image")
	private String image;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_hero")
	private boolean isHero;

	public User() {
	}

	public User(String email, String password, String userName, String image, boolean isAdmin) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.image = image;
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return super.id;
	}

	public void setId(Long id) {
		super.id = id;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean isHero() {
		return isHero;
	}

	public void setHero(boolean hero) {
		isHero = hero;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return getId() == user.getId() &&
				Objects.equals(getEmail(), user.getEmail()) &&
				Objects.equals(getPassword(), user.getPassword()) &&
				Objects.equals(getUserName(), user.getUserName()) &&
				Objects.equals(getImage(), user.getImage()) &&
				Objects.equals(isAdmin(), user.isAdmin()) &&
				Objects.equals(isHero(), user.isHero());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getEmail(), getPassword(), getUserName(), getImage(), isAdmin(), isHero());
	}
}
