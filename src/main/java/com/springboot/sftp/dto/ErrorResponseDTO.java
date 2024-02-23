package com.springboot.sftp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Leonardo Siagian
 * Created on 22/02/2024
 */
@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String code;
    @JsonProperty("msg")
    private String message;
}
