package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobposts")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        if (jobPostService.getJobPosts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Job post list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                jobPostService.getJobPosts()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Job post added successfully")
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (jobPostService.updateJobPost(id, jobPost)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Job post updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Job post not found")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        if (jobPostService.removeJobPost(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Job post deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Job post not found or empty")
            );
        }
    }
}