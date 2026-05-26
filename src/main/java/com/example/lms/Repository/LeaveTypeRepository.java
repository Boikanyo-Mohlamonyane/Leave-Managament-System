package com.example.lms.Repository;

import com.example.lms.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveTypeRepository extends JpaRepository<LeaveType,Long> {
    Optional<LeaveType> findByTypeName(String typeName);
}
