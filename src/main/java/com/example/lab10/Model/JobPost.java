package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 5, message = "Title must be more than 4 characters long")
    @Column(columnDefinition = "varchar(255) not null")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String location;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be a non-negative number")
    @Column (columnDefinition = "double not null ")

    private Double salary;

    @Column(columnDefinition = "date")
    private LocalDate postingDate;
}
