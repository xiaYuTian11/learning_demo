package com.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author TMW
 * @date 2019/12/23 15:03
 */
@Data
public class SuccessKilled implements Serializable {

    private static final long serialVersionUID = -7648274829417928584L;

    private long seckillId;
    /**
     * 用户的手机号码
     */
    private long userPhone;
    /**
     * 秒杀的状态
     */
    private short state;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 多对一,因为一件商品在库存中肯定有许多,对应的购买信息也有很多
     */
    private Seckill seckill;

    @Override
    public String toString() {
        return "com.demo.entity.SuccessKilled{" +
                "主键ID=" + seckillId +
                ", 手机号码=" + userPhone +
                ", 秒杀状态=" + state +
                ", 创建时间=" + createTime +
                ", 秒杀的商品=" + seckill +
                '}';
    }
}
