package com.moviebooking.user;

import com.moviebooking.user.model.Role;
import com.moviebooking.user.model.User;
import com.moviebooking.user.model.UserRole;
import com.moviebooking.user.repo.UserRepository;
import com.moviebooking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication. run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		if(userRepository.findByUserName("admin")==null) {
			User user = new User();
			user.setUserName("admin");
			user.setPassword(this.bCryptPasswordEncoder.encode("admin@123"));
			user.setEmail("admin@gmail.com");
			user.setSecurityQuestion("Pet");
			user.setAnswer("Parrot");

			Role role1 = new Role();
			role1.setRoleId(44);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);

			userRoleSet.add(userRole);
			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUserName());
		}
		System.out.println("starting code");
	}
}
