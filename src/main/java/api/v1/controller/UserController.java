package api.v1.controller;

import api.v1.dto.PostUserDto;
import api.v1.dto.UserMapper;
import api.v1.entity.User;
import api.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody PostUserDto postUserDto){
        User user = userMapper.postToUser(postUserDto);
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/usersAll")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsersByCondition(@RequestParam @Nullable String location,
                                                          @RequestParam @Nullable String type ){
        // 조건 (지역, 업종)으로 조회
        return ResponseEntity.ok(userService.findUsersByCondition(location, type));
    }

}
