package com.campbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campbackend.modal.AyGrade;
@Repository
public interface AyGradeRepository extends JpaRepository<AyGrade,UUID>{

}
