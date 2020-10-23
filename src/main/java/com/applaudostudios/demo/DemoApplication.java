package com.applaudostudios.demo;

import com.applaudostudios.demo.enums.RoleEnum;
import com.applaudostudios.demo.models.Role;
import com.applaudostudios.demo.models.User;
import com.applaudostudios.demo.repositories.RoleRepository;
import com.applaudostudios.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = { "com.applaudostudios.demo.models" })
@EnableJpaAuditing
public class DemoApplication {

    @Value("${spring.jpa.hibernate.populate:false}")
    boolean populate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository) {
        if(populate){
            List<User> defaultUserList = new ArrayList<>();
            List<Role> defaultAuthoritiesList = new ArrayList<>();
            final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User adminUser = User.builder()
                    .email("diegoromero@gmail.com").username("diegoromero")
                    .firstName("Diego").lastName("Romero").password(encoder.encode("admin")).build();

            User nonAdminUser = User.builder()
                    .email("diegoromero1@gmail.com").username("diegoromero1")
                    .firstName("Diego").lastName("Romero").password(encoder.encode("nonadmin")).build();


            Role admin = Role.builder().roleName(RoleEnum.ADMIN.getCode()).user(adminUser).build();
            Role nonAdmin = Role.builder().roleName(RoleEnum.USER.getCode()).user(nonAdminUser).build();

            defaultUserList.add(adminUser);
            defaultUserList.add(nonAdminUser);

            defaultAuthoritiesList.add(admin);
            defaultAuthoritiesList.add(nonAdmin);

            return args -> {
                userRepository.saveAll(defaultUserList);
                roleRepository.saveAll(defaultAuthoritiesList);
            };
        }
        return null;
    }



}
