package com.cpxiao.dotsandboxes.mode;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.cpxiao.dotsandboxes.mode.common.Sprite;

/**
 * 正方形的角
 *
 * @author cpxiao on 2017/08/02.
 */
public class CornerOfBlock extends Sprite {


    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        canvas.drawCircle(getCenterX(), getCenterY(), 0.5F * getWidth(), paint);
    }
}