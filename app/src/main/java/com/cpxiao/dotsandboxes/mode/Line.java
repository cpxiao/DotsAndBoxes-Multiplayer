package com.cpxiao.dotsandboxes.mode;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.cpxiao.dotsandboxes.mode.extra.GameColor;
import com.cpxiao.gamelib.Config;

/**
 * @author cpxiao on 2017/08/03.
 */

public class Line {
    protected boolean DEBUG = Config.DEBUG;
    protected String TAG = getClass().getSimpleName();

    public LineCirclePoint mActionStartPoint = new LineCirclePoint();
    public LineCirclePoint mActionEndPoint = new LineCirclePoint();

    /**
     * 边的宽度
     */
    private float strokeWidth;
    private int color = GameColor.COLOR_LINE_DEFAULT;

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void onDraw(Canvas canvas, Paint paint) {
        if (mActionStartPoint.indexX < 0 || mActionStartPoint.indexY < 0) {
            return;
        }

        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);

        //        canvas.drawCircle(mActionStartPoint.x, mActionStartPoint.y, mActionStartPoint.r, paint);
        canvas.drawLine(mActionStartPoint.x, mActionStartPoint.y, mActionEndPoint.x, mActionEndPoint.y, paint);
        //        if (mActionEndPoint.indexX >= 0 && mActionEndPoint.indexY >= 0) {
        //            canvas.drawCircle(mActionEndPoint.x, mActionEndPoint.y, mActionEndPoint.r, paint);
        //        }
    }

    public void reset() {
        mActionStartPoint.resetIndex();
        mActionEndPoint.resetIndex();
    }

    public void setCirclePointR(float circlePointR) {
        mActionStartPoint.setR(circlePointR);
        mActionEndPoint.setR(circlePointR);
    }
}
