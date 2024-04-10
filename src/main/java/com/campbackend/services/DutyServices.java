package com.campbackend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.campbackend.input.DutyInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Church;
import com.campbackend.modal.Duty;
import com.campbackend.pagination.DutyPage;
import com.campbackend.repository.DutyRepository;

public class DutyServices {
    @Autowired
    private DutyRepository dutyRepository;
    private AccountHolderServices accountHolderServices;
    private ChurchServices churchServices;

    public ResponseEntity<String> saveOrUpdateDuty(DutyInput dutyInput) {
        try {
            AccountHolder accountHolder = accountHolderServices.findById(dutyInput.getAccountHolderId());
            Church church = churchServices.findById(dutyInput.getChurchId());
            if (church == null)
                throw new Exception("Church not found");
            Duty duty = dutyRepository.save(new Duty(dutyInput.getId(), dutyInput.getName(), dutyInput.getDescription(),
                    accountHolder, church));
            return new ResponseEntity<>(accountHolder.getName() + " has assigned " + duty.getName() + " successful",
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Duty findById(UUID id) {
        return dutyRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<String> deleteDuty(UUID id) {
        try {
            Duty duty = this.findById(id);
            dutyRepository.delete(duty);
            return new ResponseEntity<>(duty.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public DutyPage dutyPage(PageInput page) {
        org.springframework.data.domain.Page<Duty> pagination = dutyRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new DutyPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }
}
