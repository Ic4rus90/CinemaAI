package com.booleanuk.api.cinema.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String status;
    private Object data;
}
