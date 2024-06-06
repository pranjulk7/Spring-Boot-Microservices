package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Response Information"
)
public class ResponseDto {

    @Schema(
            description = "Http Code Received" , example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Response Information Received" , example = "Created Successfully"
    )
    private String statusMsg;


}
