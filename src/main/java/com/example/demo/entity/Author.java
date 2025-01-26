package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Author")
@Table(
        name = "authors",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "author_email_unique",
                        columnNames = "email"
                )
        }
)
public class Author {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "age",
            nullable = false,
            columnDefinition = "Integer"
    )
    private Integer age;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "VARCHAR",
            length = 200
    )
    private String email;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "VARCHAR",
            length = 200
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR",
            length = 200
    )
    private String lastName;

    @ManyToMany
    private List<Course> courses;


}
