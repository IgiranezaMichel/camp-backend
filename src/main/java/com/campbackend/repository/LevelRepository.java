package com.campbackend.repository;

import java.util.UUID;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.campbackend.modal.Levels;

public interface LevelRepository extends JpaRepository<Levels,UUID>{
    @Query("select u from Levels u where u.fromAge<=:age AND u.toAge>=:age")
    List<Levels> findLevelsBtnAge(int age);

}
