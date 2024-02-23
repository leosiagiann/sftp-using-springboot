package com.springboot.sftp.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Leonardo Siagian
 * Created on 22/02/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class SftpCustomException extends RuntimeException {
    private String code;
    private Object additionalValue;
    @JsonProperty("msg")
    private String message;

    public SftpCustomException(String code) {
        super(code);
        this.code = code;
    }

    public SftpCustomException(String code, Object additionalValue) {
        this.code = code;
        this.additionalValue = additionalValue;
    }
}
