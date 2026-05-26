package com.example.lms.model;

import com.example.lms.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 1 User → many LeaveApplications (submit)
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<LeaveApplication> leaveApplications;

    // 1 User → many LeaveApplications (approve)
    @JsonIgnore
    @OneToMany(mappedBy = "approvedBy")
    private List<LeaveApplication> approvedLeaves;

    // 1 User → many Notifications
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    // 1 User → many LeaveBalances
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<LeaveBalance> leaveBalances;
}