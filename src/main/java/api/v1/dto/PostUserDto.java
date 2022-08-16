package api.v1.dto;

import api.v1.entity.User;
import lombok.Getter;

@Getter
public class PostUserDto {
    String name;
    String password;
    String sex;
    String companyName;
    String companyType;
    String companyLocation;
}
