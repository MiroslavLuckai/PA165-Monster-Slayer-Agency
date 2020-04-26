package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.msa.dto.MonsterCreateDTO;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;
import cz.muni.fi.pa165.msa.facade.MonsterFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MonsterFacadeImpl implements MonsterFacade {

    @Autowired
    MonsterService monsterService;

    @Autowired
    BeanMappingService beanMappingService;

    @Override
    public Long createMonster(MonsterCreateDTO monsterCreateDTO) {
        Monster monster = beanMappingService.mapTo(monsterCreateDTO, Monster.class);
        monsterService.create(monster);
        return monster.getId();
    }

    @Override
    public boolean removeMonster(Long id) {
        Monster monster = monsterService.findById(id);
        if (monster == null)
            return false;
        monsterService.delete(monster);
        return true;
    }

    @Override
    public void changeName(MonsterDTO monsterDTO, String newName) {
        Monster monster = monsterService.findById(monsterDTO.getId());
        monster.setName(newName);
        monsterService.update(monster);
    }

    @Override
    public MonsterDTO findById(Long id) {
        Monster category = monsterService.findById(id);
        return (category == null) ? null : beanMappingService.mapTo(category,MonsterDTO.class);
    }

    @Override
    public List<MonsterDTO> findAll() {
        return beanMappingService.mapTo(monsterService.findAll(),MonsterDTO.class);
    }

    @Override
    public MonsterDTO findByName(String name) {
        Monster category = monsterService.findByName(name);
        return (category == null) ? null : beanMappingService.mapTo(category,MonsterDTO.class);

    }

    @Override
    public List<MonsterDTO> findByMonsterType(MonsterType monsterType) {
        return beanMappingService.mapTo(monsterService.findByMonsterType(monsterType),MonsterDTO.class);
    }

    @Override
    public List<MonsterDTO> findBySize(int size) {
        return beanMappingService.mapTo(monsterService.findBySize(size),MonsterDTO.class);
    }

}
