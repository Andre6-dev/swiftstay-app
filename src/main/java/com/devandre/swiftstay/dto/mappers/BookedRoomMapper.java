package com.devandre.swiftstay.dto.mappers;

import com.devandre.swiftstay.persistence.models.BookedRoom;
import com.devandre.swiftstay.dto.BookedRoomDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookedRoomMapper {
    BookedRoom toEntity(BookedRoomDto bookedRoomDto);

    BookedRoomDto toDto(BookedRoom bookedRoom);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookedRoom partialUpdate(BookedRoomDto bookedRoomDto, @MappingTarget BookedRoom bookedRoom);
}