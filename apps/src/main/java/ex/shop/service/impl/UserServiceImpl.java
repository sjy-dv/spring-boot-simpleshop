package ex.shop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ex.shop.dto.UserDto;
import ex.shop.mapper.UserMapper;
import ex.shop.service.UserService;
import ex.shop.utils.Bcrypt;
import ex.shop.utils.Jwt;
import ex.shop.vo.LoginVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final Bcrypt bcrypt;
    private final Jwt jwt;

    @Override
    @Transactional
    public LoginVo signUp(UserDto user) throws Exception {
        try {
            if (user.getEmail() == "" || user.getNickname() == "" || user.getUserId() == "" ||
                    user.getPassword() == "") {
                throw new Exception("parameter is empty");
            }
            user.setPassword(bcrypt.HashPassword(user.getPassword()));
            int res = userMapper.signUp(user);
            if (res == 0) {
                // error
                throw new Exception("create error");
            }
            res = userMapper.addInfo(false, "basic", user.getUserId());
            if (res == 0) {
                // error
                throw new Exception("create error");
            }
            LoginVo retval = new LoginVo();
            retval.setToken(jwt.CreateToken(user.getUserId(), user.getNickname()));
            retval.setRefreshToken(jwt.CreateRToken(user.getUserId()));
            retval.setGrade("basic");
            return retval;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public Boolean checkId(String userId) throws Exception {
        try {
            int res = userMapper.checkId(userId);
            if (res == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.toString());
        }
    }

    @Override
    public LoginVo signIn(UserDto req) throws Exception {
        try {
            UserDto user = userMapper.signIn(req.getUserId());
            Boolean check = bcrypt.CompareHash(req.getPassword(), user.getPassword());
            if (!check) {
                throw new Exception("패스워드가 일치하지 않습니다.");
            }
            LoginVo retval = new LoginVo();
            retval.setToken(jwt.CreateToken(user.getUserId(), user.getNickname()));
            retval.setRefreshToken(jwt.CreateRToken(user.getUserId()));
            return retval;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.toString());
        }
    }
}
