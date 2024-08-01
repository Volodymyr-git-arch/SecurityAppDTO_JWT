package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.models.Person;


import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}