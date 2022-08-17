package api.v1.dto;

import api.v1.dto.request.PostUserDto;
import api.v1.dto.response.UserResponseDto;
import api.v1.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User postToUser(PostUserDto postUserDto);

    UserResponseDto userToUserResponseDto(User user);
    List<UserResponseDto> usersToUserResponseDto(List<User> users);
}
