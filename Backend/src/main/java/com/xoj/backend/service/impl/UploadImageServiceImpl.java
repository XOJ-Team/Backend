package com.xoj.backend.service.impl;

import com.xoj.backend.exception.BizException;
import com.xoj.backend.service.UploadImageService;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Service
public class UploadImageServiceImpl implements UploadImageService {
    @Value("${smms.upload-url}")
    private String uploadUrl;

    @Value("${smms.delete-url}")
    private String deleteUrl;

    @Value("${smms.authorization}")
    private String authorization;

    @Override
    public String uploadPicture(MultipartFile smfile) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            File file = new File(Objects.requireNonNull(smfile.getOriginalFilename()));
            FileUtils.copyInputStreamToFile(smfile.getInputStream(), file);
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("smfile", smfile.getName(),
                            RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            Request request = new Request.Builder()
                    .url(uploadUrl)
                    .method("POST", body)
                    .addHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                    .addHeader("Authorization", authorization)
                    .build();
            Response response = client.newCall(request).execute();
            if (file.exists()) {
                file.delete();
            }
            return response.body().string();
        } catch (Exception e) {
            throw new BizException();
        }
    }
}
