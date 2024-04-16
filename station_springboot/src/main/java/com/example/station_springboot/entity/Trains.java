package com.example.station_springboot.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author cisjy
 * @since 2024-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Trains对象", description = "")
public class Trains implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String trainNumber;

    private BigDecimal price;

    private String departureStationId;

    private String arrivalStationId;

    private String departureTime;

    private String arrivalTime;


}
