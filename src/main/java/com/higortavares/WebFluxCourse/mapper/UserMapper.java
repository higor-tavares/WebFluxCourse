package com.higortavares.WebFluxCourse.mapper;

import com.higortavares.WebFluxCourse.entity.User;
import com.higortavares.WebFluxCourse.model.request.UserRequest;
import com.higortavares.WebFluxCourse.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest userRequest);
    UserResponse toResponse(final User userEntity);
}
