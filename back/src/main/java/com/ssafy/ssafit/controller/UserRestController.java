package com.ssafy.ssafit.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ssafy.ssafit.config.JwtUtil;
import com.ssafy.ssafit.dto.User;
import com.ssafy.ssafit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private JwtUtil jwtUtil;

    private static final String SUCCESS = "succes";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(User user) {


        Map<String, Object> result = new HashMap<String, Object>();

        // user를 이용해서 Service -> Dao -> DB를 통해 실제 유저인지 확인을 해야한다.
        // 우리는 하지 않겠다. ㅎ 직접 해볼것
        // 아이디가 널이 아니거나 길이가 있거나
        HttpStatus status = null;


        String userid = user.getUserid();
        Optional<User> findUser = Optional.ofNullable(userService.selectOne(userid));


        try {
            if ( findUser !=null && user.getPassword().equals(findUser.get().getPassword())) {
                System.out.println(findUser.get().getId());
                result.put("access-token", jwtUtil.createToken("id", user.getUserid()));
                result.put("loginUserId", findUser.get().getId());
                result.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }else {
                result.put("message", FAIL);
                status = HttpStatus.NO_CONTENT;
            }
        } catch (UnsupportedEncodingException e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String,Object>>(result, status);
    }
}