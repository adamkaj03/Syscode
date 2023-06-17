package com.inerview.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String address;
}
