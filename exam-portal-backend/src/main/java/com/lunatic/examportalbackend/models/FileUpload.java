package com.lunatic.examportalbackend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUpload extends BaseModule {
    private String fileName;
    private String downloadUri;
    private long size;
}
