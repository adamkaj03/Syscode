package com.addressservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String address;
}
