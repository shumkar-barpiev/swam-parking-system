package com.myexam.parkingsystem.config.global.exception;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

public record ApiError(
		Instant timestamp,
		int status,
		String error,
		String message,
		Map<String, String> validationErrors
) {
}
