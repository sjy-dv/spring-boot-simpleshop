package ex.shop.mapper;

import org.apache.ibatis.annotations.*;

import ex.shop.dto.UserDto;

@Mapper
public interface UserMapper {

    @Insert("insert into user(nickname, email, user_id, password) values(#{nickname}, #{email}, #{userId}, #{password})")
    int signUp(UserDto user);

    @Insert("insert into user_info(user_id, email_status, grade) values(#{userId},#{emailStatus}, #{grade})")
    int addInfo(Boolean emailStatus, String grade, String userId);

    @Select("select count(*) from user where user_id = #{userId}")
    int checkId(String userId);

    @Select("select user_id as userId, password from user where user_id = #{userId}")
    UserDto signIn(String userId);

    @Select("select user_id as userId, nickname  from user where user_id = #{userId}")
    UserDto findOne(String userId);
}
