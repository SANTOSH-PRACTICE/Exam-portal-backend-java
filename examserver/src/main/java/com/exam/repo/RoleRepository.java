package com.exam.repo;

import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public java.util.Set<Role> findByRoleId(long roleId) ;
	
 
}
