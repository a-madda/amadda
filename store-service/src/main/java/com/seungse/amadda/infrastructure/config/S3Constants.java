package com.seungse.amadda.infrastructure.config;

public class S3Constants {

    public static final String BUCKET_NAME = "amadda-store";
    public static final String STORE_DIRECTORY = "stores";
    public static final String BASE_S3_URL = "https://%s.s3.amazonaws.com/%s";

    private S3Constants() {
    }

    public static String getFileUrl(String key) {
        return String.format(BASE_S3_URL, BUCKET_NAME, key);
    }

}
