package cz.muni.fi.pa165.msa.dto;

import java.util.Objects;

public class AuthenticationResponseDTO {
    private UserDTO user;

    private boolean success;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationResponseDTO that = (AuthenticationResponseDTO) o;
        return success == that.success &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, success);
    }

    @Override
    public String toString() {
        return "AuthenticationResponseDTO{" +
                "user=" + user +
                ", success=" + success +
                '}';
    }
}

