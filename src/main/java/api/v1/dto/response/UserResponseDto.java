package api.v1.dto.response;

import api.v1.entity.Company;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {
    Long id;
    String name;
    String sex;
    Company company;
}
