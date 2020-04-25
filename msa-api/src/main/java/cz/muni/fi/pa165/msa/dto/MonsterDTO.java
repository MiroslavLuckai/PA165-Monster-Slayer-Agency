package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.Food;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MonsterDTO {

    private Long id;
    private String name;
    private Set<Resistance> resistances = new HashSet<>();
    private MonsterType monsterType;
    private String image;
    private int size;
    private Food food;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonsterDTO)) return false;
        MonsterDTO that = (MonsterDTO) o;
        return getSize() == that.getSize() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getResistances(), that.getResistances()) &&
                getMonsterType() == that.getMonsterType() &&
                Objects.equals(getImage(), that.getImage()) &&
                getFood() == that.getFood();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getResistances(), getMonsterType(), getImage(), getSize(), getFood());
    }
}
