package com.campbackend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.modal.Church;
import com.campbackend.modal.Duty;
public interface DutyRepository extends JpaRepository<Duty,UUID> {

    List<Duty> findAllByChurch(Church church);

}