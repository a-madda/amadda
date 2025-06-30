package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreImage {

    private Long id;
    private String bucketName;
    private String originalFileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private Integer fileOrder;

}
