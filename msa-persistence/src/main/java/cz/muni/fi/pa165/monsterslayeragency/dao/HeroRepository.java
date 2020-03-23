package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Hero repository extends CrudRepository
 * CrudRepository should take care of all the basic crud operations and it should also be able
 * make your custom method works if named properly
 * Doc: https://docs.spring.io/spring-data/jpa/docs/1.7.2.RELEASE/reference/html/#repositories.custom-implementations
 * @author Miroslav Luckai
 */
public interface HeroRepository extends CrudRepository<Hero, Long> {
    /**
     * finds hero by its hero nick-name(hero nick-name have to be unique)
     * @param heroName hero nick-name of the user
     * @return instance of class Hero with wanted nick-name, hopefully null otherwise
     */
    Hero findByHeroName(String heroName);

    /**
     * finds hero profile by its underlying user
     * @param user user whose hero profile we are looking for
     * @return instance of class Hero with wanted underlying user, hopefully null otherwise
     */
    Hero findByUser(User user);

}
