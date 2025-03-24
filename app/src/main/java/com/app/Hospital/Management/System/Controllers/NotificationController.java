package com.app.Hospital.Management.System.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.app.Hospital.Management.System.Services.NotificationService;
import com.app.Hospital.Management.System.entities.Notification;
import com.app.Hospital.Management.System.exceptions.BadRequestException;
import com.app.Hospital.Management.System.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;
    
    
    
    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestParam Long appointmentID) {
        try {
            service.createNotificationsForAppointment(appointmentID);
            return new ResponseEntity<>("Notification created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create notification: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = service.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notification>> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = service.getNotificationById(id);
        if (notification.isPresent()) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotificationById(@PathVariable Long id) {
        try {
           service.deleteNotificationById(id);
            return new ResponseEntity<>("Notification deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete notification: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @PostMapping("/generate-reminders")
    public ResponseEntity<String> generateReminders() {
        service.generateRemindersForAppointments();
        return new ResponseEntity<>("Reminders generated successfully for tomorrow's appointments.", HttpStatus.OK);
    }

    // Global Exception Handler for better error responses
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}