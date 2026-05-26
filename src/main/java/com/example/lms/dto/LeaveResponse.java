package com.example.lms.dto;

import com.example.lms.enums.LeaveStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponse {

    private Long leaveId;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    private LeaveStatus status;

    private LocalDate appliedDate;

    // Employee info (simplified)
    private Long userId;
    private String userName;

    // Leave type info (simplified)
    private Long leaveTypeId;
    private String leaveTypeName;

    // Approver info (can be null if pending)
    private Long approvedById;
    private String approvedByName;
}