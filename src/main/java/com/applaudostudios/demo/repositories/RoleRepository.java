package com.applaudostudios.demo.repositories;

import com.applaudostudios.demo.models.Item;
import com.applaudostudios.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
