package com.devandre.swiftstay.dto.mappers;

import com.devandre.swiftstay.dto.ListRoomDto;
import com.devandre.swiftstay.persistence.models.Room;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ListRoomMapper {
    Room toEntity(ListRoomDto listRoomDto);

    ListRoomDto toDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(ListRoomDto listRoomDto, @MappingTarget Room room);
}