package com.backend.java.backendjava.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionDetails {
        @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
        private LocalDateTime localDateTime;
        private Integer status;
        private String message;
        private String path;
}