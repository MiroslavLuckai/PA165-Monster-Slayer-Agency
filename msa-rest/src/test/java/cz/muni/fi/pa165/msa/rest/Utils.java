package cz.muni.fi.pa165.msa.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.monsterslayeragency.enums.*;
import cz.muni.fi.pa165.msa.dto.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static UserDTO getUserDTODummy1() {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("default@mail.com");
        user.setImage("test_image");
        user.setUserName("Geralt");
        user.setAdmin(false);
        return user;
    }

    public static UserDTO getUserDTODummy2() {
        UserDTO user = new UserDTO();
        user.setId(2L);
        user.setEmail("normal@mail.com");
        user.setImage("image");
        user.setUserName("Peter");
        user.setAdmin(true);
        return user;
    }

    public static MonsterDTO getMonsterDTODummy1() {
        MonsterDTO monster = new MonsterDTO();
        monster.setId(1L);
        monster.setName("Beast Monster");
        monster.setSize(10);
        Set<Resistance> resistanceSet = new HashSet<>();
        resistanceSet.add(Resistance.ICE);
        resistanceSet.add(Resistance.ROCK);
        monster.setResistances(resistanceSet);
        monster.setMonsterType(MonsterType.BEAST);
        return monster;
    }

    public static MonsterDTO getMonsterDTODummy2() {
        MonsterDTO monster = new MonsterDTO();
        monster.setId(2L);
        monster.setName("Hybrid Monster");
        monster.setSize(15);
        Set<Resistance> resistanceSet = new HashSet<>();
        resistanceSet.add(Resistance.PSYCHIC);
        monster.setResistances(resistanceSet);
        monster.setMonsterType(MonsterType.HYBRID);
        return monster;
    }

    public static MonsterDTO getMonsterDTODummy3() {
        MonsterDTO monster = new MonsterDTO();
        monster.setId(3L);
        monster.setName("Ghoul");
        monster.setSize(10);
        monster.setMonsterType(MonsterType.NECROPHAGE);
        Set<Resistance> monsterSet = new HashSet<>();
        monsterSet.add(Resistance.DARK);
        monsterSet.add(Resistance.FIRE);
        monster.setResistances(monsterSet);
        return monster;
    }

    public static HeroDTO getHeroDTODummy1() {
        HeroDTO hero = new HeroDTO();
        hero.setId(1L);
        hero.setName("Witcher");
        hero.setUser(getUserDTODummy1());
        hero.setImage("test");
        Set<Skill> skilsSet = new HashSet<>();
        skilsSet.add(Skill.ARMOR);
        skilsSet.add(Skill.MAGIC);
        hero.setSkills(skilsSet);
        return hero;
    }

    public static HeroDTO getHeroDTODummy2() {
        HeroDTO hero = new HeroDTO();
        hero.setId(2L);
        hero.setName("Vesemir");
        hero.setUser(getUserDTODummy2());
        hero.setImage("testimage");
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(Skill.RANGED);
        skillSet.add(Skill.SNEAK);
        skillSet.add(Skill.MELEE);
        hero.setSkills(skillSet);
        return hero;
    }

    public static RequestDTO getRequestDTODummy1() {
        RequestDTO request = new RequestDTO();
        request.setId(1L);
        request.setCustomer(getUserDTODummy1());
        request.setLocation("Novigrad");
        request.setAward(new BigDecimal(10000));
        request.setMonsters(new HashSet<>());
        request.getMonsters().add(getMonsterDTODummy1());
        return request;
    }

    public static RequestDTO getRequestDTODummy2() {
        RequestDTO request = new RequestDTO();
        request.setId(2L);
        request.setCustomer(getUserDTODummy2());
        request.setLocation("Velen");
        request.setAward(new BigDecimal(100));
        request.setMonsters(new HashSet<>());
        request.getMonsters().add(getMonsterDTODummy2());
        request.getMonsters().add(getMonsterDTODummy3());
        return request;
    }

    public static JobDTO getJobDTODummy1() {
        JobDTO job = new JobDTO();
        job.setId(1L);
        job.setHeroes(new HashSet<>());
        job.getHeroes().add(getHeroDTODummy2());
        job.setEvaluation(5);
        job.setRequest(getRequestDTODummy1());
        job.setSeverity(Severity.MODERATE);
        job.setStatus(JobStatus.DONE);
        return job;
    }

    public static JobDTO getJobDTODummy2() {
        JobDTO job = new JobDTO();
        job.setId(2L);
        job.setHeroes(new HashSet<>());
        job.getHeroes().add(getHeroDTODummy1());
        job.setEvaluation(10);
        job.setRequest(getRequestDTODummy2());
        job.setSeverity(Severity.MINOR);
        job.setStatus(JobStatus.IN_PROGRESS);
        return job;
    }

    public static JobCreateDTO getJobCreateDTODummy() {
        JobCreateDTO job = new JobCreateDTO();
        job.setHeroes(new HashSet<>());
        job.getHeroes().add(getHeroDTODummy1());
        job.setEvaluation(3);
        job.setRequest(getRequestDTODummy1());
        job.setSeverity(Severity.CRITICAL);
        job.setStatus(JobStatus.ASSIGNED);
        return job;
    }

    public static String convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }
}
