package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.User;

public interface  UserRepository extends JpaRepository<User, Long> {

}
