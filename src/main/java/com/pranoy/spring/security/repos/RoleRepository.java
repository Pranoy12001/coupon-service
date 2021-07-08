package com.pranoy.spring.security.repos;

import com.pranoy.spring.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
