package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.Food;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ludovit Kopcsanyi
 */
public class MonsterCreateDTO {

	private String name;
	private Set<Resistance> resistances = new HashSet<>();
	private MonsterType monsterType;
	private String image;
	private Food food;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MonsterCreateDTO that = (MonsterCreateDTO) o;
		return Objects.equals(getName(), that.getName()) &&
				Objects.equals(getResistances(), that.getResistances()) &&
				getMonsterType() == that.getMonsterType() &&
				Objects.equals(getImage(), that.getImage()) &&
				getFood() == that.getFood();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getResistances(), getMonsterType(), getImage(), getFood());
	}
}
