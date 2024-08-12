package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {

        userRepository.save(user);
        return true;
    }

    public boolean updateUser(Integer id, User user) {
        User updatedUser = userRepository.getById(id);
        if (updatedUser.getName() == null) {
            return false;
        }
        user.setId(id);
        userRepository.save(user);
        return true;
    }

    public boolean removeUser(Integer id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
