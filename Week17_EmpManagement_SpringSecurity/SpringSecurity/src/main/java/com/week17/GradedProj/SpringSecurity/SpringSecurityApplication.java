package com.week17.GradedProj.SpringSecurity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.week17.GradedProj.SpringSecurity.SercurityConfig.UserCredentials;
import com.week17.GradedProj.SpringSecurity.SercurityConfig.UserService;


@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
	
	@Autowired
	UserService s1;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		Set<String> authAdmin = new HashSet<String>();
		authAdmin.add("Admin");
		//To Create an encode object
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserCredentials appAdmin = new UserCredentials(1, "admin", encoder.encode("admin"), authAdmin);
		s1.add(appAdmin);
		
	}

}
