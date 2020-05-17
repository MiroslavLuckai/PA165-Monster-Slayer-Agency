package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.facade.HeroFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.HeroService;
import cz.muni.fi.pa165.msa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Miroslav Luckai 469288
 */
@Service
@Transactional
public class HeroFacadeImpl implements HeroFacade {

    @Autowired
    HeroService service;

    @Autowired
    UserService userService;

    @Autowired
    BeanMappingService mapper;

    @Override
    public HeroDTO createHero(HeroDTO heroDTO) {
        Hero hero = mapper.mapTo(heroDTO, Hero.class);
        service.createHero(hero);
        User user = userService.findUserById(hero.getUser().getId());
        user.setHero(true);
        userService.updateUser(user);
        hero.setUser(user);
        return mapper.mapTo(hero, HeroDTO.class);
    }

    @Override
    public void updateHero(HeroDTO heroDTO) {
        Hero hero = mapper.mapTo(heroDTO, Hero.class);
        service.updateHero(hero);
    }

    @Override
    public void removeHero(HeroDTO heroDTO) {
        Hero hero = mapper.mapTo(heroDTO, Hero.class);
        service.removeHero(hero);
    }

    @Override
    public HeroDTO findHero(Long id) {
        Hero hero = service.findHeroById(id);
        return (hero == null) ? null : mapper.mapTo(hero, HeroDTO.class);
    }

    @Override
    public HeroDTO findByHeroName(String heroName) {
        Hero hero = service.findHeroByName(heroName);
        return (hero == null) ? null : mapper.mapTo(hero, HeroDTO.class);
    }

    @Override
    public HeroDTO findByUserId(Long userId) {
        Hero hero = service.findHeroByUserId(userId);
        return (hero == null) ? null : mapper.mapTo(hero, HeroDTO.class);
    }

    @Override
    public List<HeroDTO> findAllHeroes() {
        return mapper.mapTo(service.findAllHeroes(), HeroDTO.class);
    }
}
