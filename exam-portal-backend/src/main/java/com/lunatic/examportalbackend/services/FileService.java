package com.lunatic.examportalbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String saveFileAvatar(String fileName, MultipartFile multipartFile) throws IOException;

    String saveFileQuestion(String fileName, MultipartFile multipartFile) throws IOException;

    byte[] getImageAvatar(String fileCode);

    byte[] getImageQuestion(String fileCode);

    Resource getFile(String fileCode) throws IOException;

    String updateFileAvatar(String fileName, String fileCode, MultipartFile multipartFile) throws IOException;

    String updateFileQuestion(String fileName, String fileCode, MultipartFile multipartFile) throws IOException;

    Boolean deleteFileCode(String fileCode, String type) throws IOException;
}
