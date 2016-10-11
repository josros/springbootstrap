package de.jro.demo.web.defaults;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorMessageDTO> illegalArgument(Exception ex, HttpServletRequest req) {
    log.error("Error: ", ex);

    return handleException(ex, req, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessageDTO> generalError(Exception ex, HttpServletRequest req) {
    log.error("General error: ", ex);

    return handleException(ex, req, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  private ResponseEntity<ErrorMessageDTO> handleException(Exception ex, HttpServletRequest req,
      HttpStatus status) {
    ErrorMessageDTO errorMessage = generateErrorMessage(ex, req, status, "");
    return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
  }
  
  private ErrorMessageDTO generateErrorMessage(
      Exception ex,
      HttpServletRequest req,
      HttpStatus status,
      String errorCode) {
    String uri = req.getRequestURI();
    if (req.getQueryString() != null) {
      uri += '?' + req.getQueryString();
    }

    return new ErrorMessageDTO(
        generateTimestamp(),
        status,
        ex.getClass().getName(),
        ex.getLocalizedMessage(),
        uri,
        errorCode);
  }

  private static String generateTimestamp() {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    return dateFormatter.format(new Date());
  }

}
