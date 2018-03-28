package ru.barinov.server.model;

public class DefaultResponse {
  private String description;

  public DefaultResponse(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
