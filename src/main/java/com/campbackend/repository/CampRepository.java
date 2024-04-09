package com.campbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.modal.Camp;

public interface CampRepository extends JpaRepository<Camp,UUID> {

}
