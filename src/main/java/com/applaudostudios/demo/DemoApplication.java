package com.applaudostudios.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EntityScan(basePackages = { "com.applaudostudios.demo.repositories.models" })
@EnableJpaAuditing
public class DemoApplication {

    @Value("${spring.jpa.hibernate.populate:false}")
    boolean populate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

}
