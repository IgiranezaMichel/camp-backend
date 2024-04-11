package com.campbackend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import com.campbackend.enums.Role;
import com.campbackend.input.AccountHolderInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Church;
import com.campbackend.modal.Duty;
import com.campbackend.pagination.AccountHolderPage;
import com.campbackend.repository.AccountHolderRepository;
import com.campbackend.repository.DutyRepository;

@Service
public class AccountHolderServices {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired private DutyRepository dutyRepository;
    @Autowired private ChurchServices churchServices;
    public ResponseEntity<String> saveOrUpdateAccountHolder(AccountHolderInput accountHolderInput,UUID churchId) {
        try {
            Random random=new Random();
            String generatedPassword = random.ints(48, 113)
              .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
              .limit(20)
              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
              .toString();
            accountHolderInput.setRole(Role.CHRISTIAN);
            accountHolderInput.setPassword(generatedPassword);
            AccountHolder accountHolder2 = accountHolderRepository.save(new AccountHolder(accountHolderInput.getId(), accountHolderInput.getName(), accountHolderInput.getGender(), accountHolderInput.getPhoneNumber(), accountHolderInput.getEmail(), accountHolderInput.getBase64Profile(), accountHolderInput.getPassword(), accountHolderInput.getRole(), accountHolderInput.getDob()));
             // add duty
            //  find church
            Church church=churchServices.findById(churchId);
             dutyRepository.save(new Duty(null, "christian", "christian", accountHolder2, church));
            return new ResponseEntity<>(accountHolder2.getName() + " saved successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something Wrong happen", HttpStatus.METHOD_NOT_ALLOWED);
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
