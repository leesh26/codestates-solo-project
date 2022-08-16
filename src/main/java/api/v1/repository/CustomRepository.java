package api.v1.repository;

import api.v1.dto.GetUserDto;
import api.v1.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRepository {
    List<User> findUsersByCondition(GetUserDto getUserDto);
}
