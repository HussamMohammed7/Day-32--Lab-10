package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getJobPosts() {
        return jobPostRepository.findAll();
    }

    public boolean addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
        return true;
    }

    public boolean updateJobPost(Integer id, JobPost jobPost) {
        if (!jobPostRepository.existsById(id)) {
            return false;
        }
        JobPost updatedJobPost = jobPostRepository.getById(id);

        updatedJobPost.setTitle(jobPost.getTitle());
        updatedJobPost.setDescription(jobPost.getDescription());
        updatedJobPost.setLocation(jobPost.getLocation());
        updatedJobPost.setSalary(jobPost.getSalary());
        updatedJobPost.setPostingDate(jobPost.getPostingDate());

        jobPostRepository.save(updatedJobPost);
        return true;
    }

    public boolean removeJobPost(Integer id) {
        if (!jobPostRepository.existsById(id)) {
            return false;
        }
        jobPostRepository.deleteById(id);
        return true;
    }
}