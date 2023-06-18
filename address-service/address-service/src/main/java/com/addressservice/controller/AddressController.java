package com.addressservice.controller;

import com.addressservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(AddressController.class);
    @GetMapping()
    public Address getAddress(){
        Address response = new Address(UUID.randomUUID(), "Budapest");
        //loggolom kérés url-t és a method típusát
        logger.info("Request method: GET");
        logger.info("Request URL: localhost:8081/address");
        //válasz loggolása
        logger.info("Response Status code: OK 200");
        logger.info("Response Body: " + response.toString());
        return response;
    }
}
