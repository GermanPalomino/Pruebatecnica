package com.pruebatecnica.libros.exception;

import java.time.LocalDateTime;

public class ApiError {

    public int status;
    public String message;
    public LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
