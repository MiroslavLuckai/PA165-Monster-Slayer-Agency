package cz.muni.fi.pa165.msa.sampledata;

import cz.muni.fi.pa165.monsterslayeragency.entities.*;
import cz.muni.fi.pa165.monsterslayeragency.enums.*;
import cz.muni.fi.pa165.msa.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader {

    final static Logger logger = LoggerFactory.getLogger(SampleDataLoaderImpl.class);

    @Autowired
    private HeroService heroService;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private MonsterService monsterService;

    public void populates() throws IOException {
        User geraltUser =  createUser("geralt@gmail.com", "Geralt of Rivia", "geralt.png", false, true);
        User yennefer = createUser("yen@gmail.com", "Yennefer of Vengerberg", "yennefer.jpg", false, true);
        User miro = createUser("miroslav.luckai@gmail.com", "Miro Luckai", "miro.jpg", true, true);
        User miska = createUser("michaela.bajanova@gmail.com", "Miska Bajanova", "miska.jpg", true, true);
        User ludo = createUser("ludovit.kopcsanyi@gmail.com", "Ludo Kopcsanyi", "ludo.jpg", true, true);
        User filip = createUser("filip.fedin@gmail.com", "Filip Fedin", "filip.jpg", true, true);
        User redBaron = createUser("red.baron@gmail.com", "Red Baron", "redBaron.jpg", false, false);
        User kingRadovid = createUser("radovid@gmail.com", "King Radovid V", "radovid.png", false, false);
        logger.info("Users table populated.");

        Monster drowner = createMonster("Drowner", 6, MonsterType.NECROPHAGE, "drowner.jpg", Food.CORPSE, Set.of(Resistance.BLEEDING, Resistance.POISON));
        Monster bruxa = createMonster("Bruxa", 6, MonsterType.VAMPIRE, "bruxa.jpg", Food.BLOOD, Set.of(Resistance.BLEEDING, Resistance.POISON));
        Monster griffin = createMonster("Griffin", 10, MonsterType.HYBRID, "griffin.png", Food.ANIMALS, null);
        Monster wraiths = createMonster("Wraiths", 6, MonsterType.SPECTER, "wraiths.jpg", Food.NONE, Set.of(Resistance.FIRE, Resistance.POISON));
        Monster werewolf = createMonster("Werewolf", 8, MonsterType.CURSED_ONE, "werewolf.png", Food.MEAT, null);
        Monster leshen = createMonster("Leshen", 9, MonsterType.RELICT, "leshen.png", Food.PLANTS, Set.of(Resistance.PSYCHIC));
        logger.info("Monster table populated.");

        Request drownerRequest = createRequest(redBaron, 1000, "Velen", List.of(drowner), Severity.MINOR);
        Request wraithsRequest = createRequest(redBaron, 500, "Skellige", List.of(wraiths), Severity.MODERATE);
        Request werewolfRequest = createRequest(redBaron, 1500, "Novigrad", List.of(werewolf), Severity.CRITICAL);
        Request leshenRequest = createRequest(kingRadovid, 200, "Oxenfurt", List.of(leshen), Severity.MODERATE);
        logger.info("Request table populated.");

        Hero geraltOfRivia = createHero(geraltUser, "Geralt of Rivia", "geralt.png", Set.of(Skill.MELEE, Skill.ACROBATICS, Skill.BLADES, Skill.MAGIC));
        Hero yenneferOfVengerberg = createHero(yennefer, "Yennegfer of Vengerberg", "yennefer.jpg", Set.of(Skill.MAGIC));
        Hero miskaTheGitMaster = createHero(miska, "Miska the Git Master", "miska.jpg", Set.of(Skill.ACROBATICS, Skill.MAGIC));
        Hero ludoTheLaptopMaster = createHero(ludo, "Ludo the Laptop Master", "ludo.jpg", Set.of(Skill.MELEE, Skill.ARMOR));
        Hero filipTheMasterOfIdeas = createHero(filip, "Filip the master of Ideas", "filip.jpg", Set.of(Skill.BLADES, Skill.ACROBATICS));
        Hero miroTheMergeMaster = createHero(miro, "Miro the merge master", "miro.jpg", Set.of(Skill.RANGED, Skill.MAGIC));
        logger.info("Hero table populated.");

        Job drownerJob = createJob(drownerRequest, Set.of(geraltOfRivia), 3, JobStatus.IN_PROGRESS, Severity.MINOR);
        Job werewolfJob = createJob(werewolfRequest, Set.of(miskaTheGitMaster, ludoTheLaptopMaster, filipTheMasterOfIdeas, miroTheMergeMaster), 1, JobStatus.ASSIGNED, Severity.CRITICAL);
        logger.info("Job table populated.");
    }

    private Monster createMonster(String name, int size, MonsterType type, String image, Food food, Set<Resistance> resistances) {
        Monster monster = new Monster();
        monster.setName(name);
        monster.setSize(size);
        monster.setImage(image);
        monster.setFood(food);
        monster.setMonsterType(type);
        monster.setResistances(resistances);

        monsterService.create(monster);

        return monster;
    }

    private Hero createHero(User user, String name, String image, Set<Skill> skills) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setImage(image);
        hero.setSkills(skills);
        hero.setUser(user);

        heroService.createHero(hero);

        return hero;
    }

    private User createUser(String email, String name, String image, boolean admin, boolean hero) {
        User user = new User();
        user.setEmail(email);
        user.setImage(image);
        user.setUserName(name);
        user.setAdmin(admin);
        user.setHero(hero);

        userService.registerUser(user, "Password1");

        return user;
    }

    private Request createRequest(User user, int award, String location, List<Monster> monsters, Severity severity) {
        Request request = new Request();
        request.setAward(new BigDecimal(award));
        request.setCustomer(user);
        request.setLocation(location);
        request.setMonsters(monsters);
        request.setSeverity(severity);

        requestService.create(request);

        return request;
    }

    private Job createJob(Request request, Set<Hero> heroes, int evaluation, JobStatus status, Severity severity) {
        Job job = new Job();
        job.setHeroes(heroes);
        job.setEvaluation(evaluation);
        job.setRequest(request);
        job.setSeverity(severity);
        job.setStatus(status);

        jobService.createJob(job);

        return job;
    }
}
