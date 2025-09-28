package com.prismcart.auth.exception;

import com.prismcart.commons.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * This class catches exceptions thrown from any controller and converts them
 * into a standardized ApiResponse format.
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthExceptionHandler.class);

    /**
     * Handles custom authentication-related exceptions.
     * @param ex The caught AuthException.
     * @return A ResponseEntity with a 400 Bad Request status and a standardized error response.
     */
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthException(AuthException ex) {
        LOG.error("Authentication error: {}", ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions related to incorrect login credentials.
     * @param ex The caught BadCredentialsException.
     * @return A ResponseEntity with a 401 Unauthorized status and a standardized error response.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleBadCredentialsException(BadCredentialsException ex) {
        LOG.warn("Bad credentials attempt: {}", ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error("Invalid username or password."),
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * A catch-all handler for any other unhandled exceptions.
     * It's crucial to log these exceptions for debugging purposes.
     * @param ex The caught Exception.
     * @return A ResponseEntity with a 500 Internal Server Error status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        LOG.error("An unexpected error occurred: ", ex);
        return new ResponseEntity<>(ApiResponse.error("An unexpected internal error occurred."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
