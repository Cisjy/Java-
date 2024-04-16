package com.example.station_springboot.service.impl;

import com.example.station_springboot.common.QueryPageParam;
import com.example.station_springboot.entity.Trains;
import com.example.station_springboot.mapper.TrainsMapper;
import com.example.station_springboot.service.TrainsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
@Service
public class TrainsServiceImpl extends ServiceImpl<TrainsMapper, Trains> implements TrainsService {
    @Autowired
    private TrainsMapper trainsMapper;

    public List<Trains> mutilsearch(HashMap queryPageParam) {
        return trainsMapper.mutilsearch(queryPageParam);
    }

    ;
}
