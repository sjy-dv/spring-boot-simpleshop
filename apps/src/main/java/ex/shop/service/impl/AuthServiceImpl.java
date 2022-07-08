package ex.shop.service.impl;

import org.springframework.stereotype.Service;

import ex.shop.dto.UserDto;
import ex.shop.mapper.UserMapper;
import ex.shop.service.AuthService;
import ex.shop.utils.Jwt;
import ex.shop.vo.LoginVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final Jwt jwt;

    // use refresh issued access
    @Override
    public LoginVo IssuedToken(String token) throws Exception {
        try {
            String userId = jwt.VerifyRToken(token);
            if (userId == null) {
                throw new Exception("token is invalid");
            }
            UserDto user = userMapper.findOne(userId);
            LoginVo retval = new LoginVo();
            retval.setToken(jwt.CreateToken(user.getUserId(), user.getNickname()));
            return retval;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e);
        }
    }

    // refresh issued access, refresh
    @Override
    public LoginVo AutoIssuedToken(String token) throws Exception {
        try {
            String userId = jwt.VerifyRToken(token);
            if (userId == null) {
                throw new Exception("token is invalid");
            }
            UserDto user = userMapper.findOne(userId);
            LoginVo retval = new LoginVo();
            retval.setToken(jwt.CreateToken(user.getUserId(), user.getNickname()));
            retval.setRefreshToken(jwt.CreateRToken(user.getUserId()));
            return retval;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e);
        }
    }
}
