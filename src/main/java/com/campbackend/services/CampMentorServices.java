package com.campbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.input.CampMentorInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Camp;
import com.campbackend.modal.CampMentor;
import com.campbackend.repository.CampMentorRepository;
import java.util.*;

@Service
public class CampMentorServices {
    @Autowired
    private CampMentorRepository campMentorRepository;
    @Autowired
    private AccountHolderServices accountHolderServices;
    @Autowired
    private CampServices campServices;

    public ResponseEntity<String> saveOrUpdateCampMentor(CampMentorInput camp) {
        try {
            AccountHolder accountHolder = accountHolderServices.findByEmail(camp.getAccountHolderEmail());
            Camp camp2 = campServices.findById(camp.getCampId());
            if (accountHolder == null)
                throw new Exception("Account not found");
                // finding account holder already exist?
            CampMentor result= campMentorRepository.findByAccountHolderAndCamp(accountHolder,camp2);
            if(result!=null){
                result.setRole(camp.getRole());
                result.setDescription(camp.getDescription());
                campMentorRepository.save(result);
                return new ResponseEntity<>("Role saved  successful",HttpStatus.OK);
            }
            CampMentor campMentor = new CampMentor();
            campMentor.setAccountHolder(accountHolder);
            campMentor.setCamp(camp2);
            campMentor.setDescription(camp.getDescription());
            campMentor.setRole(camp.getRole());
            campMentor.setId(camp.getId());
            campMentorRepository.save(campMentor);
            return new ResponseEntity<>("Saved successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public CampMentor findById(UUID id) {
        return campMentorRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<String> deleteCampMentor(UUID id) {
        try {
            CampMentor data = this.findById(id);
            campMentorRepository.delete(data);
            return new ResponseEntity<>(data.getAccountHolder().getName() + " removed successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" CampMentor doesn't exist", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public List<CampMentor> getCampMentorList(UUID campId) {
        Camp camp = campServices.findById(campId);
        return campMentorRepository.findAllByCamp(camp);
    }
}
