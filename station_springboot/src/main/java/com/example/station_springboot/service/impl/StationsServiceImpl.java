package com.example.station_springboot.service.impl;

import com.example.station_springboot.entity.Stations;
import com.example.station_springboot.mapper.StationsMapper;
import com.example.station_springboot.service.StationsService;
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
public class StationsServiceImpl extends ServiceImpl<StationsMapper, Stations> implements StationsService {

    @Autowired
    private StationsMapper stationsMapper;


    public Stations findByName(String name) {
        return this.baseMapper.findByName(name);
    }

}
