package br.com.cmdev.data.jdbc.mapper;

import br.com.cmdev.data.jdbc.dto.UserRequest;
import br.com.cmdev.data.jdbc.dto.UserResponse;
import br.com.cmdev.data.jdbc.entity.User;
import br.com.cmdev.data.jdbc.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", expression = "java(Boolean.TRUE)")
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "changeDate", ignore = true)
    User userRequestToUser(UserRequest request);

    @Mapping(target = "creationDate", source = "creationDate", dateFormat = Constants.DATETIME_MILLISECONDS_FORMAT)
    @Mapping(target = "changeDate", source = "changeDate", dateFormat = Constants.DATETIME_MILLISECONDS_FORMAT)
    UserResponse userToUserResponse(User newUser);

    List<UserResponse> userListToUserResponseList(Iterable<User> users);

}
