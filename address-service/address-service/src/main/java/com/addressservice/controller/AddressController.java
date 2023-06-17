package com.addressservice.controller;

import com.addressservice.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @GetMapping()
    public Address getAddress(){
        return new Address(UUID.randomUUID(), "Budapest");
    }
}
