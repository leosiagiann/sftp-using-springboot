package com.springboot.sftp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: Leonardo Siagian
 * Created on 23/02/2024
 */
@Data
@Builder
public class SftpDTO {
    @NotNull(message = "300000")
    @NotBlank(message = "300000")
    private String path;

    @NotNull(message = "300000")
    @NotBlank(message = "300000")
    private String name;
}
