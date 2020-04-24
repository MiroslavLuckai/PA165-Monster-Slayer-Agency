package cz.muni.fi.pa165.msa.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * @author Filip Daniel Fedin
 */
public class RequestDTO {

    private Long id;
    private UserDTO customer;
    private String location;
    private Set<MonsterDTO> heroes;
    private BigDecimal award;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<MonsterDTO> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<MonsterDTO> heroes) {
        this.heroes = heroes;
    }

    public BigDecimal getAward() {
        return award;
    }

    public void setAward(BigDecimal award) {
        this.award = award;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestDTO)) return false;
        RequestDTO that = (RequestDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCustomer(), that.getCustomer()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getHeroes(), that.getHeroes()) &&
                Objects.equals(getAward(), that.getAward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getLocation(), getHeroes(), getAward());
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", location='" + location + '\'' +
                ", heroes=" + heroes +
                ", award=" + award +
                '}';
    }
}