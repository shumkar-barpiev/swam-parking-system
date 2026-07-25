package com.myexam.config.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(
			ResourceNotFoundException exception
	) {
		ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(),
				Map.of()
		);

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ApiError> handleConflict(
			ConflictException exception
	) {
		ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(),
				Map.of()
		);

		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(
			MethodArgumentNotValidException exception
	) {
		Map<String, String> validationErrors =
				new LinkedHashMap<>();

		exception.getBindingResult()
				.getFieldErrors()
				.forEach(fieldError ->
						validationErrors.put(
								fieldError.getField(),
								fieldError.getDefaultMessage()
						)
				);

		ApiError error = new ApiError(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Request validation failed",
				validationErrors
		);

		return ResponseEntity
				.badRequest()
				.body(error);
	}
}
