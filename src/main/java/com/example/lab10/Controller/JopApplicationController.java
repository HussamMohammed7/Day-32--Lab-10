package com.example.lab10.Controller;


import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JopApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class JopApplicationController {

    private final JopApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllApplications() {
        if (jobApplicationService.getApplicationPosts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Job application list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                jobApplicationService.getApplicationPosts()
        );
    }

    @PostMapping("/apply")
    public ResponseEntity addApplication(@Valid @RequestBody JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        int result = jobApplicationService.applyApplication(jobApplication);
        if (result == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("User not found")
            );
        } else if (result == -2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Job post not found")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Job application added successfully")
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteApplication(@PathVariable Integer id) {
        if (jobApplicationService.deleteApplication(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Job application deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Job application not found")
            );
        }
    }
}
