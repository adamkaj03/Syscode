package com.addressservice.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String address;

}
