package cz.muni.fi.pa165.monsterslayeragency.entities;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ludovit Kopcsanyi
 */

@Entity
public class Hero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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

    public Hero() {
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

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getId() == hero.getId() &&
                Objects.equals(getUser(), hero.getUser()) &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getImage(), hero.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getName(), getImage());
    }
}
