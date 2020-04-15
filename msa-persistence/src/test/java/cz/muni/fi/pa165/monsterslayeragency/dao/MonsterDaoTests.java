package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michaela Bajanova (469166)
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MonsterDaoTests extends AbstractTestNGSpringContextTests{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MonsterDao monsterDao;
    private Monster monster, monster2;

    @BeforeMethod
    public void setUp() {
        monster = new Monster();
        monster.setName("Beast Monster");
        monster.setSize(10);
        monster.addResistance(Resistance.ICE);
        monster.addResistance(Resistance.ROCK);
        monster.setMonsterType(MonsterType.BEAST);

        monster2 = new Monster();
        monster2.setName("Hybrid Monster");
        monster2.setSize(15);
        monster2.addResistance(Resistance.PSYCHIC);
        monster2.setMonsterType(MonsterType.HYBRID);
    }

    @Test
    public void testAddMonster() {
        monsterDao.addMonster(monster);

        List<Monster> resultList = em.createQuery("select m from Monster m", Monster.class).getResultList();
        assertThat(resultList).containsExactlyInAnyOrder(monster);
    }

    @Test
    public void testUpdateMonster() {
        em.persist(monster);

        monster.setName("Beast Monster - Updated");
        monster.addResistance(Resistance.ICE);
        monster.addResistance(Resistance.ROCK);
        monsterDao.updateMonster(monster);

        Monster actual = em.find(Monster.class, monster.getId());
        assertThat(actual).isEqualTo(monster);
    }

    @Test
    public void testRemoveMonster() {
        em.persist(monster);
        Monster foundMonster = em.find(Monster.class, monster.getId());
        assertThat(foundMonster).isNotNull();

        monsterDao.removeMonster(monster);
        foundMonster = em.find(Monster.class, monster.getId());
        assertThat(foundMonster).isNull();
    }

    @Test
    public void testFindById() {
        em.persist(monster);
        Monster foundMonster = monsterDao.findById(monster.getId());
        assertThat(foundMonster).isNotNull().isEqualTo(monster);
    }

    @Test
    public void testFindNonexistentMonsterById() {
        Monster foundMonster = monsterDao.findById(12345L);
        assertThat(foundMonster).isNull();
    }

    @Test
    public void testFindByName() {
        em.persist(monster);
        Monster foundMonster = monsterDao.findByName(monster.getName());
        assertThat(foundMonster).isNotNull().isEqualTo(monster);
    }

    @Test
    public void testFindNonexistentMonsterByName() {
        Monster foundMonster = monsterDao.findByName("FAKE_NAME");
        assertThat(foundMonster).isNull();
    }

    @Test
    public void testFindByMonsterType() {
        em.persist(monster);
        em.persist(monster2);
        List<Monster> foundMonsters = monsterDao.findByMonsterType(monster.getMonsterType());
        assertThat(foundMonsters.size()).isEqualTo(1);
        assertThat(foundMonsters.get(0)).isEqualTo(monster);
    }

    @Test
    public void testFindNonexistentMonsterByType() {
        List<Monster> foundMonsters = monsterDao.findByMonsterType(MonsterType.BEAST);
        assertThat(foundMonsters.size()).isEqualTo(0);
    }

    @Test
    public void testFindBySize() {
        em.persist(monster);
        em.persist(monster2);
        List<Monster> foundMonsters = monsterDao.findBySize(monster2.getSize());
        assertThat(foundMonsters.size()).isEqualTo(1);
        assertThat(foundMonsters.get(0)).isEqualTo(monster2);
    }

    @Test
    public void testFindNonexistentMonsterBySize() {
        List<Monster> foundMonsters = monsterDao.findBySize(8);
        assertThat(foundMonsters.size()).isEqualTo(0);
    }

    @Test
    public void testAddNullMonster() {
        Assertions.assertThatThrownBy(() -> monsterDao.addMonster(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUpdateNullMonster() {
        Assertions.assertThatThrownBy(() -> monsterDao.updateMonster(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testRemoveNullMonster() {
        Assertions.assertThatThrownBy(() -> monsterDao.removeMonster(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByNullId() {
        Assertions.assertThatThrownBy(() -> monsterDao.findById(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByNullName() {
        Assertions.assertThatThrownBy(() -> monsterDao.findByName(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByNullMonsterType() {
        Assertions.assertThatThrownBy(() -> monsterDao.findByMonsterType(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByInvalidSize() {
        Assertions.assertThatThrownBy(() -> monsterDao.findBySize(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
