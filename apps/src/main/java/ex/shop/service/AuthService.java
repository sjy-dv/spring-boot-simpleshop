package ex.shop.service;

import ex.shop.vo.LoginVo;

public interface AuthService {

    LoginVo IssuedToken(String token) throws Exception;

    LoginVo AutoIssuedToken(String token) throws Exception;

}
