package com.edu.tecsup.libro.microserviciolibros.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
public @Data class ResponseMessage {
    private String message;
    private HttpStatus httpStatus;
}
