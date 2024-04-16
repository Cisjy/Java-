package com.example.station_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author cisjy
 * @since 2024-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Stations对象", description = "")
public class Stations {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;


}
