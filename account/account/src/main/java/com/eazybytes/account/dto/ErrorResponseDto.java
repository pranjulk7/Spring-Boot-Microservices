package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Error Response"
)
@Data@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "endpoint",
            example = "/create"
    )
    private String apiPath;

    @Schema(
            description = "Error code",
            example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error Message",
            example = "Internal Server Error"
    )
    private String errorMessage;

    @Schema(
            description = "Error Time",
            example = "12:30:32"
    )
    private LocalDateTime errorTime;

}
