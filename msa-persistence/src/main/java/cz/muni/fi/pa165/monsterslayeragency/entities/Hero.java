package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.Skill;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ludovit Kopcsanyi
 */
@Entity(name = "Hero")
@Table(name = "hero")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @Column(name = "skills")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;

    @Column(name = "image")
    private String image;


    public Hero(User user, String name, Set<Skill> skills, String image) {
        this.user = user;
        this.name = name;
        this.skills = skills;
        this.image = image;
    }

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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean addSkill(Skill skill) {
        return this.skills.add(skill);
    }

    public boolean removeSkill(Skill skill) {
        return this.skills.remove(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return getId() == hero.getId() &&
                Objects.equals(getUser(), hero.getUser()) &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getSkills(), hero.getSkills()) &&
                Objects.equals(getImage(), hero.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getName(), getSkills(), getImage());
    }
}
