package com.crud.diver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String localization;
    private String email;
    private String login;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (!Objects.equals(id, userDto.id)) return false;
        if (!Objects.equals(name, userDto.name)) return false;
        if (!Objects.equals(surname, userDto.surname)) return false;
        if (!Objects.equals(dateOfBirth, userDto.dateOfBirth)) return false;
        if (!Objects.equals(localization, userDto.localization))
            return false;
        if (!Objects.equals(email, userDto.email)) return false;
        if (!Objects.equals(login, userDto.login)) return false;
        return Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (localization != null ? localization.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
