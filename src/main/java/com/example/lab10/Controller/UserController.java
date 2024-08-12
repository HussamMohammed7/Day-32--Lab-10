package com.example.lab10.Controller;


import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        if (userService.getUsers().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("User list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsers()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("User added successfully")
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (userService.updateUser(id, user)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("User updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("User not found")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (userService.removeUser(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("User deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("User not found or empty")
            );
        }
    }



}
