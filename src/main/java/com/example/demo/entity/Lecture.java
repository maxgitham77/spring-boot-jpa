package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Lecture")
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Integer id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "VARCHAR"
    )
    private String name;

}
