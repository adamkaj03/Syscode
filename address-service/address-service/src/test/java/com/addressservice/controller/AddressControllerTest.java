package com.addressservice.controller;

import com.addressservice.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressControllerTest {

    @InjectMocks
    private AddressController underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void name() {
        Address response = underTest.getAddress();
        assertEquals("Budapest", response.getAddress());
    }
}