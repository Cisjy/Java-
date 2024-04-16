package com.example.station_springboot.service;

import com.example.station_springboot.common.QueryPageParam;
import com.example.station_springboot.entity.Trains;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
public interface TrainsService extends IService<Trains> {

    List<Trains> mutilsearch(HashMap queryPageParam);
}
