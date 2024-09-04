package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.models.Person;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.services.PeopleService;


@Component
public class PeopleValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Person person = (Person) o;
        if (peopleService.getPersonByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "", "A person with this name already exists");
    }
}
