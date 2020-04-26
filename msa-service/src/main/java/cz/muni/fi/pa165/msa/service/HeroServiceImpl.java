package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.HeroDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HeroServiceImpl implements HeroService{

    @Autowired
    private HeroDao heroDao;

    @Override
    public Hero createHero(Hero hero) {
        heroDao.addHero(hero);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) {
        heroDao.updateHero(hero);
    }

    @Override
    public void removeHero(Hero hero) {
        heroDao.removeHero(hero);
    }

    @Override
    public Hero findHeroById(Long id) {
        return heroDao.findHero(id);
    }

    @Override
    public Hero findHeroByName(String name) {
        return heroDao.findByHeroName(name);
    }

    @Override
    public Hero findHeroByUserId(Long id) {
        return heroDao.findByUserId(id);
    }

    @Override
    public List<Hero> findAllHeroes() {
        return heroDao.findAllHeroes();
    }
}
