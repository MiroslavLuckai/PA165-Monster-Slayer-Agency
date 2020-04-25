package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.Skill;

import java.util.Objects;
import java.util.Set;

public class HeroDTO {

    private Long id;

    private UserDTO user;

    private String name;

    private Set<Skill> skills;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroDTO)) return false;
        HeroDTO heroDTO = (HeroDTO) o;
        return Objects.equals(getId(), heroDTO.getId()) &&
                Objects.equals(getUser(), heroDTO.getUser()) &&
                Objects.equals(getName(), heroDTO.getName()) &&
                Objects.equals(getSkills(), heroDTO.getSkills()) &&
                Objects.equals(getImage(), heroDTO.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getName(), getSkills(), getImage());
    }

    @Override
    public String toString() {
        return "HeroDTO{" +
                "id=" + id +
                "user=" + user +
                ", name='" + name + '\'' +
                ", skills=" + skills +
                ", image='" + image + '\'' +
                '}';
    }
}
