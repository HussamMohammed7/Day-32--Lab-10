package com.example.lab10.Service;


import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class JopApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;


    public List<JobApplication> getApplicationPosts() {
        return applicationRepository.findAll();
    }

    public int applyApplication(JobApplication application) {
        if (!userRepository.findById(application.getUser_id()).isPresent()){
            return -1;
        }
        if (!jobPostRepository.findById(application.getJob_post_id()).isPresent()){
            return -2;
        }
        applicationRepository.save(application);

        return 1;
    }

    public boolean deleteApplication(int applicationId) {

        if(applicationRepository.findById(applicationId).isPresent()){
            applicationRepository.deleteById(applicationId);
            return true;
        }
        return false;

    }



}
