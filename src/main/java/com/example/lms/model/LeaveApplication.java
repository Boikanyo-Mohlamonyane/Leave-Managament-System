package com.example.lms.model;

import com.example.lms.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private LocalDate appliedDate;

    // FK → User (submitter)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // FK → LeaveType
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    // FK → User (approver)
    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;
}