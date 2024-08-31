package com.higortavares.WebFluxCourse.mapper;

import com.higortavares.WebFluxCourse.entity.User;
import com.higortavares.WebFluxCourse.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest userRequest);
}
