package com.campbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.CampApplicant;

public interface CampApplicantRepository extends JpaRepository<CampApplicant,UUID>{

    Page<CampApplicant> findAllByAccountHolder(AccountHolder accountHolder, PageRequest of);

}
