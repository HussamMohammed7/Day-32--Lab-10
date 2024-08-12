package com.example.lab10.Repository;

import com.example.lab10.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
