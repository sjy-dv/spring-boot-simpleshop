package ex.shop.service;

import ex.shop.dto.UserDto;
import ex.shop.vo.LoginVo;

public interface UserService {

    LoginVo signUp(UserDto user) throws Exception;

    Boolean checkId(String userId) throws Exception;

    LoginVo signIn(UserDto user) throws Exception;
}
