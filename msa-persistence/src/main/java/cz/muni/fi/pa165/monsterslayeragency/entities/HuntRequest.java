package cz.muni.fi.pa165.monsterslayeragency.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Basic entity class
 * Represents request to kill monster in our application
 * @author Miroslav Luckai
 */
@Entity
public class HuntRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    private String location;

    private int reward;

    @Column(nullable = false)
    @ManyToOne(targetEntity = User.class)
    private User client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof HuntRequest)) {
            return false;
        }
        final HuntRequest huntRequest = (HuntRequest)object;

        if (!Objects.equals(this.client, huntRequest.getClient())) {
            return false;
        }

        return Objects.equals(this.title, huntRequest.getTitle());
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int result = 1;
        return result * prime * this.client.hashCode() + this.title.hashCode();
    }


}
