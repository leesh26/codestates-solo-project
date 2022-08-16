package api.v1.repository;

import api.v1.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRepository {
    List<User> findUsersByCondition(String location, String type);
}
