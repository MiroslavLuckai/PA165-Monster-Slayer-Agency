package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.Skill;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Ludovit Kopcsanyi
 */
@Entity
public class Hero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;

	@Column(nullable = false, unique = true)
	private String name;
	/*
	 Commented this one out as hibernate cannot map array
	 either change it to list and add annotation @ElementCollection (create separate table for it)
	 or map it according to this: https://vladmihalcea.com/how-to-map-java-and-sql-arrays-with-jpa-and-hibernate/
	private Skill[] skills;
	 */
	private String image;

	public Hero() {}

	public Hero(User user, String name, Skill[] skills, String image) {
		this.user = user;
		this.name = name;
		//this.skills = skills;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String heroName) {
		this.name = heroName;
	}
/*
	public Skill[] getSkills() {
		return skills;
	}

	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
*/
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
		Hero hero = (Hero) o;
		return getUser().equals(hero.getUser()) &&
				getName().equals(hero.getName()) &&
				//Arrays.equals(getSkills(), hero.getSkills()) &&
				getImage().equals(hero.getImage());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getUser(), getName(), getImage());
		//result = 31 * result + Arrays.hashCode(getSkills());
		return result;
	}
}
