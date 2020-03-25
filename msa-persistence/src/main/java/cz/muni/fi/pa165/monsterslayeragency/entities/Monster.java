package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private String image;
}
