package de.jro.demo.web.defaults;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({ "timestamp", "status", "error", "exception", "message", "path" })
public class ErrorMessageDTO {

  private String timestamp;
  private int status;
  private String error;
  private String exception;
  private String message;
  private String path;

  public ErrorMessageDTO() {
  }

  public ErrorMessageDTO(String timestamp, HttpStatus statusCode, String exception, String message,
      String path, String errorCode) {
    this.timestamp = timestamp;
    this.status = statusCode.value();
    this.error = errorCode;
    this.exception = exception;
    this.message = message;
    this.path = path;
  }

  @JsonProperty("timestamp")
  public String getTimestamp() {
    return timestamp;
  }

  @JsonProperty("timestamp")
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty("status")
  public int getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(int status) {
    this.status = status;
  }

  @JsonProperty("error")
  public String getError() {
    return error;
  }

  @JsonProperty("error")
  public void setError(String error) {
    this.error = error;
  }

  @JsonProperty("exception")
  public String getException() {
    return exception;
  }

  @JsonProperty("exception")
  public void setException(String exception) {
    this.exception = exception;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  @JsonProperty("message")
  public void setMessage(String message) {
    this.message = message;
  }

  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  @JsonProperty("path")
  public void setPath(String path) {
    this.path = path;
  }
}

