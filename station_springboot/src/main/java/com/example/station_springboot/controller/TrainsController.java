package com.example.station_springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.station_springboot.common.QueryPageParam;
import com.example.station_springboot.common.Result;
import com.example.station_springboot.entity.Stations;
import com.example.station_springboot.entity.Trains;
import com.example.station_springboot.service.StationsService;
import com.example.station_springboot.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/trains")
@CrossOrigin
public class TrainsController {

    @Autowired
    private TrainsService trainsService;
    @Autowired
    private StationsService stationsService;


    @PostMapping("/add")
    public Result add(@RequestBody Map<String, Trains> map) {
        Trains trains = map.get("form");
        return trainsService.save(trains) ? Result.success() : Result.error(400, "添加失败");
    }

    @GetMapping("/delete")

    public Result delete(@RequestParam Integer id) {

        return trainsService.removeById(id) ? Result.success() : Result.error(400, "删除失败");

    }

    @PostMapping("/update")
    public Result update(@RequestBody Trains trains) {

        return trainsService.updateById(trains) ? Result.success() : Result.error(400, "更新失败");
    }


    @PostMapping("/listpage")
    public Result listpage(@RequestBody QueryPageParam queryPageParam) {
        System.out.println(queryPageParam);
        Page<Trains> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        List<Trains> trains = trainsService.mutilsearch(queryPageParam.getParam());


        IPage<Trains> iPage = trainsService.page(page, new QueryWrapper<Trains>().in("id", trains.stream().map(Trains::getId).collect(Collectors.toList())));

        //将车站id替换成车站名称
        List<Trains> records = iPage.getRecords();
        for (Trains train : records) {
            String departureStationId = train.getDepartureStationId();
            Stations stations = stationsService.getById(departureStationId);
            train.setDepartureStationId(stations.getName());
            String arrivalStationId = train.getArrivalStationId();
            Stations stations1 = stationsService.getById(arrivalStationId);
            train.setArrivalStationId(stations1.getName());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", iPage.getTotal());
        map.put("list", iPage.getRecords());
        return Result.success(map);
    }


}
