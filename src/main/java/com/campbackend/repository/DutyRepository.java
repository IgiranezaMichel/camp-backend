package com.campbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campbackend.modal.Duty;
public interface DutyRepository extends JpaRepository<Duty,UUID> {

}