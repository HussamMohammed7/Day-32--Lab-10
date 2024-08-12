package com.example.lab10.Repository;

import com.example.lab10.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
}
