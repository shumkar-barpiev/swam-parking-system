package com.myexam.parkingsystem.controller;

import tools.jackson.databind.json.JsonMapper;
import com.myexam.parkingsystem.dto.address.AddressRequest;
import com.myexam.parkingsystem.dto.address.AddressResponse;
import com.myexam.parkingsystem.dto.driver.CreateDriverRequest;
import com.myexam.parkingsystem.dto.driver.DriverResponse;
import com.myexam.parkingsystem.service.DriverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JsonMapper jsonMapper;

	@MockitoBean
	private DriverService driverService;

	@Test
	void createDriverTest() throws Exception {
		AddressRequest addressRequest = new AddressRequest(
				"Via Roma 10",
				"Florence",
				"50123",
				"Italy"
		);

		CreateDriverRequest request = new CreateDriverRequest(
				"John Smith",
				"john.smith@example.com",
				"+39 333 123 4567",
				"DL-123456",
				addressRequest
		);

		AddressResponse addressResponse = new AddressResponse(
				1L,
				"Via Roma 10",
				"Florence",
				"50123",
				"Italy"
		);

		DriverResponse response = new DriverResponse(
				1L,
				"John Smith",
				"john.smith@example.com",
				"+39 333 123 4567",
				"DL-123456",
				addressResponse,
				Set.of()
		);

		when(driverService.createDriver(any(CreateDriverRequest.class)))
				.thenReturn(response);

		mockMvc.perform(
						post("/api/drivers")
								.contentType(APPLICATION_JSON)
								.content(jsonMapper.writeValueAsString(request))
				)
				.andExpect(status().isCreated())
				.andExpect(header().string(
						"Location",
						"/api/drivers/1"
				))
				.andExpect(content().contentTypeCompatibleWith(
						APPLICATION_JSON
				))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.fullName").value("John Smith"))
				.andExpect(
						jsonPath("$.email")
								.value("john.smith@example.com")
				)
				.andExpect(
						jsonPath("$.phoneNumber")
								.value("+39 333 123 4567")
				)
				.andExpect(
						jsonPath("$.drivingLicenseNumber")
								.value("DL-123456")
				)
				.andExpect(
						jsonPath("$.address.city")
								.value("Florence")
				)
				.andExpect(jsonPath("$.vehicleIds").isArray());

		verify(driverService)
				.createDriver(any(CreateDriverRequest.class));
	}
}