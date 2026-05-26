package com.example.lms.controller;

import com.example.lms.dto.LeaveRequest;
import com.example.lms.dto.LeaveResponse;
import com.example.lms.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave")
@RequiredArgsConstructor
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    @PostMapping("/apply")
    public LeaveResponse applyLeave(@RequestBody LeaveRequest request) {
        return leaveService.applyLeave(request);
    }

    @PutMapping("/approve")
    public String approveLeave(@RequestParam Long approverId,
                               @RequestParam Long leaveId) {
        return leaveService.approveLeave(approverId, leaveId);
    }


    @PutMapping("/reject")
    public String rejectLeave(@RequestParam Long approverId,
                              @RequestParam Long leaveId) {
        return leaveService.rejectLeave(approverId, leaveId);
    }


}
