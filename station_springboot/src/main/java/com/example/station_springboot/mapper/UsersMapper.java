package com.example.station_springboot.mapper;

import com.example.station_springboot.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    @Select("SELECT * FROM users WHERE username = #{username}")
    Users getUserByUsername(String username);
}
