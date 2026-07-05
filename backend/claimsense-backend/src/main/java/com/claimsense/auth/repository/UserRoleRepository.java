package com.claimsense.auth.repository;

import com.claimsense.auth.entity.UserRole;
import com.claimsense.auth.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}