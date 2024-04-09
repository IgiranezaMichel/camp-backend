package com.campbackend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.input.PageInput;
import com.campbackend.modal.Camp;
import com.campbackend.pagination.CampPage;
import com.campbackend.repository.CampRepository;

@Service
public class CampServices {
    @Autowired private CampRepository campRepository;
    public ResponseEntity<String>saveOrUpdateCamp(Camp camp){
        try {
            Camp data=campRepository.save(camp);
            return new ResponseEntity<>(data.getTitle()+" saved successful",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Camp already exist",HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    public Camp findById(UUID id){
      return campRepository.findById(id).orElseThrow();
    }
    public ResponseEntity<String>deleteCamp(UUID id){
        try {
            Camp data=this.findById(id);
            campRepository.delete(data);
            return new ResponseEntity<>(data.getTitle()+" removed successful",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Camp doesn't exist",HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    public CampPage campPage(PageInput page){
        org.springframework.data.domain.Page<Camp> pagination=campRepository.findAll(PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
        return new CampPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(), pagination.getContent());
     }
}
