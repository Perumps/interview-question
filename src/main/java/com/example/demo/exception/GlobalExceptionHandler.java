package com.example.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;

import static com.example.demo.util.Constants.*;

/**
 * Global exception handler that provides meaningful errors messages to the invoker
 * Returns appropriate HTTP status code and message
 * Logs error details to the console
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles missing input parameters
     *
     * @param ex - Short URL parameter from the input
     * @param headers - HTTP headers
     * @param status - HTTP status
     * @param request - Input request
     * @return ResponseEntity with error details
     *
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {
        String error = "Parameter '" + ex.getParameterName() + "' is missing in the request";
        LOGGER.error(error);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions for invalid data, system and application errors
     *
     * @param ex - Short URL parameter from the input
     * @param request - HTTP request
     * @param response - HTTP response
     * @return ResponseEntity with error details
     *
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof MissingServletRequestParameterException) {
            LOGGER.error(MISSING_URL_MESSAGE + URL_MESSAGE_PLACEHOLDER + request.getParameter(URL));
            return new ResponseEntity<>(MISSING_URL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        if (ex instanceof InvalidUrlException) {
            LOGGER.error(ex.getMessage() + URL_MESSAGE_PLACEHOLDER + request.getParameter(URL));
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if (ex instanceof HashCollisionException) {
            LOGGER.error(HASH_COLLISION_ERROR + URL_MESSAGE_PLACEHOLDER + request.getParameter(URL));
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (ex instanceof UrlNotFoundException) {
            LOGGER.error(URL_NOT_FOUND_ERROR + URL_MESSAGE_PLACEHOLDER + request.getParameter(URL));
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        LOGGER.error(INTERNAL_SERVER_ERROR + URL_MESSAGE_PLACEHOLDER + request.getParameter(URL));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
