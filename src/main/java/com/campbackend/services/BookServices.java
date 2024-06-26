package com.campbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import com.campbackend.input.BookInput;
import com.campbackend.input.PageInput;
import com.campbackend.modal.AccountHolder;
import com.campbackend.modal.Book;
import com.campbackend.modal.Levels;
import com.campbackend.pagination.BookPage;
import com.campbackend.repository.BookRepository;
import java.time.*;
import java.time.temporal.ChronoUnit;
@Service
public class BookServices {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LevelServices levelServices;
    @Autowired
    private AccountHolderServices accountHolderServices;
    public ResponseEntity<String> saveOrUpdateBook(BookInput bookInput) {
        try {
            Levels level = levelServices.findById(bookInput.getLevelId());
            Book book = bookRepository.save(new Book(bookInput.getId(),bookInput.getName(), bookInput.getBase64File(),level, bookInput.getBase64Cover(), bookInput.getAuthor(),bookInput.getPublicationDate(), bookInput.getPublisher(),bookInput.getSerialNumber()));
            return new ResponseEntity<>(book.getName() + " saved successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Book findById(UUID id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<String> deleteBook(UUID id) {
        try {
            Book book = this.findById(id);
            bookRepository.delete(book);
            return new ResponseEntity<>(book.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Something happen", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
 public BookPage bookPage(PageInput page){
    org.springframework.data.domain.Page<Book> pagination=bookRepository.findAll(PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
    return new BookPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(), pagination.getContent());
 }

public BookPage accountHolderBookPage(PageInput page,UUID accountHolderId) {
    AccountHolder accountHolder=accountHolderServices.findById(accountHolderId);
    LocalDate date=accountHolder.getDob();
    System.out.println(date);
    Levels levels=new Levels();
    try {
        UUID levelId=levelServices. levelBetweenUserDob(date.until(LocalDate.now(), ChronoUnit.YEARS));
        levels=levelServices.findById(levelId);
    } catch (Exception e) {
       levels=null;
    }

    org.springframework.data.domain.Page<Book> pagination=bookRepository.findAllByLevels(levels,PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
    return new BookPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(), pagination.getContent());
}
}
