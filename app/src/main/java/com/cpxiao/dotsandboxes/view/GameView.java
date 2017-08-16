package com.cpxiao.dotsandboxes.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.cpxiao.R;
import com.cpxiao.dotsandboxes.mode.extra.Extra;
import com.cpxiao.dotsandboxes.mode.Block;
import com.cpxiao.dotsandboxes.mode.CornerOfBlock;
import com.cpxiao.dotsandboxes.mode.LTRB;
import com.cpxiao.dotsandboxes.mode.Line;
import com.cpxiao.dotsandboxes.mode.LineCirclePoint;
import com.cpxiao.dotsandboxes.mode.extra.GameColor;
import com.cpxiao.gamelib.views.BaseSurfaceView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author cpxiao on 2017/03/22.
 */

public class GameView extends BaseSurfaceView {
    private Bundle mBundle;
    private Block[][] mBlockArray;
    private CornerOfBlock[][] mCornerOfBlockArray;
    private Line mLine;
    private int mCurrentPlayerColor;//当前玩家颜色
    private List<Integer> mPlayerScoreList = new ArrayList<>();//得分
    private int[] mPlayerColorArray;//颜色
    private int mPlayerColorArrayIndex = 0;//数组下标

    private int mBlockCountX = Extra.Name.BLOCK_COUNT_X_DEFAULT;
    private int mBlockCountY = Extra.Name.BLOCK_COUNT_Y_DEFAULT;
    private int mPlayerCount = Extra.Name.PLAYER_COUNT_DEFAULT;

    private static final float strokeWidthPrepare = 0.018F;
    private static final float cornerOfBlockWHPrepare = 0.05F;

    public GameView(Context context, Bundle bundle) {
        super(context);
        mBundle = bundle;
        setBgColor(ContextCompat.getColor(context, R.color.commonBg));
    }

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initWidget() {
        if (mBundle != null) {
            mBlockCountX = mBundle.getInt(Extra.Name.BLOCK_COUNT_X, mBlockCountX);
            mBlockCountY = mBundle.getInt(Extra.Name.BLOCK_COUNT_Y, mBlockCountY);
            mPlayerCount = mBundle.getInt(Extra.Name.PLAYER_COUNT, mPlayerCount);
        }
        float blockWH = Math.min(0.85F * mViewWidth / mBlockCountX, 0.6F * mViewHeight / mBlockCountY);
        float strokeWidth = strokeWidthPrepare * mViewWidth;
        float paddingT = 0.5F * (mViewHeight - blockWH * mBlockCountY);
        float paddingLR = 0.5F * (mViewWidth - blockWH * mBlockCountX);

        mBlockArray = new Block[mBlockCountY][mBlockCountX];
        float blockPadding = 1.5F * strokeWidth;
        for (int x = 0; x < mBlockCountX; x++) {
            for (int y = 0; y < mBlockCountY; y++) {
                Block block = new Block();
                block.setX(paddingLR + blockWH * x);
                block.setY(paddingT + blockWH * y);
                block.setWidth(blockWH);
                block.setHeight(blockWH);
                block.setStrokeWidth(strokeWidth);
                block.setCenterColorRectF(block.getX() + blockPadding
                        , block.getY() + blockPadding
                        , block.getX() + block.getWidth() - blockPadding
                        , block.getY() + block.getHeight() - blockPadding);

                mBlockArray[y][x] = block;
            }
        }

        float cornerOfBlockWH = cornerOfBlockWHPrepare * mViewWidth;
        float cornerOfBlockCollideWH = 1.5F * cornerOfBlockWH;
        mCornerOfBlockArray = new CornerOfBlock[mBlockCountY + 1][mBlockCountX + 1];
        for (int x = 0; x <= mBlockCountX; x++) {
            for (int y = 0; y <= mBlockCountY; y++) {
                CornerOfBlock cornerOfBlock = new CornerOfBlock();
                cornerOfBlock.setWidth(cornerOfBlockWH);
                cornerOfBlock.setHeight(cornerOfBlockWH);
                float centerX = paddingLR + blockWH * x;
                float centerY = paddingT + blockWH * y;
                cornerOfBlock.centerTo(paddingLR + blockWH * x, paddingT + blockWH * y);
                cornerOfBlock.setCollideRectF(centerX - cornerOfBlockCollideWH
                        , centerY - cornerOfBlockCollideWH
                        , centerX + cornerOfBlockCollideWH
                        , centerY + cornerOfBlockCollideWH);
                mCornerOfBlockArray[y][x] = cornerOfBlock;
            }
        }

        mLine = new Line();
        mLine.setStrokeWidth(strokeWidth);
        mLine.setCirclePointR(0.5F * cornerOfBlockWH);

        mPlayerColorArray = GameColor.getPlayerColorArray(mPlayerCount);
        mPlayerColorArrayIndex = 0;
        mCurrentPlayerColor = mPlayerColorArray[mPlayerColorArrayIndex];

        mPlayerScoreList.clear();
        for (int i = 0; i < mPlayerCount; i++) {
            mPlayerScoreList.add(i, 0);
        }
    }

    @Override
    public void drawCache() {
        //注意绘制顺序，防止出现覆盖异常问题
        drawBlock(mCanvasCache, mPaint);
        drawMotionEventLine(mCanvasCache, mPaint);
        drawCornerOfBlock(mCanvasCache, mPaint);
        drawTitle(mCanvasCache, mPaint);
    }

    private void drawTitle(Canvas canvas, Paint paint) {
        paint.setColor(mCurrentPlayerColor);
        String currentPlayerMsg = getContext().getString(GameColor.getTitleMsg(mPlayerColorArrayIndex));
        String title = String.format(getContext().getString(R.string.title_format), currentPlayerMsg);
        paint.setTextSize(0.06F * mViewWidth);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, 0.5F * mViewWidth, 0.15F * mViewHeight, paint);
    }


    private void drawBlock(Canvas canvas, Paint paint) {
        if (mBlockArray == null) {
            return;
        }
        for (int x = 0; x < mBlockCountX; x++) {
            for (int y = 0; y < mBlockCountY; y++) {
                mBlockArray[y][x].onDraw(canvas, paint);
            }
        }
    }


    private void drawCornerOfBlock(Canvas canvas, Paint paint) {
        paint.setColor(GameColor.COLOR_CIRCLE);
        if (mCornerOfBlockArray == null) {
            return;
        }
        for (int x = 0; x <= mBlockCountX; x++) {
            for (int y = 0; y <= mBlockCountY; y++) {
                mCornerOfBlockArray[y][x].onDraw(canvas, paint);
            }
        }
    }

    private void drawMotionEventLine(Canvas canvas, Paint paint) {
        if (mLine == null || mLine.mActionStartPoint == null || mLine.mActionEndPoint == null) {
            if (DEBUG) {
                throw new IllegalArgumentException("param error!");
            }
            return;
        }
        mLine.setColor(mCurrentPlayerColor);
        mLine.onDraw(canvas, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //        return super.onTouchEvent(event);
        if (DEBUG) {
            Log.d(TAG, "onTouch: ");
        }
        int action = event.getAction();

        if (action == MotionEvent.ACTION_MOVE) {
            if (mLine.mActionStartPoint.indexX < 0 || mLine.mActionStartPoint.indexY < 0) {
                setupPointByMotionEvent(mLine.mActionStartPoint, event);
            }
            setupPointByMotionEvent(mLine.mActionEndPoint, event);
        } else if (action == MotionEvent.ACTION_UP) {
            checkLine();

            boolean isGameOver = checkGameOver();
            if (isGameOver) {
                showGameOverDialog();

            }
            mLine.reset();
        }
        myDraw();
        return true;
    }

    /**
     * show game over dialog
     */
    private void showGameOverDialog() {
        Resources resources = getResources();
        String[] playerArray = resources.getStringArray(R.array.player_array);
        String winner = "";
        int maxScore = 0;
        for (int i = 0; i < mPlayerScoreList.size(); i++) {
            maxScore = Math.max(mPlayerScoreList.get(i), maxScore);
        }
        for (int i = 0; i < mPlayerScoreList.size(); i++) {
            if (maxScore == mPlayerScoreList.get(i)) {
                winner = winner + " " + playerArray[i];
            }
        }
        String title = String.format(resources.getString(R.string.winner_format), winner);

        String msg = "";
        for (int i = 0; i < mPlayerScoreList.size(); i++) {
            msg = msg + String.format(resources.getString(R.string.score_format), playerArray[i]) + " " + mPlayerScoreList.get(i) + "\n";
        }

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDraw();
                        initWidget();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 判断游戏是否结束
     */

    private boolean checkGameOver() {
        if (mBlockArray == null) {
            if (DEBUG) {
                throw new IllegalArgumentException("param error!");
            }
            return true;
        }
        for (int x = 0; x < mBlockCountX; x++) {
            for (int y = 0; y < mBlockCountY; y++) {
                Block block = mBlockArray[y][x];
                if (!block.isFull()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断此次划线是否有效
     */
    private void checkLine() {
        int sX = mLine.mActionStartPoint.indexX;
        int sY = mLine.mActionStartPoint.indexY;
        int eX = mLine.mActionEndPoint.indexX;
        int eY = mLine.mActionEndPoint.indexY;
        //开始点和结束点左右相邻，即画的是横线，则更新上下两个Block
        if ((sY == eY) && (sX - eX == 1 || sX - eX == -1)) {
            int indexX = (sX + eX) / 2;
            boolean isUpdatedColor0 = updateBlockColor(indexX, sY - 1, LTRB.BOTTOM, mCurrentPlayerColor);
            boolean isUpdatedColor1 = updateBlockColor(indexX, sY, LTRB.TOP, mCurrentPlayerColor);
            if (isUpdatedColor0 || isUpdatedColor1) {
                boolean isUpdatedMsg0 = updateBlockMsg(indexX, sY - 1);
                boolean isUpdatedMsg1 = updateBlockMsg(indexX, sY);
                calculateScore(isUpdatedMsg0, isUpdatedMsg1);
            }
        }
        //开始点和结束点上下相邻，即画的是竖线，则更新左右两个Block
        if ((sX == eX && (sY - eY == 1 || sY - eY == -1))) {
            int indexY = (sY + eY) / 2;
            boolean isUpdatedColor0 = updateBlockColor(sX - 1, indexY, LTRB.RIGHT, mCurrentPlayerColor);
            boolean isUpdatedColor1 = updateBlockColor(sX, indexY, LTRB.LEFT, mCurrentPlayerColor);

            if (isUpdatedColor0 || isUpdatedColor1) {
                boolean isUpdatedMsg0 = updateBlockMsg(sX - 1, indexY);
                boolean isUpdatedMsg1 = updateBlockMsg(sX, indexY);
                calculateScore(isUpdatedMsg0, isUpdatedMsg1);
            }
        }
    }

    /**
     * 计算分数
     */
    private void calculateScore(boolean isUpdatedMsg0, boolean isUpdatedMsg1) {
        if (isUpdatedMsg0 || isUpdatedMsg1) {
            int index = mPlayerColorArrayIndex;
            int score = mPlayerScoreList.get(index);
            if (isUpdatedMsg0) {
                score++;
            }
            if (isUpdatedMsg1) {
                score++;
            }
            mPlayerScoreList.set(index, score);
        } else {
            mCurrentPlayerColor = nextColor();
        }
    }

    private boolean updateBlockColor(int indexX, int indexY, LTRB ltrb, int color) {
        if (mBlockArray == null) {
            if (DEBUG) {
                throw new IllegalArgumentException("param error!");
            }
            return false;
        }
        if (indexX < 0 || indexX >= mBlockCountX || indexY < 0 || indexY >= mBlockCountY) {
            return false;
        }
        Block block = mBlockArray[indexY][indexX];

        return block.updateColor(ltrb, color);
    }

    /**
     * @param indexX indexX
     * @param indexY indexY
     * @return true 被当前玩家占领;false 当前方块之前就已被占领
     */
    private boolean updateBlockMsg(int indexX, int indexY) {
        if (mBlockArray == null) {
            if (DEBUG) {
                throw new IllegalArgumentException("param error!");
            }
            return false;
        }
        if (indexX < 0 || indexX >= mBlockCountX || indexY < 0 || indexY >= mBlockCountY) {
            return false;
        }
        Block block = mBlockArray[indexY][indexX];

        if (block.isFull() && !block.isDefaultColor()) {
            //此方格被当前玩家占领，设置为当前玩家id
            block.setBlockColor(mCurrentPlayerColor);
            return true;
        }
        return false;
    }

    private int nextColor() {
        mPlayerColorArrayIndex = (mPlayerColorArrayIndex + 1) % mPlayerColorArray.length;
        return mPlayerColorArray[mPlayerColorArrayIndex];
    }


    public void setupPointByMotionEvent(LineCirclePoint point, MotionEvent event) {
        if (point == null || event == null) {
            if (DEBUG) {
                throw new IllegalArgumentException("param error!");
            }
            return;
        }
        for (int x = 0; x <= mBlockCountX; x++) {
            for (int y = 0; y <= mBlockCountY; y++) {
                RectF rectF = mCornerOfBlockArray[y][x].getCollideRectF();
                if (event.getX() >= rectF.left && event.getX() <= rectF.right
                        && event.getY() >= rectF.top && event.getY() <= rectF.bottom) {
                    point.setIndex(x, y);
                    point.set(rectF.centerX(), rectF.centerY());
                    return;
                }
            }
        }
        point.set(event.getX(), event.getY());
        point.resetIndex();
    }

}
