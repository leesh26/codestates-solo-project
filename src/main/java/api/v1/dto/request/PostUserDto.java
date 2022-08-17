package api.v1.dto.request;

import api.v1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserDto {
    String name;
    String password;
    String sex;
    String companyName;
    String companyType;
    String companyLocation;
}
