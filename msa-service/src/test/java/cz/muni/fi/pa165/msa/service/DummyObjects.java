package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.*;
import cz.muni.fi.pa165.monsterslayeragency.enums.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class DummyObjects {

    public static User getUserDummy1() {
        User user = new User();
        user.setEmail("default@mail.com");
        user.setImage("test_image");
        user.setPassword("default");
        user.setUserName("Geralt");
        return user;
    }

    public static User getUserDummy2() {
        User user = new User();
        user.setEmail("normal@mail.com");
        user.setImage("image");
        user.setUserName("Peter");
        user.setPassword("password");
        return user;
    }

    public static Monster getMonsterDummy1() {
        Monster monster = new Monster();
        monster.setName("Beast Monster");
        monster.setSize(10);
        monster.addResistance(Resistance.ICE);
        monster.addResistance(Resistance.ROCK);
        monster.setMonsterType(MonsterType.BEAST);
        return monster;
    }

    public static Monster getMonsterDummy2() {
        Monster monster = new Monster();
        monster.setName("Hybrid Monster");
        monster.setSize(15);
        monster.addResistance(Resistance.PSYCHIC);
        monster.setMonsterType(MonsterType.HYBRID);
        return monster;
    }

    public static Monster getMonsterDummy3() {
        Monster monster = new Monster();
        monster.setName("Ghoul");
        monster.setSize(10);
        monster.setMonsterType(MonsterType.NECROPHAGE);
        monster.addResistance(Resistance.DARK);
        monster.addResistance(Resistance.FIRE);
        return monster;
    }

    public static Hero getHeroDummy1() {
        Hero hero = new Hero();
        hero.setName("Witcher");
        hero.setUser(getUserDummy1());
        hero.setImage("test");
        hero.setSkills(new HashSet<>());
        hero.addSkill(Skill.ARMOR);
        hero.addSkill(Skill.MAGIC);
        return hero;
    }

    public static Hero getHeroDummy2() {
        Hero hero = new Hero();
        hero.setName("Vesemir");
        hero.setUser(getUserDummy2());
        hero.setImage("testimage");
        hero.setSkills(new HashSet<>());
        hero.addSkill(Skill.RANGED);
        hero.addSkill(Skill.SNEAK);
        hero.addSkill(Skill.MELEE);
        return hero;
    }

    public static Request getRequestDummy1() {
        Request request = new Request();
        request.setLocation("Novigrad");
        request.setAward(new BigDecimal(10000));
        request.setMonsters(new ArrayList<>());
        request.getMonsters().add(getMonsterDummy1());
        return request;
    }

    public static Request getRequestDummy2() {
        Request request = new Request();
        request.setLocation("Velen");
        request.setAward(new BigDecimal(100));
        request.setMonsters(new ArrayList<>());
        request.getMonsters().add(getMonsterDummy2());
        request.getMonsters().add(getMonsterDummy3());
        return request;
    }

    public static Job getJobDummy1() {
        Job job = new Job();
        job.setHeroes(new HashSet<>());
        job.getHeroes().add(getHeroDummy2());
        job.setEvaluation(5);
        job.setRequest(getRequestDummy1());
        job.setSeverity(JobSeverity.MODERATE);
        job.setStatus(JobStatus.DONE);
        return job;
    }

    public static Job getJobDummy2() {
        Job job = new Job();
        job.setHeroes(new HashSet<>());
        job.getHeroes().add(getHeroDummy1());
        job.setEvaluation(10);
        job.setRequest(getRequestDummy2());
        job.setSeverity(JobSeverity.MINOR);
        job.setStatus(JobStatus.IN_PROGRESS);
        return job;
    }

}
