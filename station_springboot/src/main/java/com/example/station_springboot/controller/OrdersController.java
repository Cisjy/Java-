package com.example.station_springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.station_springboot.common.QueryPageParam;
import com.example.station_springboot.common.Result;
import com.example.station_springboot.entity.Orders;
import com.example.station_springboot.entity.Trains;
import com.example.station_springboot.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p
 *
 * @author cisjy
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // 订单列表
    @PostMapping("/list")
    public Result list() {
        return Result.success(ordersService.list());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Map<String, String> requestBody) {
        System.out.println(requestBody);
        String username = requestBody.get("username");

        Orders orders = new Orders();
        orders.setUsername(username);
        orders.setTrainNumber(requestBody.get("trainNumber"));
        orders.setCreatedAt(LocalDateTime.now());

        return ordersService.save(orders) ? Result.success() : Result.error(200, "添加失败");
    }


    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        return ordersService.removeById(id) ? Result.success() : Result.error(200, "删除失败");
    }

    @PostMapping("/listpage")
    public Result listpage(@RequestBody QueryPageParam queryPageParam) {
        Page<Orders> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Orders::getTrainNumber, queryPageParam.getParam().get("trainNumber"));
        wrapper.like(Orders::getUsername, queryPageParam.getParam().get("username"));
        IPage<Orders> iPage = ordersService.page(page, wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", iPage.getRecords());
        map.put("total", iPage.getTotal());
        return Result.success(map);
    }

}



