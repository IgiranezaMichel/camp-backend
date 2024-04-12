package com.campbackend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.campbackend.enums.Role;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Church;
import com.campbackend.modal.Duty;
public interface DutyRepository extends JpaRepository<Duty,UUID> {

    List<Duty> findAllByChurch(Church church);
    @Query("SELECT d FROM Duty d WHERE d.accountHolder.role = ?1")
    Page<Duty> findAllByAccountHolderRole(Role role, PageRequest of);
    boolean existsByAccountHolder(AccountHolder accountHolder);
    Duty findByAccountHolder(AccountHolder accountHolder);

}