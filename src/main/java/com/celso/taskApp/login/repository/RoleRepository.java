package com.celso.taskApp.login.repository;

import com.celso.taskApp.login.models.ERole;
import com.celso.taskApp.login.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole roleType);

}
