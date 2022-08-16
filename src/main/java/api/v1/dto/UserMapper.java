package api.v1.dto;

import api.v1.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User postToUser(PostUserDto postUserDto);
}
