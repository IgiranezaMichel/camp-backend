package com.campbackend.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campbackend.enums.ChurchType;
import com.campbackend.modal.Church;

public interface ChurchRepository extends JpaRepository<Church,UUID> {
    List<Church> findAllByType(ChurchType churchType);

}