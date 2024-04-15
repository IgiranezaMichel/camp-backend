package com.campbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.input.CampApplicantInput;
import com.campbackend.input.CampApplicantPage;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Camp;
import com.campbackend.modal.CampApplicant;
import com.campbackend.repository.CampApplicantRepository;
import java.time.*;
import java.util.*;
@Service
public class CampApplicantServices {
@Autowired private CampApplicantRepository applicantRepository;
@Autowired private CampServices campServices;
@Autowired private AccountHolderServices accountHolderServices;
public ResponseEntity<String> saveOrUpdateCampApplicant(CampApplicantInput campApplicant) {
    try {
        Camp camp = campServices.findById(campApplicant.getCampId());
        AccountHolder accountHolder = accountHolderServices.findById(campApplicant.getAccountHolderId());
        CampApplicant campApplicant2=applicantRepository.save(new CampApplicant(campApplicant.getId(), camp, accountHolder, LocalDate.now(),campApplicant.getCampApplicantStatus()));
        return new ResponseEntity<>(campApplicant2.getAccountHolder().getName() + " saved successful", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
    }
}

public CampApplicant findById(UUID id) {
    return applicantRepository.findById(id).orElseThrow();
}

public ResponseEntity<String> deleteBook(UUID id) {
    try {
        CampApplicant campApplicant = this.findById(id);
        applicantRepository.delete(campApplicant);
        return new ResponseEntity<>(campApplicant.getAccountHolder().getName() + " deleted successful", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
// admin 
public CampApplicantPage campApplicantPage(PageInput page){
org.springframework.data.domain.Page<CampApplicant> pagination=applicantRepository.findAll(PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
return new CampApplicantPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(), pagination.getContent());
}
// user page
public CampApplicantPage accountHolderCampApplicationPage(PageInput page,String email){
    AccountHolder accountHolder=accountHolderServices.findByEmail(email);
    org.springframework.data.domain.Page<CampApplicant> pagination=applicantRepository.findAllByAccountHolder(accountHolder,PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
    return new CampApplicantPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(), pagination.getContent());
    }
}
