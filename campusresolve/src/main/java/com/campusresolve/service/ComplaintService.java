package com.campusresolve.service;

import java.time.LocalDateTime;
import java.util.List;

import com.campusresolve.dto.ComplaintRequest;
import com.campusresolve.entity.Complaint;
import com.campusresolve.entity.ComplaintStatus;
import com.campusresolve.entity.Role;
import com.campusresolve.entity.User;
import com.campusresolve.repository.ComplaintRepository;
import com.campusresolve.repository.UserRepository;
import com.campusresolve.security.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    // Student - Raise Complaint
    public Complaint raiseComplaint(ComplaintRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setStatus(ComplaintStatus.PENDING);
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setUpdatedAt(null);
        complaint.setUser(user);

        return complaintRepository.save(complaint);
    }

    // Get Complaint By Id
    public Complaint getComplaintById(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User loggedInUser = userDetails.getUser();

        // Admin can access every complaint
        if (loggedInUser.getRole() == Role.ADMIN) {
            return complaint;
        }

        // Student can access only own complaint
        if (complaint.getUser().getId().equals(loggedInUser.getId())) {
            return complaint;
        }

        throw new RuntimeException("Access Denied");
    }

    // Student - My Complaints
    public List<Complaint> getMyComplaints() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepository.findByUserId(user.getId());
    }

    // Admin - All Complaints
    public List<Complaint> getAllComplaints() {

        return complaintRepository.findAll();
    }

    // Admin - Update Status
    public Complaint updateStatus(Long id, ComplaintStatus status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        if (complaint.getStatus() == ComplaintStatus.RESOLVED) {
            throw new RuntimeException("Complaint is already resolved");
        }

        complaint.setStatus(status);
        complaint.setUpdatedAt(LocalDateTime.now());

        return complaintRepository.save(complaint);
    }

    // Dashboard
    public long getTotalComplaints() {

        return complaintRepository.count();
    }
}