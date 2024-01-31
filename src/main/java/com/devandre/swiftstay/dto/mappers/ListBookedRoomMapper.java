package com.devandre.swiftstay.dto.mappers;

import com.devandre.swiftstay.persistence.models.BookedRoom;
import com.devandre.swiftstay.dto.ListBookedRoomDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ListBookedRoomMapper {
    BookedRoom toEntity(ListBookedRoomDto listBookedRoomDto);

    ListBookedRoomDto toDto(BookedRoom bookedRoom);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookedRoom partialUpdate(ListBookedRoomDto listBookedRoomDto, @MappingTarget BookedRoom bookedRoom);
}