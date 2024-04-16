package com.example.station_springboot.controller;


import com.example.station_springboot.common.Result;
import com.example.station_springboot.entity.Users;
import com.example.station_springboot.service.UsersService;
import com.example.station_springboot.utils.JwtsUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/login")
    public Result login(@RequestBody Users body) {
        String token = JwtsUtil.generateToken(body.getUsername());
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        Users users = usersService.findUserByUsername(body.getUsername());
        if (users.getPassword().equals(DigestUtils.md5DigestAsHex(body.getPassword().getBytes()))) {
            return Result.success(map);
        }
        return Result.error(400, "用户名或密码错误");
    }

    @RequestMapping("/getusers")
    public Result getUser() {
        return Result.success(usersService.list());
    }


}
