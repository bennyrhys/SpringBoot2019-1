package com.bennyrhys.data.repository;

import com.bennyrhys.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
