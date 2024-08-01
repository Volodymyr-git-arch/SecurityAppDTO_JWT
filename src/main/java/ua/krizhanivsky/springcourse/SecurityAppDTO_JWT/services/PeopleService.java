package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.models.Person;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.repositories.PeopleRepository;


import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
     @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    @Transactional
    public Optional<Person> getPersonByUsername (String username){
        return peopleRepository.findByUsername(username);
    }
}
