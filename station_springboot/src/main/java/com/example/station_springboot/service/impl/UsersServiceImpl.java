package com.example.station_springboot.service.impl;

import com.example.station_springboot.entity.Users;
import com.example.station_springboot.mapper.UsersMapper;
import com.example.station_springboot.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users findUserByUsername(String username) {
        return usersMapper.getUserByUsername(username);
    }

}
