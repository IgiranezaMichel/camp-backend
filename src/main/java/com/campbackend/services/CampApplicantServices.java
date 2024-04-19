package com.campbackend.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.enums.CampApplicantStatus;
import com.campbackend.input.CampApplicantInput;
import com.campbackend.input.CampApplicantPage;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Camp;
import com.campbackend.modal.CampApplicant;
import com.campbackend.modal.Levels;
import com.campbackend.repository.CampApplicantRepository;
import jakarta.persistence.EntityManager;

@Service
public class CampApplicantServices {
    @Autowired
    private CampApplicantRepository applicantRepository;
    @Autowired
    private CampServices campServices;
    @Autowired
    private AccountHolderServices accountHolderServices;
    @Autowired
    private LevelServices levelServices;

    public ResponseEntity<String> saveOrUpdateCampApplicant(CampApplicantInput campApplicant) {
        try {
            Camp camp = campServices.findById(campApplicant.getCampId());
            AccountHolder accountHolder = accountHolderServices.findById(campApplicant.getAccountHolderId());
            CampApplicant applicationExist = this.findByCampAndAccountHolder(camp, accountHolder);
            // updating the payment modes
            if (applicationExist != null) {
                applicationExist.setPaymentCode(campApplicant.getPaymentCode());
                applicationExist.setTelephone(campApplicant.getTelephone());
                applicationExist.setComment(campApplicant.getComment());
                applicantRepository.save(applicationExist);
                return new ResponseEntity<>(" Updated successful", HttpStatus.OK);
            }
            UUID levelId = levelServices
                    .levelBetweenUserDob(Math.abs(Period.between(LocalDate.now(), accountHolder.getDob()).getYears()));
            Levels levels = levelServices.findById(levelId);
            CampApplicant campApplicant2 = applicantRepository.save(new CampApplicant(campApplicant.getId(), camp,
                    accountHolder, LocalDate.now(), campApplicant.getCampApplicantStatus(), levels,
                    campApplicant.getTelephone(), campApplicant.getPaymentCode(), campApplicant.getComment()));
            return new ResponseEntity<>(campApplicant2.getAccountHolder().getName() + " saved successful",
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public CampApplicant findById(UUID id) {
        return applicantRepository.findById(id).orElseThrow();
    }

    public CampApplicant findByCampAndAccountHolder(Camp camp, AccountHolder accountHolder) {
        return applicantRepository.findByAccountHolderAndCamp(accountHolder, camp);
    }
    @Autowired EntityManager entityManager;
    public ResponseEntity<String> updateCampApplicantStatus(UUID campApplicantId,CampApplicantStatus campApplicantStatus,String comment){
        try {
            CampApplicant campApplicant=this.findById(campApplicantId);
            if(campApplicant!=null)
            {
            // campApplicant.setCampApplicantStatus(campApplicantStatus);
            campApplicant.setComment(comment);
            applicantRepository.save(campApplicant);
        }
        return new ResponseEntity<>("Saved",HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity<>("Application not found",HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    public ResponseEntity<String> deleteCampApplicant(UUID id) {
        try {
            CampApplicant campApplicant = this.findById(id);
            applicantRepository.delete(campApplicant);
            return new ResponseEntity<>(campApplicant.getAccountHolder().getName() + " deleted successful",
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    // admin
    public CampApplicantPage campApplicantPage(UUID campId, PageInput page) {
        Camp camp = campServices.findById(campId);
        org.springframework.data.domain.Page<CampApplicant> pagination = applicantRepository.findAllByCamp(camp,
                PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CampApplicantPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }

    // user page
    public CampApplicantPage accountHolderCampApplicationPage(PageInput page, String email) {
        AccountHolder accountHolder = accountHolderServices.findByEmail(email);
        org.springframework.data.domain.Page<CampApplicant> pagination = applicantRepository.findAllByAccountHolder(
                accountHolder, PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CampApplicantPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }
}
