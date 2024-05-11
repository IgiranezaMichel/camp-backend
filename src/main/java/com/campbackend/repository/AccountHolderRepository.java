package com.campbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.modal.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder,UUID>{
    AccountHolder findByEmail(String id);
    AccountHolder findByEmailAndPassword(String email,String password);
}
