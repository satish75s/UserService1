package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entiry.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
