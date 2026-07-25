package com.myexam.dto.address;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.myexam.entity.Address}
 */
@Value
public class AddressResponse implements Serializable {
	Long id;
	String street;
	String city;
	String postalCode;
	String country;
}