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
	private String heroName;
	private Skill[] skills;
	private String image;

	public Hero(User user, String heroName, Skill[] skills, String image) {
		this.user = user;
		this.heroName = heroName;
		this.skills = skills;
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

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
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
				getHeroName().equals(hero.getHeroName()) &&
				Arrays.equals(getSkills(), hero.getSkills()) &&
				getImage().equals(hero.getImage());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getUser(), getHeroName(), getImage());
		result = 31 * result + Arrays.hashCode(getSkills());
		return result;
	}
}
