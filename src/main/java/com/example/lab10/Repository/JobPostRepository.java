package com.example.lab10.Repository;

import com.example.lab10.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {


}
