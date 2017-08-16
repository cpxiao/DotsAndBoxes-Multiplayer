package com.cpxiao.dotsandboxes.mode;

/**
 * @author cpxiao on 2017/08/03.
 */

public class LineCirclePoint {
    public float x, y, r;
    public int indexX, indexY;

    public LineCirclePoint() {
        resetIndex();
    }

    public void setR(float r) {
        this.r = r;
    }

    /**
     * Set the point's indexX and indexY coordinates
     */
    public void setIndex(int indexX, int indexY) {
        this.indexX = indexX;
        this.indexY = indexY;
    }

    /**
     * Set the point's x and y coordinates
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void resetIndex() {
        setIndex(-1, -1);
    }
}
