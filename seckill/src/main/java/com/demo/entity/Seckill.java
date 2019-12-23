package com.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author TMW
 * @date 2019/12/23 15:10
 */
@Data
public class Seckill implements Serializable {

    private static final long serialVersionUID = 8623826691592896073L;
    /**
     * 主键ID
     */
    private long seckillId;
    /**
     * 秒杀商品名字
     */
    private String name;
    /**
     * 秒杀的商品编号
     */
    private int number;
    /**
     * 开始秒杀的时间
     */
    private LocalDateTime startTime;
    /**
     * 结束秒杀的时间
     */
    private LocalDateTime endTime;
    /**
     * 创建的时间
     */
    private LocalDateTime createTIme;

    @Override
    public String toString() {
        return "com.demo.entity.Seckill{" +
                "主键ID=" + seckillId +
                ", 秒杀商品='" + name + '\'' +
                ", 编号=" + number +
                ", 开始秒杀时间=" + startTime +
                ", 结束秒杀时间=" + endTime +
                ", 创建时间=" + createTIme +
                '}';
    }
}
