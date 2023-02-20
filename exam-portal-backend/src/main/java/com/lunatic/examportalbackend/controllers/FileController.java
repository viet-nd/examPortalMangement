package com.lunatic.examportalbackend.controllers;

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
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/avatar")
    public ResponseEntity<?> uploadFileAvatar(
            @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileCode = fileService.saveFileAvatar(fileName, multipartFile);

        return ResponseEntity.ok(fileCode);
    }

    @PostMapping("/question")
    public ResponseEntity<?> uploadFileQuestion(
            @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileCode = fileService.saveFileQuestion(fileName, multipartFile);

//        FileUpload fileUpload = new FileUpload();
//        fileUpload.setFileName(fileName);
//        fileUpload.setDownloadUri(fileCode);
//        fileUpload.setSize(multipartFile.getSize());

        return ResponseEntity.ok(fileCode);
    }

    @GetMapping("/download/{fileCode}")
    public ResponseEntity<?> downloadFile(
            @PathVariable("fileCode") String fileCode) throws IOException {
        Resource resource = null;
        try {
            resource = fileService.getFile(fileCode);
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

    @GetMapping("/avatar/{fileCode}")
    public ResponseEntity<byte[]> getImageAvatar(@PathVariable("fileCode") String fileCode) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileService.getImageAvatar(fileCode));
    }

    @GetMapping("/question/{fileCode}")
    public ResponseEntity<byte[]> getImageQuestion(@PathVariable("fileCode") String fileCode) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileService.getImageQuestion(fileCode));
    }

    @PutMapping("/avatar/{fileCode}")
    public ResponseEntity<?> updateFileAvatar(
            @PathVariable("fileCode") String fileCode,
            @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String newFileCode = fileService.updateFileAvatar(fileName, fileCode, multipartFile);

        return ResponseEntity.ok(newFileCode);
    }

    @PutMapping("/question/{fileCode}")
    public ResponseEntity<?> updateFileQuestion(
            @PathVariable("fileCode") String fileCode,
            @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String newFileCode = fileService.updateFileQuestion(fileName, fileCode, multipartFile);

        return ResponseEntity.ok(newFileCode);
    }

    @DeleteMapping("/{fileCode}")
    public ResponseEntity<?> deleteFileQuestion(
            @RequestParam("type") String type,
            @PathVariable("fileCode") String fileCode) throws IOException {

        return ResponseEntity.ok(fileService.deleteFileCode(fileCode, type));
    }
}
