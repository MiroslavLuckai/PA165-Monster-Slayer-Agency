package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Filip Daniel Fedin
 */

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private MonsterType monsterType;

    @ElementCollection
    private List<Resistance> resitances;

    @Column(nullable = false)
    private String image;

    public Monster() {
    }

    public Monster(String name, int size, MonsterType monsterType, List<Resistance> resistances, String image) {
        this.name = name;
        this.size = size;
        this.monsterType = monsterType;
        this.resitances = resistances;
        this.image = image;
    }
}
