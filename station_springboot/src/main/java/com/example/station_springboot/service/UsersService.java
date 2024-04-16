package com.example.station_springboot.service;

import com.example.station_springboot.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
public interface UsersService extends IService<Users> {


    Users findUserByUsername(String username);
}
