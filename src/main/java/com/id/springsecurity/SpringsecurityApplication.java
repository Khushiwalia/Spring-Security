package com.id.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.id.springsecurity.models.User;
import com.id.springsecurity.repos.UserRepository;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {
    
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired   
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 User user= new User();
		 user.setEmail("kavya@gmail.com");
		 user.setUsername("Kavya");
		 user.setPassword(this.bCryptPasswordEncoder.encode("verma"));
		 user.setRole("ROLE_NORMAL");
		 this.userRepository.save(user);
		 
		 User user1= new User();
		 user1.setEmail("Amita@gmail.com");
		 user1.setUsername("Amita");
		 user1.setPassword(this.bCryptPasswordEncoder.encode("rawat"));
		 user1.setRole("ROLE_ADMIN");
		 this.userRepository.save(user1);
		
	}

}
