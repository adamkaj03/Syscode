package com.addressservice;

import com.addressservice.controller.AddressController;
import com.addressservice.model.Address;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AddressServiceApplicationTests {

	@InjectMocks
	private AddressController addressController;
	@Test
	void getAddressTest() {
		Address response = addressController.getAddress();
		assertEquals("Budapest", response.getAddress());
	}

}
