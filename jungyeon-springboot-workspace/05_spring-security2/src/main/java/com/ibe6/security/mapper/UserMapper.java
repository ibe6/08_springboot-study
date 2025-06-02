package com.ibe6.security.mapper;

import com.ibe6.security.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(UserDto user);
    UserDto selectUserById(String userId);
}
