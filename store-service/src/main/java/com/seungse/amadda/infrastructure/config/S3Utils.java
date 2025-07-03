package com.seungse.amadda.infrastructure.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnBean(S3Config.class)
public class S3Utils {

    private final S3Client s3Client;

    public String uploadFile(String directory, String id, File file) {
        if (file == null || !file.exists() || file.length() == 0) {
            throw new IllegalArgumentException("file not found");
        }
        String key = directory + "/" + id + "/" + file.getName();
        log.info("upload key: " + key);

        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(S3Constants.BUCKET_NAME)
            .key(key)
            .build();

        PutObjectResponse response = s3Client.putObject(request, file.toPath());

        if (response.sdkHttpResponse().isSuccessful()) {
            return S3Constants.getFileUrl(key);
        }
        throw new RuntimeException(response.sdkHttpResponse().statusText().toString());
    }

    public void deleteFile(String bucketName, String key) throws RuntimeException {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

        DeleteObjectResponse response = s3Client.deleteObject(deleteRequest);

        if (!response.sdkHttpResponse().isSuccessful()) {
            throw new RuntimeException(response.sdkHttpResponse().statusText().toString());
        }
    }

}
