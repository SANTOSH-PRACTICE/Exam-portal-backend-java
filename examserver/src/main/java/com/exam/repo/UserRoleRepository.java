package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
	public UserRole findByUserRoleId(long userRoleId) ;

}
