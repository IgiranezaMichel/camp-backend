package com.campbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.campbackend.modal.Book;
import com.campbackend.modal.Levels;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findAllByLevels(Levels levels, PageRequest of);

}
