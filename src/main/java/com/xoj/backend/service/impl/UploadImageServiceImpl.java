package com.xoj.backend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.service.UploadImageService;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

/**
 * @author Yingxi
 */

@Service
public class UploadImageServiceImpl implements UploadImageService {
    @Value("${smms.upload-url}")
    private String uploadUrl;

    @Value("${smms.delete-url}")
    private String deleteUrl;

    @Value("${smms.authorization}")
    private String authorization;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String uploadPicture(MultipartFile smfile) {
        try {
            logger.info("start upload");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            logger.info("set media type");
            MediaType mediaType = MediaType.parse("text/plain");
            logger.info("new file");
            File file = new File(Objects.requireNonNull(smfile.getOriginalFilename()));
            logger.info("copy");
            try {
                FileUtils.copyInputStreamToFile(smfile.getInputStream(), file);
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new BizException();
            }
            logger.info("body");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("smfile", smfile.getName(),
                            RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            logger.info("start request");
            Request request = new Request.Builder()
                    .url(uploadUrl)
                    .method("POST", body)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                    .addHeader("Authorization", authorization)
                    .build();
            Response response = client.newCall(request).execute();
            if (file.exists()) {
                file.delete();
            }
            logger.info("get response");
            String jsonString = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode url = null;
            logger.info("get url");
            if ("image_repeated".equals(jsonNode.get("code").asText())) {
                url = jsonNode.get("images");
            } else if ("Upload success.".equals(jsonNode.get("message").asText())) {
                url = jsonNode.get("data").get("url");
            }
            logger.info("completed");
            return null != url && StringUtils.hasText(url.asText()) ? url.asText() : "";
        } catch (Exception e) {
            throw new BizException(CommonErrorType.UPLOAD_FAIL);
        }
    }

    public void delete() {

    }
}
