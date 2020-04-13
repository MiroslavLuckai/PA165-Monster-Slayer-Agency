package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.Food;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Filip Daniel Fedin
 */

@Entity(name = "Monster")
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "size")
    private int size;

    @Column(name = "resistances")
    @ElementCollection(targetClass = Resistance.class)
    @Enumerated(EnumType.STRING)
    private Set<Resistance> resistances = new HashSet<>();

    @Column(name = "monster_type")
    private MonsterType monsterType;

    @Column(name = "image")
    private String image;

    @Column(name = "food")
    private Food food;

    public Monster() {
    }

    public Monster(String name, int size, Set<Resistance> resistances, MonsterType type, String image, Food food) {
        this.name = name;
        this.size = size;
        this.resistances = resistances;
        this.monsterType = type;
        this.image = image;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Set<Resistance> getResistances() {
        return resistances;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public String getImage() {
        return image;
    }

    public Food getFood() {
        return food;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setResistances(Set<Resistance> resistances) {
        this.resistances = resistances;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public boolean addResistance(Resistance resistance) {
        return this.resistances.add(resistance);
    }

    public boolean removeResistance(Resistance resistance) {
        return this.resistances.remove(resistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monster)) return false;
        Monster monster = (Monster) o;
        return getSize() == monster.getSize() &&
                Objects.equals(getId(), monster.getId()) &&
                Objects.equals(getName(), monster.getName()) &&
                Objects.equals(getResistances(), monster.getResistances()) &&
                getMonsterType() == monster.getMonsterType() &&
                Objects.equals(getImage(), monster.getImage()) &&
                getFood() == monster.getFood();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSize(), getResistances(), getMonsterType(), getImage(), getFood());
    }
}
