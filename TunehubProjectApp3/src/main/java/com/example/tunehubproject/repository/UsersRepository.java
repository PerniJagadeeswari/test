package com.example.tunehubproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tunehubproject.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
public Users findByEmail(String email);
}
