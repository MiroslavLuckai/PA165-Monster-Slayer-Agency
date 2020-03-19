package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.Food;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;

import java.util.Arrays;
import java.util.Objects;

public class Monster {
    private int id;
    private String name;
    private int size;
    private Food food;
    private Resistance[] resistances;
    private byte[] image;

    public Monster(int id, String name, int size, Food food, Resistance[] resistances, byte[] image) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.food = food;
        this.resistances = resistances;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Resistance[] getResistances() {
        return resistances;
    }

    public void setResistances(Resistance[] resistances) {
        this.resistances = resistances;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monster)) return false;
        Monster monster = (Monster) o;
        return getId() == monster.getId() &&
                getSize() == monster.getSize() &&
                getName().equals(monster.getName()) &&
                getFood() == monster.getFood() &&
                Arrays.equals(getResistances(), monster.getResistances()) &&
                Arrays.equals(getImage(), monster.getImage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getSize(), getFood());
        result = 31 * result + Arrays.hashCode(getResistances());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
