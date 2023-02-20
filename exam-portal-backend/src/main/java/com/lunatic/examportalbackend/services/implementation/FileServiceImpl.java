package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.services.FileUploadService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadDirection = Paths.get("file-upload");

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirection.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioe) {
            throw new IOException("Error saving uploaded file: " + fileName, ioe);
        }

        return fileCode;
    }
}
