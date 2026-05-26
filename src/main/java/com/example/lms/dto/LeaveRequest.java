package com.example.lms.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    private Long userId;
    private Long leaveTypeId;
}