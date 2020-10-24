package com.applaudostudios.demo.config;

import com.applaudostudios.demo.enums.RoleEnum;
import com.applaudostudios.demo.models.Role;
import com.applaudostudios.demo.models.User;
import com.applaudostudios.demo.repositories.RoleRepository;
import com.applaudostudios.demo.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDatabase implements ApplicationRunner {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.populate:false}")
    private boolean populate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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

            userRepository.saveAll(defaultUserList);
            roleRepository.saveAll(defaultAuthoritiesList);
        }
    }
}
