package com.myexam.parkingsystem.dto.address;

import com.myexam.parkingsystem.entity.Address;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Address}
 */
@Value
public class AddressResponse implements Serializable {
	Long id;
	String street;
	String city;
	String postalCode;
	String country;
}