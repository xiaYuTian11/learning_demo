package com.tmw.enumTest;

import java.util.Objects;

/**
 * @author TMW
 * @date 2020/6/28 9:51
 */
public enum ColorEnum {

    /**
     * 颜色
     */
    RED(0), GREEN(1),

    BLANK(2), YELLOW(3);

    private int color;

    ColorEnum(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public static boolean equest(int i, ColorEnum colorEnum) {
        return colorEnum.getColor() == i;
    }

    public static ColorEnum getColorEnum(int color) {
        for (ColorEnum value : ColorEnum.values()) {
            if (Objects.equals(value.getColor(), color)) {
                return value;
            }
        }
        return null;
    }



}
