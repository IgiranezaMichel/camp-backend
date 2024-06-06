package com.campbackend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Camp;
import com.campbackend.modal.CampMentor;

public interface CampMentorRepository extends JpaRepository<CampMentor,UUID>{
    List<CampMentor> findAllByCamp(Camp camp);
    CampMentor findByAccountHolderAndCamp(AccountHolder accountHolder, Camp camp2);
}
