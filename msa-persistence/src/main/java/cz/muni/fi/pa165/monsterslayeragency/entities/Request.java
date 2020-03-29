package cz.muni.fi.pa165.monsterslayeragency.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Michaela Bajanova (469166)
 */
@Entity
public class Request {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User customer;

    private String location;

    // https://stackoverflow.com/questions/21059451/how-to-add-list-as-property-of-entity-in-datastore-of-google-app-engine-basic
    @OneToMany
    private List<Monster> monsters;

    private BigDecimal award;

    public Request() {}

    public Request(User customer, String location, List<Monster> monsters, BigDecimal award) {
        this.customer = customer;
        this.location = location;
        this.monsters = monsters;
        this.award = award;
    }

    public Long getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public String getLocation() {
        return location;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public BigDecimal getAward() {
        return award;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setAward(BigDecimal award) {
        this.award = award;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getId().equals(request.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
