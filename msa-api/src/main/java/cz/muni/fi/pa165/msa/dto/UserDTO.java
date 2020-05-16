package cz.muni.fi.pa165.msa.dto;

import java.util.Objects;

public class UserDTO {

    private Long id;

    private String email;

    private String userName;

    private String image;

    private boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return isAdmin == userDTO.isAdmin &&
                Objects.equals(id, userDTO.id) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(userName, userDTO.userName) &&
                Objects.equals(image, userDTO.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, userName, image, isAdmin);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", image='" + image + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
