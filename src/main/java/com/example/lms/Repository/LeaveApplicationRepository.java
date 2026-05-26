package com.example.lms.Repository;

import com.example.lms.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {
}
