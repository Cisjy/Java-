package com.example.station_springboot.service;

import com.example.station_springboot.entity.Stations;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
public interface StationsService extends IService<Stations> {

    Stations findByName(String name);
}
