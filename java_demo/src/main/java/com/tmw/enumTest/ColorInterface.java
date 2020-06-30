package com.tmw.enumTest;

/**
 * @author TMW
 * @date 2020/6/28 10:05
 */
public interface ColorInterface {

    enum ColorEnum implements ColorInterface {
        GREEN, YELLOW, RED
    }

    enum NewColorEnum implements ColorInterface {
        NEW_GREEN, NEW_YELLOW, NEW_RED
    }
}
