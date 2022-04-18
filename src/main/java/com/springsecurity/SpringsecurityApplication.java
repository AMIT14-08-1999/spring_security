package com.springsecurity;

import com.springsecurity.Repo.UserRepository;
import com.springsecurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		User user=new User();
		user.setEmail("john@gmail.com");
		user.setUsername("john");
		user.setPassword(this.bCryptPasswordEncoder.encode("amit@1999"));
		user.setRole("ROLE_NORMAL");
		this.userRepository.save(user);

		User user1=new User();
		user1.setEmail("rohsni@gmail.com");
		user1.setUsername("roshni");
		user1.setPassword(this.bCryptPasswordEncoder.encode("roshni@1999"));
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
	}
}

//1:38:01