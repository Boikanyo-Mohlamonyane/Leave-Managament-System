package com.example.lms.service;

import com.example.lms.Repository.LeaveApplicationRepository;
import com.example.lms.Repository.LeaveTypeRepository;
import com.example.lms.Repository.UserRepository;
import com.example.lms.dto.LeaveRequest;
import com.example.lms.dto.LeaveResponse;
import com.example.lms.enums.LeaveStatus;
import com.example.lms.model.LeaveApplication;
import com.example.lms.model.LeaveType;
import com.example.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaveService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;



    public LeaveResponse applyLeave(LeaveRequest request) {

        if (request.getUserId() == null) {
            throw new RuntimeException("UserId cannot be null");
        }

        if (request.getLeaveTypeId() == null) {
            throw new RuntimeException("LeaveTypeId cannot be null");
        }

        if (request.getStartDate() == null || request.getEndDate() == null) {
            throw new RuntimeException("StartDate and EndDate are required");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LeaveType leaveType = leaveTypeRepository.findById(request.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("Leave type not found"));
        LeaveApplication leave = LeaveApplication.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .reason(request.getReason())
                .status(LeaveStatus.PENDING)
                .appliedDate(LocalDate.now())
                .user(user)
                .leaveType(leaveType)
                .build();

        LeaveApplication saved = leaveApplicationRepository.save(leave);

        return mapToResponse(saved);
    }
    public String approveLeave(Long approverId, Long leaveId) {

        if (approverId == null || leaveId == null) {
            throw new RuntimeException("approverId and leaveId cannot be null");
        }

        User user = userRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        LeaveApplication leave = leaveApplicationRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Only pending leaves can be approved");
        }

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setApprovedBy(user);
        leaveApplicationRepository.save(leave);

        return "Leave with ID " + leave.getLeaveId() + " has been APPROVED";
    }
    public String rejectLeave(Long approverId, Long leaveId) {

        if (approverId == null || leaveId == null) {
            throw new RuntimeException("approverId and leaveId cannot be null");
        }

        User user = userRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        LeaveApplication leave = leaveApplicationRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Only pending leaves can be rejected");
        }

        leave.setStatus(LeaveStatus.REJECTED);
        leave.setApprovedBy(user);
        leaveApplicationRepository.save(leave);

        return "Leave with ID " + leave.getLeaveId() + " has been REJECTED";
    }

    private LeaveResponse mapToResponse(LeaveApplication leave) {

        return LeaveResponse.builder()
                .leaveId(leave.getLeaveId())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .appliedDate(leave.getAppliedDate())

                .userId(leave.getUser().getUserId())
                .userName(leave.getUser().getFullName())

                .leaveTypeId(leave.getLeaveType().getLeaveTypeId())
                .leaveTypeName(leave.getLeaveType().getTypeName())

                .build();
    }

    public int calculateRemainingLeave(int total, int used) {

        if (used > total) {
            throw new IllegalArgumentException("Used leave cannot exceed total leave");
        }

        return total - used;
    }
}