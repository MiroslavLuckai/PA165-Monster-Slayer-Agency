package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.Skill;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Ludovit Kopcsanyi
 */
public class Hero {

	private User user;
	private String name;
	private Skill[] skills;
	private String image;

	public Hero(User user, String name, Skill[] skills, String image) {
		this.user = user;
		this.name = name;
		this.skills = skills;
		this.image = image;
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

	public void setName(String name) {
		this.name = name;
	}

	public Skill[] getSkills() {
		return skills;
	}

	public void setSkills(Skill[] skills) {
		this.skills = skills;
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
		Hero hero = (Hero) o;
		return getUser().equals(hero.getUser()) &&
				getName().equals(hero.getName()) &&
				Arrays.equals(getSkills(), hero.getSkills()) &&
				getImage().equals(hero.getImage());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getUser(), getName(), getImage());
		result = 31 * result + Arrays.hashCode(getSkills());
		return result;
	}
}
