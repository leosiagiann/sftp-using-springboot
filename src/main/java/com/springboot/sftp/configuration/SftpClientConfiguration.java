package com.springboot.sftp.configuration;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * @Author: Leonardo Siagian
 * Created on 22/02/2024
 */
@Component
@Slf4j
public class SftpClientConfiguration {
    @Value("${sftp.client.host}")
    private String host;

    @Value("${sftp.client.port}")
    private Integer port;

    @Value("${sftp.client.user}")
    private String user;

    @Value("${sftp.client.password}")
    private String password;

    @Value("${sftp.client.basePath}")
    private String basePath;

    private Session session;

    private ChannelSftp channelSftp;

    @PostConstruct
    public void open() {
        try {
            log.info("Trying to connect Sftp server...");
            JSch jSch = new JSch();
            session = jSch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            log.info("Success connect to Sftp");
        } catch (JSchException ex) {
            log.error("Failed to connect to Sftp server");
        }
    }

    @PreDestroy
    public void close() {
        if (!Objects.isNull(channelSftp)) {
            channelSftp.disconnect();
        }
        if (!Objects.isNull(session)) {
            session.disconnect();
        }
    }

    public void putFile(String fileName, InputStream inputStream) throws Exception {
        if (!channelSftp.isConnected()) {
            open();
        }
        if(!channelSftp.isConnected()) {
            throw new Exception("cannot connect to sftp");
        }
        channelSftp.put(inputStream, basePath + "/" +fileName);
    }

    public void putFile(String fileName, File file) throws Exception {
        if (!channelSftp.isConnected()) {
            open();
        }
        if(!channelSftp.isConnected()) {
            throw new Exception("cannot connect to sftp");
        }
        FileInputStream fis = new FileInputStream(file);
        channelSftp.put(fis, basePath + "/" +fileName);
    }

    public List<String> getFiles(String path) throws Exception {
        if (!channelSftp.isConnected()) {
            open();
        }
        if(!channelSftp.isConnected()) {
            throw new Exception("cannot connect to sftp");
        }
        List<String> fileNames = new ArrayList<>();
        Vector<ChannelSftp.LsEntry> entries = channelSftp.ls(basePath + "/" + path);
        for (ChannelSftp.LsEntry entry : entries) {
            if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..") && !entry.getAttrs().isDir()) {
                fileNames.add(entry.getFilename());
            }
        }
        return fileNames;
    }

    public List<String> getFolders(String path) throws Exception {
        if (!channelSftp.isConnected()) {
            open();
        }
        if(!channelSftp.isConnected()) {
            throw new Exception("cannot connect to sftp");
        }
        List<String> folderNames = new ArrayList<>();
        Vector<ChannelSftp.LsEntry> entries = channelSftp.ls(basePath + "/" + path);
        for (ChannelSftp.LsEntry entry : entries) {
            if (entry.getAttrs().isDir() && !entry.getFilename().equals(".") && !entry.getFilename().equals("..")) {
                folderNames.add(entry.getFilename());
            }
        }
        return folderNames;
    }

    public void createFolder(String folder) throws Exception {
        if (!channelSftp.isConnected()) {
            open();
        }
        if (!channelSftp.isConnected()) {
            throw new Exception("cannot connect to sftp");
        }
        channelSftp.mkdir(basePath + "/" + folder);
    }
}
