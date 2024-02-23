package com.springboot.sftp.service;

import com.springboot.sftp.configuration.SftpClientConfiguration;
import com.springboot.sftp.dto.SftpDTO;
import com.springboot.sftp.exception.SftpCustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @Author: Leonardo Siagian
 * Created on 22/02/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SftpService {
    private final SftpClientConfiguration sftpClientConfiguration;
    public List<String> getFolders(String path) {
        try {
            return sftpClientConfiguration.getFolders(path);
        } catch (Exception e) {
            log.error("SftpService::getFolderName, failed to get folder names, error {}, details: ", e.getMessage(), e);
            throw new SftpCustomException(e.getMessage());
        }
    }
    public List<String> getFiles(String path) {
        try {
            return sftpClientConfiguration.getFiles(path);
        } catch (Exception e) {
            log.error("SftpService::getFolderName, failed to get folder names, error {}, details: ", e.getMessage(), e);
            throw new SftpCustomException(e.getMessage());
        }
    }

    public void createFolder(SftpDTO sftpDTO) {
        try {
            String folder = sftpDTO.getPath().concat("/").concat(sftpDTO.getName());
            sftpClientConfiguration.createFolder(folder);
        } catch (Exception e) {
            log.error("SftpService::createNewFolder, failed to create new folder, error {}, details: ", e.getMessage(), e);
            throw new SftpCustomException(e.getMessage());
        }
    }


    public void saveFile(SftpDTO sftpDTO) {
        try {
            File file = new File("D:\\BINUS\\Semester 1\\Akuntansi\\Session 2\\pesamaan akuntansi.jpg");
            String newFile = sftpDTO.getPath().concat("/").concat(sftpDTO.getName());
            sftpClientConfiguration.putFile(newFile, file);
        } catch (Exception e) {
            log.error("SftpService::putFile, failed to save file, error {}, details: ", e.getMessage(), e);
            throw new SftpCustomException(e.getMessage());
        }
    }
}
