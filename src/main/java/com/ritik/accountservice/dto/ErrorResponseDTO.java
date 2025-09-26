package com.ritik.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDTO {
    @Schema(
            description = "API path for which error occurred"
    )
    private String apiPath;

    @Schema(
            description = "HTTP Status code",
            example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message",
            example = "Internal Server Error"
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp when error occurred",
            example = "2024-01-20T10:30:45"
    )
    private LocalDateTime errorTime;
}
