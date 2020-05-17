package cz.muni.fi.pa165.monsterslayeragency.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Michaela Bajanova (469166)
 */
@Entity(name = "Request")
@Table(name = "requests")
public class Request extends AbstractEntity {

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @Column(name = "location")
    private String location;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id")
    private List<Monster> monsters;

    @Column(name = "award")
    private BigDecimal award;

    public Request() {}

    public Request(User customer, String location, List<Monster> monsters, BigDecimal award) {
        this.customer = customer;
        this.location = location;
        this.monsters = monsters;
        this.award = award;
    }

    public Long getId() {
        return super.id;
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
        super.id = id;
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
        return Objects.equals(getId(), request.getId()) &&
                Objects.equals(getCustomer(), request.getCustomer()) &&
                Objects.equals(getLocation(), request.getLocation()) &&
                Objects.equals(getMonsters(), request.getMonsters()) &&
                Objects.equals(getAward(), request.getAward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getLocation(), getMonsters(), getAward());
    }
}
