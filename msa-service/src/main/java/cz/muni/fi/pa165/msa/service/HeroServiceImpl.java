package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.HeroDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.msa.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService{

    @Autowired
    private HeroDao heroDao;

    private String HERO_IS_NULL_MESSAGE = "Hero cannot be null.";

    @Override
    public Hero createHero(Hero hero) {
        Validator.validate(hero, HERO_IS_NULL_MESSAGE);
        heroDao.addHero(hero);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) {
        Validator.validate(hero, HERO_IS_NULL_MESSAGE);
        heroDao.updateHero(hero);
    }

    @Override
    public void removeHero(Hero hero) {
        Validator.validate(hero, HERO_IS_NULL_MESSAGE);
        heroDao.removeHero(hero);
    }

    @Override
    public Hero findHeroById(Long id) {
        Validator.validate(id, "Id cannot be null");
        return heroDao.findHero(id);
    }

    @Override
    public Hero findHeroByName(String name) {
        Validator.validate(name, "Name cannot be null.");
        return heroDao.findByHeroName(name);
    }

    @Override
    public Hero findHeroByUserId(Long id) {
        Validator.validate(id, "Id cannot be null");
        return heroDao.findByUserId(id);
    }

    @Override
    public List<Hero> findAllHeroes() {
        return heroDao.findAllHeroes();
    }
}
