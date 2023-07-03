package com.campbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.campbackend.enums.ChurchType;
import com.campbackend.input.ChurchInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.Church;
import com.campbackend.pagination.ChurchPage;
import com.campbackend.repository.ChurchRepository;

@Service
public class ChurchServices {
    @Autowired
    private ChurchRepository churchRepository;
    public ResponseEntity<String> saveOrUpdateChurch(ChurchInput churchInput) {
        try {
           Church churchData=new Church();
            if(churchInput.getChurchId()!=null)
               churchData=this.findById(churchInput.getChurchId());
            else churchData=null;
           Church church=churchRepository.save(new Church(churchInput.getId(), churchInput.getName(), churchInput.getType(), churchData, churchInput.getLocation()));
            return new ResponseEntity<>(church.getName() + " saved successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
            
        }
    }

    public Church findById(UUID id) {
        return churchRepository.findById(id).orElse(null);
    }
    public List<Church> findAllByChurchType(ChurchType churchType) {
        return churchRepository.findAllByType(churchType);
    }
    public ResponseEntity<String> deleteChurch(UUID id) {
        try {
            Church church = this.findById(id);
            churchRepository.delete(church);
            return new ResponseEntity<>(church.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
 public ChurchPage churchPage(PageInput page){
    org.springframework.data.domain.Page<Church> pagination=churchRepository.findAll(PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
    return new ChurchPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(), pagination.getContent());
 }
}
