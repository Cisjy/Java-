package com.example.station_springboot.mapper;

import com.example.station_springboot.entity.Stations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
@Mapper
public interface StationsMapper extends BaseMapper<Stations> {

    @Select("SELECT  * from stations where name = #{name}")
    Stations findByName(String name);
}
