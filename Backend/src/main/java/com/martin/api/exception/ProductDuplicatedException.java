package com.martin.api.exception;

public class ProductDuplicatedException extends RuntimeException {

  public ProductDuplicatedException(String message) {
    super(message);
  }
}
