package com.campbackend.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.modal.Camp;

public interface CampRepository extends JpaRepository<Camp,UUID> {

    Page<Camp> findAllByEndingDateBefore(LocalDate now, PageRequest of);

    Page<Camp> findAllByEndingDateAfter(LocalDate now, PageRequest of);

}
