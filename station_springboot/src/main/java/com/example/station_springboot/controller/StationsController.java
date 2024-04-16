package com.example.station_springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.station_springboot.common.QueryPageParam;
import com.example.station_springboot.common.Result;
import com.example.station_springboot.entity.Stations;
import com.example.station_springboot.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/stations")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StationsController {

    @Autowired
    private StationsService stationsService;


    @PostMapping("/getstations")
    public Result getStations() {
        return Result.success(stationsService.list());
    }

    //增
    @PostMapping("/add")
    public Result add(@RequestBody Stations stations) {

        String name = stations.getName();

        Stations existingStations = stationsService.findByName(name);
        if (existingStations != null) {
            return Result.error(400, "该站点已存在");
        } else {
            stationsService.save(stations);
            return Result.success();
        }


    }

    //删
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        return stationsService.removeById(id) ? Result.success() : Result.error(400, "删除失败");
    }

    //改
    @PostMapping("/update")
    public Result update(@RequestBody Stations stations) {

        return stationsService.updateById(stations) ? Result.success() : Result.error(400, "更新失败");
    }

    //查
    @PostMapping("/listpage")
    public Result listPage(@RequestBody QueryPageParam query) {


        Page<Stations> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Stations> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Stations::getName, query.getParam().get("name"));
        IPage result = stationsService.page(page, wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", result.getRecords());
        map.put("total", result.getTotal());
        return Result.success(map);
    }

}
