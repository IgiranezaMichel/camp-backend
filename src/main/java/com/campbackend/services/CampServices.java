package com.campbackend.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.input.CampInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.Camp;
import com.campbackend.pagination.CampPage;
import com.campbackend.repository.CampRepository;
import java.time.*;

@Service
public class CampServices {
    @Autowired
    private CampRepository campRepository;
    public ResponseEntity<String> saveOrUpdateCamp(CampInput camp) {
     
        try {
            camp.setTimeStamp(LocalDateTime.now());
            if (camp.getStartingDate().isBefore(LocalDate.now())) {
                throw new Exception("Please Specify the date before today");
            } else if (camp.getStartingDate().isAfter(camp.getEndingDate())) {
                throw new Exception("Please Specify the date before " + camp.getStartingDate());
            } else if (camp.getDeadline().isBefore(LocalDate.now())) {
                throw new Exception("Please Specify the date before " + LocalDate.now());
            } else if (camp.getCost() == 0) {
                throw new Exception("Please Specify Cost");
            }
            Camp data = campRepository
                    .save(new Camp(camp.getId(), camp.getTitle(), camp.getCost(), camp.getAddress(), camp.getDeadline(),
                            camp.getStartingDate(), camp.getEndingDate(), camp.getDescription(), camp.getContent()));
            return new ResponseEntity<>(data.getTitle() + " saved successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Camp findById(UUID id) {
        return campRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<String> deleteCamp(UUID id) {
        try {
            Camp data = this.findById(id);
            campRepository.delete(data);
            return new ResponseEntity<>(data.getTitle() + " removed successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Camp doesn't exist", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public CampPage campPage(PageInput page) {
        org.springframework.data.domain.Page<Camp> pagination = campRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CampPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }

    public CampPage inActiveCamp(PageInput page) {
        org.springframework.data.domain.Page<Camp> pagination = campRepository.findAllByEndingDateBefore(
                LocalDate.now(), PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CampPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }

    public CampPage activeCamp(PageInput page) {
        org.springframework.data.domain.Page<Camp> pagination = campRepository.findAllByDeadlineAfter(LocalDate.now(),
                PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CampPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }
}
