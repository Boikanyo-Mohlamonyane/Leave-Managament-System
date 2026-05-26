package com.example.lms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "leave_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveTypeId;

    private String typeName;
    private int maxDays;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveApplication> leaveApplications;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveBalance> leaveBalances;
}