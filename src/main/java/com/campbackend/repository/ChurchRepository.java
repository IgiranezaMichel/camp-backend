package com.campbackend.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campbackend.modal.Church;

public interface ChurchRepository extends JpaRepository<Church,UUID> {

}