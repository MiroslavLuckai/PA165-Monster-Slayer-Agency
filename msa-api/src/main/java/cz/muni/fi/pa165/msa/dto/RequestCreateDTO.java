package cz.muni.fi.pa165.msa.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class RequestCreateDTO {

    private UserDTO customer;
    private String location;
    private Set<MonsterDTO> monsters;
    private BigDecimal award;

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

    public Set<MonsterDTO> getMonsters() {
        return monsters;
    }

    public void setMonsters(Set<MonsterDTO> monsters) {
        this.monsters = monsters;
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
        if (!(o instanceof RequestCreateDTO)) return false;
        RequestCreateDTO that = (RequestCreateDTO) o;
        return Objects.equals(getCustomer(), that.getCustomer()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getMonsters(), that.getMonsters()) &&
                Objects.equals(getAward(), that.getAward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getLocation(), getMonsters(), getAward());
    }

    @Override
    public String toString() {
        return "RequestCreateDTO{" +
                "customer=" + customer +
                ", location='" + location + '\'' +
                ", monsters=" + monsters +
                ", award=" + award +
                '}';
    }
}
