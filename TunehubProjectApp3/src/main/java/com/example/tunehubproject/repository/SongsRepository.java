package com.example.tunehubproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tunehubproject.entities.Songs;

public interface SongsRepository extends JpaRepository<Songs, Integer> {
public Songs findByName(String name);
}
