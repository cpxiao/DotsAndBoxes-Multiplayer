package com.cpxiao.dotsandboxes.mode;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.cpxiao.dotsandboxes.mode.common.Sprite;
import com.cpxiao.dotsandboxes.mode.extra.GameColor;

/**
 * @author cpxiao on 2017/06/11.
 */

public class Block extends Sprite {

    /**
     * 四条边的宽度
     */
    private float strokeWidth;

    /**
     * 上下左右颜色
     */
    private static final int DEFAULT_COLOR = GameColor.COLOR_LINE_DEFAULT;

    private int LColor = DEFAULT_COLOR;
    private int TColor = DEFAULT_COLOR;
    private int RColor = DEFAULT_COLOR;
    private int BColor = DEFAULT_COLOR;

//    private int mBlockColor = GameColor.COLOR_BLOCK_DEFAULT;
    private int mBlockColor ;

    private RectF mCenterColorRectF;

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setBlockColor(int blockColor) {
        mBlockColor = blockColor;
    }

    public RectF getCenterColorRectF() {
        return mCenterColorRectF;
    }

    public void setCenterColorRectF(float left, float top, float right, float bottom) {
        mCenterColorRectF = new RectF();
        mCenterColorRectF.left = left;
        mCenterColorRectF.top = top;
        mCenterColorRectF.right = right;
        mCenterColorRectF.bottom = bottom;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        //        super.onDraw(canvas, paint);
        paint.setStrokeWidth(strokeWidth);

        drawCenterColor(canvas, paint);

        drawLeftLine(canvas, paint);
        drawTopLine(canvas, paint);
        drawRightLine(canvas, paint);
        drawBottomLine(canvas, paint);

    }

    private void drawCenterColor(Canvas canvas, Paint paint) {
        paint.setColor(mBlockColor);
        canvas.drawRect(getCenterColorRectF(), paint);
    }

    private void drawLeftLine(Canvas canvas, Paint paint) {
        paint.setColor(LColor);
        canvas.drawLine(getX(), getY(), getX(), getY() + getHeight(), paint);
    }

    private void drawTopLine(Canvas canvas, Paint paint) {
        paint.setColor(TColor);
        canvas.drawLine(getX(), getY(), getX() + getWidth(), getY(), paint);
    }

    private void drawRightLine(Canvas canvas, Paint paint) {
        paint.setColor(RColor);
        canvas.drawLine(getX() + getWidth(), getY(), getX() + getWidth(), getY() + getHeight(), paint);
    }

    private void drawBottomLine(Canvas canvas, Paint paint) {
        paint.setColor(BColor);
        canvas.drawLine(getX(), getY() + getHeight(), getX() + getWidth(), getY() + getHeight(), paint);
    }

    public boolean isDefaultColor() {
        return mBlockColor == DEFAULT_COLOR;
    }

    private boolean isDefaultColor(LTRB ltrb) {
        if (ltrb == LTRB.LEFT) {
            return LColor == DEFAULT_COLOR;
        } else if (ltrb == LTRB.TOP) {
            return TColor == DEFAULT_COLOR;
        } else if (ltrb == LTRB.RIGHT) {
            return RColor == DEFAULT_COLOR;
        } else if (ltrb == LTRB.BOTTOM) {
            return BColor == DEFAULT_COLOR;
        } else {
            if (DEBUG) {
                throw new IllegalArgumentException("LTRB error!");
            }
        }
        return true;
    }

    /**
     * @param ltrb  方向
     * @param color 颜色
     * @return true 更新了;false 未更新
     */
    public boolean updateColor(LTRB ltrb, int color) {
        if (isDefaultColor(ltrb)) {
            if (ltrb == LTRB.LEFT) {
                LColor = color;
            } else if (ltrb == LTRB.TOP) {
                TColor = color;
            } else if (ltrb == LTRB.RIGHT) {
                RColor = color;
            } else if (ltrb == LTRB.BOTTOM) {
                BColor = color;
            } else {
                if (DEBUG) {
                    throw new IllegalArgumentException("LTRB error!");
                }
            }
            return true;
        }
        return false;

    }

    public boolean isFull() {
        return LColor != DEFAULT_COLOR && TColor != DEFAULT_COLOR && RColor != DEFAULT_COLOR && BColor != DEFAULT_COLOR;
    }
}