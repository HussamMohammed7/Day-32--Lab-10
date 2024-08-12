package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Check(constraints = "role IN ('JOB_SEEKER', 'EMPLOYER')")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be more than 4 characters long")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only alphabetic characters")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be a valid format")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Age cannot be null")
    @Min(value = 22, message = "Age must be more than 21")
    @Column (columnDefinition = "int not null ")
    private int age;

    @NotEmpty(message = "Role cannot be null")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "Role must be either 'JOB_SEEKER' or 'EMPLOYER'")
    //    @Column(columnDefinition = "varchar(5) not null check(role = 'EMPLOYER' OR role = 'JOB_SEEKER')")
    @Column(columnDefinition = "varchar(15) not null ")
    private String role;
}
