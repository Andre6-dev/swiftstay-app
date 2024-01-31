package com.devandre.swiftstay.dto.mappers;

import com.devandre.swiftstay.dto.ListUserDto;
import com.devandre.swiftstay.persistence.models.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ListUserMapper {
    User toEntity(ListUserDto listUserDto);

    ListUserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(ListUserDto listUserDto, @MappingTarget User user);
}