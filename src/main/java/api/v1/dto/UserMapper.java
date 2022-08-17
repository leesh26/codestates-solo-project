package api.v1.dto;

import api.v1.dto.request.PostUserDto;
import api.v1.dto.response.UserResponseDto;
import api.v1.entity.Company;
import api.v1.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponseDto> usersToUserResponseDto(List<User> users);

    default User postToUser(PostUserDto postUserDto) {
        if ( postUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( postUserDto.getName() );
        user.password( postUserDto.getPassword() );
        if ( postUserDto.getSex() != null ) {
            user.sex( Enum.valueOf( User.Sex.class, postUserDto.getSex() ) );
        }

        Company company = new Company(postUserDto.getCompanyName(), postUserDto.getCompanyLocation(), postUserDto.getCompanyType());
        user.companyInfo(company);
        return user.build();
    }

    default UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setName( user.getName() );
        if ( user.getSex() != null ) {
            userResponseDto.setSex( user.getSex().name() );
        }

        userResponseDto.setCompany(user.getCompanyInfo());
        return userResponseDto;
    }
}
