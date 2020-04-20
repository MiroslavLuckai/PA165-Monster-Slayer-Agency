package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.msa.dto.MonsterCreateDTO;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;
import cz.muni.fi.pa165.msa.facade.MonsterFacade;

import java.util.List;

public class MonsterFacadeImpl implements MonsterFacade {

    @Override
    public Long createMonster(MonsterCreateDTO monster) {
        return null;
    }

    @Override
    public boolean removeMonster(Long id) {
        return false;
    }

    @Override
    public MonsterDTO findById(Long id) {
        return null;
    }

    @Override
    public List<MonsterDTO> findAll() {
        return null;
    }

    @Override
    public MonsterDTO findByName(String name) {
        return null;
    }

    @Override
    public List<MonsterDTO> findByMonsterType(MonsterType monsterType) {
        return null;
    }

    @Override
    public List<MonsterDTO> findBySize(int size) {
        return null;
    }

}
