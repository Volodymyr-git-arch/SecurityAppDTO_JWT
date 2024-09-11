package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
    private String username;

    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;


    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
