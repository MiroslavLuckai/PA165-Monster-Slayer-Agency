package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.facade.HeroFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;

public class HeroFacadeImpl implements HeroFacade {

    @Autowired
    HeroService service;

    @Autowired
    BeanMappingService mapper;

    @Override
    public Long createHero(HeroDTO heroDTO) {
        Hero hero = mapper.mapTo(heroDTO, Hero.class);
        return service.createHero(hero);
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
}
