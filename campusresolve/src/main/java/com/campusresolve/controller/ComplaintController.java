package com.campusresolve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.campusresolve.dto.ComplaintRequest;
import com.campusresolve.entity.Complaint;
import com.campusresolve.entity.ComplaintStatus;
import com.campusresolve.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // Student - Raise Complaint
    @PostMapping
    public Complaint raiseComplaint(@RequestBody ComplaintRequest request) {

        return complaintService.raiseComplaint(request);
    }

    // Get Complaint By Id
    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {

        return complaintService.getComplaintById(id);
    }

    // Student - My Complaints
    @GetMapping("/my")
    public List<Complaint> getMyComplaints() {

        return complaintService.getMyComplaints();
    }

    // Admin - All Complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {

        return complaintService.getAllComplaints();
    }

    // Admin - Update Complaint Status
    @PutMapping("/{id}")
    public Complaint updateStatus(@PathVariable Long id,
                                  @RequestParam ComplaintStatus status) {

        return complaintService.updateStatus(id, status);
    }
}