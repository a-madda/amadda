package com.seungse.amadda.application.mapper;

import com.seungse.amadda.application.port.in.CreateStoreAdditionalCommand;
import com.seungse.amadda.application.port.in.CreateStoreBasicCommand;
import com.seungse.amadda.domain.OperatingHour;
import com.seungse.amadda.domain.Store;
import com.seungse.amadda.domain.StoreHoliday;
import com.seungse.amadda.domain.StoreImage;

import java.util.Collections;
import java.util.List;

public class StoreCommandMapper {

    public static Store toDomain(CreateStoreBasicCommand basic, CreateStoreAdditionalCommand additional) {
        return Store.builder()
            .businessNumber(basic.getBusinessNumber())
            .storeName(basic.getStoreName())
            .category(basic.getCategory())
            .description(basic.getDescription())
            .phoneNumber(basic.getPhoneNumber())
            .address(basic.getAddress())
            .storeImages(toStoreImageDomain(additional.getStoreImages()))
            .amenities(additional.getAmenities())
            .storeHoliday(toHolidayDomain(additional.getStoreHoliday()))
            .operatingHours(toOperatingHourDomain(additional.getOperatingHours()))
            .build();
    }

    private static List<StoreImage> toStoreImageDomain(List<CreateStoreAdditionalCommand.StoreImageCommand> files) {
        if (files == null)
            return Collections.emptyList();

        return files.stream().map(file -> StoreImage.builder()
            .bucketName(file.getBucketName())
            .originalFileName(file.getOriginalFileName())
            .filePath(file.getFilePath())
            .fileType(file.getFileType())
            .fileSize(file.getFileSize())
            .fileOrder(file.getFileOrder())
            .build()).toList();
    }

    private static StoreHoliday toHolidayDomain(CreateStoreAdditionalCommand.StoreHolidayCommand command) {
        if (command == null)
            return null;

        return StoreHoliday.builder()
            .isHoliday(command.isHoliday())
            .holidayType(command.getHolidayType())
            .holidayDays(command.getHolidayDays())
            .build();
    }

    private static List<OperatingHour> toOperatingHourDomain(List<CreateStoreAdditionalCommand.OperatingHourCommand> commands) {
        if (commands == null)
            return Collections.emptyList();

        return commands.stream().map(cmd -> OperatingHour.builder()
            .type(cmd.getType())
            .dayOfWeek(cmd.getDayOfWeek())
            .openTime(cmd.getOpenTime())
            .closeTime(cmd.getCloseTime())
            .breakStartTime(cmd.getBreakStartTime())
            .breakEndTime(cmd.getBreakEndTime())
            .build()).toList();
    }

}
