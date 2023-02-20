package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.services.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    private Path foundFile;
    public String saveFile(String fileName, String type, MultipartFile multipartFile) throws IOException {
        Path uploadDirection = Paths.get("file-upload/" + type);
        File imagesRootDir = new File(uploadDirection.toUri());
        if (!imagesRootDir.exists()) {
            imagesRootDir.mkdirs();
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirection.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioe) {
            throw new IOException("Error saving uploaded file: " + fileName, ioe);
        }

        return fileCode;
    }

    @Override
    public String saveFileAvatar(String fileName, MultipartFile multipartFile) throws IOException {
        return saveFile(fileName, "avatar", multipartFile);
    }

    @Override
    public String saveFileQuestion(String fileName, MultipartFile multipartFile) throws IOException {
        return saveFile(fileName, "question", multipartFile);
    }

    private byte[] getImage(String type, String fileCode) {

        byte[] image = new byte[0];
        try {
            Path uploadDirection = Paths.get("file-upload/" + type);

            Files.list(uploadDirection).forEach(file -> {
                if (file.getFileName().toString().split("-")[0].equals(fileCode)) {
                    foundFile = file;
                    return;
                }
            });

            if (foundFile != null) {
                image = FileUtils.readFileToByteArray(new File(foundFile.toUri()));
                foundFile = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public byte[] getImageAvatar(String fileCode) {
        return getImage("avatar", fileCode);
    }

    @Override
    public byte[] getImageQuestion(String fileCode) {
        return getImage("question", fileCode);
    }

    @Override
    public Resource getFile(String fileCode) throws IOException {
        Path uploadDirector = Paths.get("file-upload");

        Files.list(uploadDirector).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            Resource resource = new UrlResource(foundFile.toUri());
            foundFile = null;
            return resource;
        }

        return null;
    }

    @Override
    public String updateFileAvatar(String fileName, String fileCode, MultipartFile multipartFile) throws IOException {
        return updateFile("avatar", fileName, fileCode, multipartFile);
    }

    public String updateFileQuestion(String fileName, String fileCode, MultipartFile multipartFile) throws IOException {
        return updateFile("question", fileName, fileCode, multipartFile);
    }

    @Override
    public Boolean deleteFileCode(String fileCode, String type) throws IOException {
        Path uploadDirection = Paths.get("file-upload/" + type);

        try {
            Files.list(uploadDirection).forEach(file -> {
                if (file.getFileName().toString().split("-")[0].equals(fileCode)) {
                    foundFile = file;
                    return;
                }
            });

            if (foundFile != null) {
                File currentFile = new File(foundFile.toUri());
                currentFile.delete();
                foundFile = null;
                return true;
            }
        } catch (IOException ioe) {
            throw new IOException("Error delete file: " + fileCode, ioe);
        }

        return false;
    }

    public String updateFile(String type, String fileName, String fileCode, MultipartFile multipartFile) throws IOException {
        Path uploadDirection = Paths.get("file-upload/" + type);

        String newFileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {

            Files.list(uploadDirection).forEach(file -> {
                if (file.getFileName().toString().split("-")[0].equals(fileCode)) {
                    foundFile = file;
                    return;
                }
            });

            if (foundFile != null) {
                File currentFile = new File(foundFile.toUri());
                if (currentFile.delete()) {
                    Path filePath = uploadDirection.resolve(newFileCode + "-" + fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    foundFile = null;
                    return newFileCode;
                }
            }


        } catch (IOException ioe) {
            throw new IOException("Error update file: " + fileName, ioe);
        }

        return null;
    }
}
