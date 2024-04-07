package com.campbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.campbackend.modal.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
