package com.myexam.parkingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String street;

	@Column(nullable = false, length = 100)
	private String city;

	@Column(nullable = false, length = 20)
	private String postalCode;

	@Column(nullable = false, length = 100)
	private String country;

	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private Driver driver;
}