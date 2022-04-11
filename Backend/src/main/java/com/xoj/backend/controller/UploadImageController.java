package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.service.UploadImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Api(value = "upload image")
public class UploadImageController {

    @Autowired
    private UploadImageService service;

    @PostMapping("")
    @ApiOperation(value = "upload")
    public RestResponse<Object> uploadImage(MultipartFile smfile) {
        String url = service.uploadPicture(smfile);
        return RestResponse.ok(url, CommonErrorType.SUCCESS.getResultMsg());
    }
}
