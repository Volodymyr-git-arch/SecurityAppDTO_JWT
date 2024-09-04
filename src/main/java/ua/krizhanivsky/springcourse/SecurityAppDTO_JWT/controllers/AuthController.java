package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.dto.AuthenticationDTO;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.dto.PersonDTO;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.models.Person;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.security.JWTUtil;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.services.RegistrationService;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.util.PeopleValidator;


import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PeopleValidator peopleValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    public AuthController(RegistrationService registrationService, PeopleValidator peopleValidator, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.peopleValidator = peopleValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) {
        Person person = convertToPerson(personDTO);
        peopleValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return Map.of("message", "Error");

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    public Person convertToPerson(PersonDTO personDTO) {

        return this.modelMapper.map(personDTO, Person.class);
    }
}