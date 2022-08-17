package api.v1.service;

import api.v1.entity.User;
import api.v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user){
        if (userRepository.findByName(user.getName()).isPresent()) return null;
        return userRepository.save(user);
    }

    public List<User> findUsers(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<User> findUsersByCondition(String location, String type){
        return userRepository.findUsersByCondition(location, type);
    }

}
