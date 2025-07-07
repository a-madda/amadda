package com.seungse.amadda.adapter.in.web.mapper;

import com.seungse.amadda.adapter.in.web.request.CreateStoreAdditionalRequest;
import com.seungse.amadda.application.port.in.CreateStoreAdditionalCommand;
import com.seungse.amadda.infrastructure.config.S3Constants;
import com.seungse.amadda.infrastructure.config.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreRequestMapper {

    private final S3Utils s3Utils;

    public CreateStoreAdditionalCommand mapToCommand(String storeBasicKey, CreateStoreAdditionalRequest request) {
        return CreateStoreAdditionalCommand.builder()
            .storeImages(toStoreImageCommands(request.getStoreImages(), storeBasicKey))
            .amenities(request.getAmenities())
            .storeHoliday(toHolidayCommand(request.getStoreHoliday()))
            .operatingHours(toOperatingHourCommands(request.getOperatingHours()))
            .build();
    }

    private CreateStoreAdditionalCommand.StoreHolidayCommand toHolidayCommand(CreateStoreAdditionalRequest.StoreHolidayRequest request) {
        if (request == null)
            return null;

        return CreateStoreAdditionalCommand.StoreHolidayCommand.builder()
            .isHoliday(request.isHoliday())
            .holidayType(request.getHolidayType())
            .holidayDays(request.getHolidayDays())
            .build();
    }

    private List<CreateStoreAdditionalCommand.OperatingHourCommand> toOperatingHourCommands(List<CreateStoreAdditionalRequest.OperatingHourRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            return Collections.emptyList();
        }

        return requests.stream()
            .map(r -> CreateStoreAdditionalCommand.OperatingHourCommand.builder()
                .type(r.getType())
                .dayOfWeek(r.getDayOfWeek())
                .openTime(r.getOpenTime())
                .closeTime(r.getCloseTime())
                .breakStartTime(r.getBreakStartTime())
                .breakEndTime(r.getBreakEndTime())
                .build())
            .toList();
    }

    public List<CreateStoreAdditionalCommand.StoreImageCommand> toStoreImageCommands(List<MultipartFile> files,
                                                                                     String storeBasicKey) {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }

        return files.stream().map(file -> {
            try {
                String fileUrl = s3Utils.uploadFile(S3Constants.STORE_DIRECTORY, storeBasicKey, file);
                return CreateStoreAdditionalCommand.StoreImageCommand.builder()
                    .bucketName(S3Constants.BUCKET_NAME)
                    .originalFileName(file.getOriginalFilename())
                    .filePath(fileUrl)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .fileOrder(files.indexOf(file) + 1)
                    .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

}
