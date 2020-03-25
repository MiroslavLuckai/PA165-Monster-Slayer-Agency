package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Monster {

    @Id
    private Long id;
    private String name;
    private int size;
    private MonsterType monsterType;
    private String image;
}
