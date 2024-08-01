package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalSecurityAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
