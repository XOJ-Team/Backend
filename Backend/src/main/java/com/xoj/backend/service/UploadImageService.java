package com.xoj.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
    String uploadPicture(MultipartFile smfile);
}
