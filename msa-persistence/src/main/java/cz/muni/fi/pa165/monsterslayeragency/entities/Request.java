package cz.muni.fi.pa165.monsterslayeragency.entities;

import java.math.BigDecimal;

/**
 * @author Michaela Bajanova (469166)
 */
public class Request {

    private Long id;
    //private Customer customer;
    private String location;
    //private Map<EMonsterType, int> monsters;
    private BigDecimal award;

    public Request() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getAward() {
        return award;
    }

    public void setAward(BigDecimal award) {
        this.award = award;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Request))
            return false;
        Request other = (Request) obj;
        return other.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();
        return result;
    }
}
