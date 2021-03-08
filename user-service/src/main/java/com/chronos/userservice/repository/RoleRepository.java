package com.chronos.userservice.repository;

import com.chronos.userservice.enums.ERole;
import com.chronos.userservice.model.Role;
import com.chronos.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
