package api.v1.controller;

import api.v1.dto.request.PostUserDto;
import api.v1.dto.UserMapper;
import api.v1.dto.response.BasicResponse;
import api.v1.dto.response.ErrorResponse;
import api.v1.dto.response.SuccessResponse;
import api.v1.dto.response.UserResponseDto;
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
    public ResponseEntity<BasicResponse> signup(@RequestBody PostUserDto postUserDto){
        UserResponseDto user = userMapper.userToUserResponseDto(userService.saveUser(userMapper.postToUser(postUserDto)));
        if (user == null) return new ResponseEntity<BasicResponse>(new ErrorResponse("회원 가입에 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<BasicResponse>(new SuccessResponse<UserResponseDto>(user), HttpStatus.CREATED);
    }

    @GetMapping("/usersAll")
    public ResponseEntity<BasicResponse> getUsers(){
        List<UserResponseDto> users = userMapper.usersToUserResponseDto(userService.findUsers());
        if (users.size() == 0) return new ResponseEntity<BasicResponse>(new ErrorResponse("가입된 회원이 없습니다."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<BasicResponse>(new SuccessResponse<List<UserResponseDto>>(users), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<BasicResponse> getUsersByCondition(@RequestParam(name = "location") @Nullable String location,
                                                          @RequestParam(name = "type") @Nullable String type){
        List<UserResponseDto> users = userMapper.usersToUserResponseDto(userService.findUsersByCondition(location, type));
        if (users.size() == 0) return new ResponseEntity<BasicResponse>(new ErrorResponse("일치하는 회원이 없습니다."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<BasicResponse>(new SuccessResponse<List<UserResponseDto>>(users), HttpStatus.OK);
    }

}
