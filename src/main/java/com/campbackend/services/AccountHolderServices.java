package com.campbackend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.enums.Role;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.pagination.AccountHolderPage;
import com.campbackend.repository.AccountHolderRepository;

@Service
public class AccountHolderServices {
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public ResponseEntity<String> saveOrUpdateAccountHolder(AccountHolder accountHolder) {
        try {
            accountHolder.setRole(Role.CHRISTIAN);
            AccountHolder accountHolder2 = accountHolderRepository.save(new AccountHolder());
            return new ResponseEntity<>(accountHolder2.getName() + " saved successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public AccountHolder findById(UUID id) {
        return accountHolderRepository.findById(id).orElseThrow();
    }

    public AccountHolder findByEmail(String email) {
        AccountHolder accountHolder = accountHolderRepository.findByEmail(email);
        return accountHolder;
    }

    public ResponseEntity<String> deleteAccountHolder(UUID id) {
        try {
            AccountHolder accountHolder = this.findById(id);
            accountHolderRepository.delete(accountHolder);
            return new ResponseEntity<>(accountHolder.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public AccountHolderPage accountPage(PageInput page) {
        org.springframework.data.domain.Page<AccountHolder> pagination = accountHolderRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new AccountHolderPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }
}
