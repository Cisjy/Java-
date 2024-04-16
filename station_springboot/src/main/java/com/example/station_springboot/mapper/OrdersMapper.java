package com.example.station_springboot.mapper;

import com.example.station_springboot.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
