package com.lunatic.examportalbackend.controllers;

import com.lunatic.examportalbackend.models.FileUpload;
import com.lunatic.examportalbackend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    private FileService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileCode = fileUploadService.saveFile(fileName, multipartFile);

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setDownloadUri("file-upload/" + fileCode);
        fileUpload.setSize(multipartFile.getSize());


        return ResponseEntity.ok(fileUpload);
    }

    @GetMapping("/download/{fileCode}")
    public ResponseEntity<?> downloadFile(
            @PathVariable("fileCode") String fileCode) throws IOException {
        Resource resource = null;
        try {
            resource = fileUploadService.getFile(fileCode);
        } catch (IOException ioe) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
