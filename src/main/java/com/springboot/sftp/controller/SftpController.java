package com.springboot.sftp.controller;

import com.springboot.sftp.dto.SftpDTO;
import com.springboot.sftp.service.SftpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Leonardo Siagian
 * Created on 22/02/2024
 */
@RestController
@RequestMapping("sftp")
@RequiredArgsConstructor
public class SftpController {
    private final SftpService sftpService;
    @GetMapping("/folders")
    public ResponseEntity<List<String>> getFolders(@RequestParam("path") String path) {
        List<String> folders = sftpService.getFolders(path);
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> getFiles(@RequestParam("path") String path) {
        List<String> folders = sftpService.getFiles(path);
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @PostMapping("/folders")
    public ResponseEntity<?> createFolder(@RequestBody @Validated SftpDTO sftpDTO) {
        sftpService.createFolder(sftpDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/files")
    public ResponseEntity<?> saveFile(@RequestBody @Validated SftpDTO sftpDTO) {
        sftpService.saveFile(sftpDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
