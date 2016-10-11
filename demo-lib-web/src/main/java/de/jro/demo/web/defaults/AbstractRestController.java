package de.jro.demo.web.defaults;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractRestController {

  protected String createGenericLogString(String methodName, String... logValues) {
    if (logValues.length % 2 != 0) {
      throw new RuntimeException("log values length must be a muliple of two");
    }
    final StringBuilder sb = new StringBuilder();
    sb.append(methodName);
    sb.append(" ");
    sb.append("with ");
    for (int i = 0; i < logValues.length; i += 2) {
      sb.append(logValues[i]);
      sb.append(": ");
      sb.append(logValues[i + 1]);
      sb.append(" ");
    }
    sb.append(" detected");
    return sb.toString();
  }
  
  protected ResponseEntity<Void> respondOkVoid() {
    return new ResponseEntity<Void>(HttpStatus.OK);
  }
  
  protected <T> ResponseEntity<T> respond(T body, HttpStatus statusCode) {
    return new ResponseEntity<T>(body, statusCode);
  }

  protected <T> ResponseEntity<T> respondOk(T body) {
    return new ResponseEntity<T>(body, HttpStatus.OK);
  }
}
