package api.v1.dto.response;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {
    Long id;
    String name;
    String sex;
    String companyName;
    String companyType;
    String companyLocation;
}
