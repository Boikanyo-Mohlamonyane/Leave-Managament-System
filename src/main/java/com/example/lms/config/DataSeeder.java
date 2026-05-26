package com.example.lms.config;

import com.example.lms.Repository.LeaveTypeRepository;
import com.example.lms.Repository.UserRepository;
import com.example.lms.enums.Role;
import com.example.lms.model.LeaveType;
import com.example.lms.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmail("Hr@lms.com").isEmpty()){
            User hrAdmin =User.builder()
                    .fullName("Boikanyo Mohlamonyane")
                    .email("Hr@lms.com")
                    .role(Role.HR_ADMIN)
                    .password(passwordEncoder.encode("admin123"))
                    .build();
            userRepository.save(hrAdmin);
            userRepository.save(hrAdmin);

            System.out.println("Default HR Admin created: hr@lms.com / admin123");
        } else {
            System.out.println(" HR Admin already exists");
        }
        if(userRepository.findByEmail("Lethabo@lms.com").isEmpty()){
            User hrAdmin =User.builder()
                    .fullName("Lethabo Mohlamonyane")
                    .email("Lethabo@lms.com")
                    .role(Role.EMPLOYEE)
                    .password(passwordEncoder.encode("employee123"))
                    .build();
            userRepository.save(hrAdmin);
            userRepository.save(hrAdmin);

            System.out.println("Default Employee created: Lethabo@lms.com /employee123");
        } else {
            System.out.println(" Employee  already exists");
        }

        if(userRepository.findByEmail("Vincent@lms.com").isEmpty()){
            User hrAdmin =User.builder()
                    .fullName("Vincent Mohlamonyane")
                    .email("Vincent@lms.com")
                    .role(Role.MANAGER)
                    .password(passwordEncoder.encode("manager123"))
                    .build();
            userRepository.save(hrAdmin);
            userRepository.save(hrAdmin);

            System.out.println("Default Manager created: vincent@lms.com / manager123");
        } else {
            System.out.println(" Manager already exists");
        }

        // =========================
        // 2. SEED LEAVE TYPES
        // =========================
        createLeaveTypeIfNotExists("ANNUAL LEAVE", 21);
        createLeaveTypeIfNotExists("SICK LEAVE", 10);
        createLeaveTypeIfNotExists("STUDY LEAVE", 15);
        createLeaveTypeIfNotExists("FAMILY RESPONSIBILITY LEAVE", 5);

        System.out.println("✅ Leave Types seeded successfully");


    }

    private void createLeaveTypeIfNotExists(String name, int maxDays) {

        if (leaveTypeRepository.findByTypeName(name).isEmpty()) {

            LeaveType leaveType = LeaveType.builder()
                    .typeName(name)
                    .maxDays(maxDays)
                    .build();

            leaveTypeRepository.save(leaveType);
        }
    }


}
