package com.example.menumanager.exception;

import java.time.LocalDateTime;

public record ErrorMessage( String message, LocalDateTime localDateTime) {
}
