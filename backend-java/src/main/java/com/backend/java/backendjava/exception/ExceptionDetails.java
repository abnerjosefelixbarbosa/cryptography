package com.backend.java.backendjava.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ExceptionDetails(
        @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
        LocalDateTime localDateTime,
        Integer status,
        String message,
        String path
) { }