package com.campbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campbackend.modal.Exam;
@Repository
public interface ExamRepository extends JpaRepository<Exam,UUID>{

}
