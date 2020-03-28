package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Filip Daniel Fedin
 */

@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private int size;

    @ElementCollection
    private List<Resistance> resistances;

    @Column
    private MonsterType monsterType;

    @Column
    private String image;

    public Monster() {
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

    public List<Resistance> getResistances() {
        return resistances;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public String getImage() {
        return image;
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

    public void setResistances(List<Resistance> resistances) {
        this.resistances = resistances;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public void setImage(String image) {
        this.image = image;
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
                Objects.equals(getImage(), monster.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSize(), getResistances(), getMonsterType(), getImage());
    }
}
