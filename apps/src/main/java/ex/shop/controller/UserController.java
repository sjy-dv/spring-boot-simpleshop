package ex.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import ex.shop.dto.UserDto;
import ex.shop.exception.GlobalException;
import ex.shop.service.AuthService;
import ex.shop.service.UserService;
import ex.shop.utils.ResponseHandler;
import ex.shop.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final GlobalException globalException;
    private final ResponseHandler response;

    @PostMapping("/ex/shop/signup")
    public ResponseEntity<Object> SignUp(@RequestBody UserDto user) {
        try {
            LoginVo reply = userService.signUp(user);
            if (reply.getRefreshToken() == null || reply.getToken() == null || reply.getGrade() == null) {
                throw new Exception("token create error");
            }
            return response.ReplyHandler(reply);
        } catch (Exception e) {
            return globalException.handleRuntimeException(e);
        }
    }

    @PostMapping("/ex/shop/checkid")
    public ResponseEntity<Object> CheckId(@RequestBody Map<String, String> req) {
        try {
            Boolean result = userService.checkId(req.get("user_id"));
            Map<String, Boolean> map = new HashMap<>();
            map.put("result", result);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return globalException.handleRuntimeException(e);
        }
    }

    @PostMapping("/ex/shop/signin")
    public ResponseEntity<Object> Signin(@RequestBody UserDto req) {
        try {
            LoginVo reply = userService.signIn(req);
            return response.ReplyHandler(reply);
        } catch (Exception e) {
            // TODO: handle exception
            return globalException.handleRuntimeException(e);
        }
    }

    // 리프레쉬로 엑세스 재발급
    @GetMapping("/ex/shop/issuetoken")
    public ResponseEntity<Object> IssuedToken(@RequestHeader("rxauth") String rxauth) {
        try {
            LoginVo reply = authService.IssuedToken(rxauth);
            return response.ReplyHandler(reply);
        } catch (Exception e) {
            // TODO: handle exception
            return globalException.handleRuntimeException(e);
        }
    }

    @GetMapping("/ex/shop/autoissuetoken")
    public ResponseEntity<Object> AutoIssuedToken(@RequestHeader("rxauth") String rxauth) {
        try {
            LoginVo reply = authService.AutoIssuedToken(rxauth);
            return response.ReplyHandler(reply);
        } catch (Exception e) {
            // TODO: handle exception
            return globalException.handleRuntimeException(e);
        }
    }
}
