package com.demo.enums;

/**
 * @author TMW
 * @date 2019/12/24 14:33
 */
public enum SeckillStatEnum {
    /**
     * 秒杀成功
     */
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATE_REWRITE(-3, "数据篡改");
    private int state;
    private String info;

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    SeckillStatEnum() {
    }

    SeckillStatEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    public static SeckillStatEnum stateOf(int state) {
        for (SeckillStatEnum seckillStatEnum : values()) {
            if (seckillStatEnum.getState() == state) {
                return seckillStatEnum;
            }
        }
        return null;
    }
}
