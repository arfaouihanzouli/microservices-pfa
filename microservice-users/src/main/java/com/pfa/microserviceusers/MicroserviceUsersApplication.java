package com.pfa.microserviceusers;

import com.pfa.microserviceusers.models.*;
import com.pfa.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class MicroserviceUsersApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUsersApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... args) throws Exception {
		userService.saveRole(new Role( "USER"));
		userService.saveRole(new Role( "ADMIN"));
		userService.saveRole(new Role( "MANAGER"));
		userService.saveUser(new User("marwen","1234",null,"marwenhanzouli@gmail.com"));
		userService.saveUser(new User("admin","1234",null,"adminadmin@gmail.com"));
		userService.saveUser(new User("nourd","1234",null,"noureddine0arfaoui@gmail.com"));
		userService.saveUser(new User("yooo","1234",null,"yoyoyoyoyyo@gmail.com"));

		userService.addRoleToUser("marwen","USER");
		userService.addRoleToUser("admin","ADMIN");
		userService.addRoleToUser("nourd","MANAGER");
		userService.addRoleToUser("yooo","MANAGER");
	}
}
