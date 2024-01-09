package com.devandre.swiftstay.dto.mappers;

import com.devandre.swiftstay.dto.ListRoleDto;
import com.devandre.swiftstay.persistence.models.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ListRoleMapper {
    Role toEntity(ListRoleDto listRoleDto);

    ListRoleDto toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(ListRoleDto listRoleDto, @MappingTarget Role role);
}