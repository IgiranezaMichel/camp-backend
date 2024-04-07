package com.campbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.modal.Levels;

public interface LevelRepository extends JpaRepository<Levels,UUID>{

}
