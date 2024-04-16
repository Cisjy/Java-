package com.example.station_springboot.entity;

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
@ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String trainNumber;

    private LocalDateTime createdAt;


}
