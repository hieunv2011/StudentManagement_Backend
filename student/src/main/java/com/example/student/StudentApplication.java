//package com.example.student;
//
//import com.example.student.entities.Role;
//import com.example.student.entities.User;
//import com.example.student.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@SpringBootApplication
//public class StudentApplication implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(StudentApplication.class, args);
//	}
//
//	public void run (String... args){
//		User adminAccount =userRepository.findByRole(Role.ADMIN);
//		if(null == adminAccount){
//			 User user =new User();
//			 user.setEmail("admin@gmail.com");
//			 user.setFirstname("admin");
//			 user.setSecondname("admin");
//			 user.setRole(Role.ADMIN);
//			 user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//			 userRepository.save(user);
//		}
//
//	}
//}
package com.example.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
