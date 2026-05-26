package com.example.lms.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LeaveServiceTest {

    @Autowired
    LeaveService leaveService;

    @Test
    void testCalculateRemainingLeave() {

        int totalLeave = 30;
        int usedLeave = 10;

        int result = leaveService.calculateRemainingLeave(totalLeave, usedLeave);

        assertEquals(20, result);
    }
}