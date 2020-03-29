package cz.muni.fi.pa165.monsterslayeragency.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ludovit Kopcsanyi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
}
