package com.example.station_springboot.service.impl;

import com.example.station_springboot.entity.Orders;
import com.example.station_springboot.mapper.OrdersMapper;
import com.example.station_springboot.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


}
