package com.lunatic.examportalbackend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String saveFile(String fileName, MultipartFile multipartFile) throws IOException;
}
