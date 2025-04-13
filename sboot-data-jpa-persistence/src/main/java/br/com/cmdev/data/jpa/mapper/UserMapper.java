package br.com.cmdev.data.jpa.mapper;

import br.com.cmdev.data.jpa.dto.AddressRequest;
import br.com.cmdev.data.jpa.dto.UserRequest;
import br.com.cmdev.data.jpa.dto.UserResponse;
import br.com.cmdev.data.jpa.entity.Address;
import br.com.cmdev.data.jpa.entity.User;
import br.com.cmdev.data.jpa.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "active", expression = "java(Boolean.TRUE)")
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "changeDate", ignore = true)
    User userRequestToUser(UserRequest request);

    @Mapping(target = "addressId", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address addressRequestToAddress(AddressRequest request);

    @Mapping(target = "creationDate", source = "creationDate", dateFormat = Constants.DATETIME_MILLISECONDS_FORMAT)
    @Mapping(target = "changeDate", source = "changeDate", dateFormat = Constants.DATETIME_MILLISECONDS_FORMAT)
    @Mapping(target = "isActive", source = "active")
    UserResponse userToUserResponse(User newUser);

    List<UserResponse> userListToUserResponseList(Iterable<User> users);

}
