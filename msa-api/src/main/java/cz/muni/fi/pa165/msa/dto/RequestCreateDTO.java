package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * @author Filip Daniel Fedin
 */
public class RequestCreateDTO {

    private UserDTO customer;
    private String location;
    private Set<MonsterDTO> monsters;
    private BigDecimal award;
    private Severity severity;

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

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestCreateDTO)) return false;
        RequestCreateDTO that = (RequestCreateDTO) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(location, that.location) &&
                Objects.equals(monsters, that.monsters) &&
                Objects.equals(award, that.award) &&
                severity == that.severity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, location, monsters, award, severity);
    }

    @Override
    public String toString() {
        return "RequestCreateDTO{" +
                "customer=" + customer +
                ", location='" + location + '\'' +
                ", monsters=" + monsters +
                ", award=" + award +
                ", severity=" + severity +
                '}';
    }
}
