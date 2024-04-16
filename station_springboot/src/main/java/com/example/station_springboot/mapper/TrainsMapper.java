package com.example.station_springboot.mapper;

import com.example.station_springboot.common.QueryPageParam;
import com.example.station_springboot.entity.Trains;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
@Mapper
public interface TrainsMapper extends BaseMapper<Trains> {

    @Select("SELECT t.* " +
            "FROM trains t " +
            "JOIN stations dst ON t.departure_station_id = dst.Id " +
            "JOIN stations ast ON t.arrival_station_id = ast.Id " +
            "WHERE t.train_number LIKE CONCAT('%', #{queryPageParam.trainNumber}, '%') " +
            "  AND dst.name LIKE CONCAT('%', #{queryPageParam.departureStation}, '%') " +
            "  AND ast.name LIKE CONCAT('%', #{queryPageParam.arrivalStation}, '%')")
    List<Trains> mutilsearch(@Param("queryPageParam") HashMap queryPageParam);

}
