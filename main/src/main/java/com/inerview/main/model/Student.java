package com.inerview.main.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "students")
public class Student {

    @Id
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

}
