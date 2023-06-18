package com.addressservice.controller;

import com.addressservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @GetMapping()
    public Address getAddress(){
        Address response = new Address(UUID.randomUUID(), "Budapest");
        //loggolom kérés url-t és a method típusát
        log.info("Request method: GET");
        log.info("Request URL: localhost:8081/address");
        //válasz loggolása
        log.info("Response Status code: OK 200");
        log.info("Response Body: " + response.toString());
        return response;
    }
}
