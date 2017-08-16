package com.cpxiao.dotsandboxes.mode.extra;

import com.cpxiao.R;

/**
 * @author cpxiao on 2017/08/16.
 */

public class GameColor {
    public static final int COLOR_CIRCLE = 0xFF666666;//黑色
    public static final int COLOR_LINE_DEFAULT = 0xFFDDDDDD;//浅灰色

    private static final int COLOR_LINE_PLAYER1 = 0xFFE00020;//红色
    private static final int COLOR_LINE_PLAYER2 = 0xFF30ABFC;//蓝色
    private static final int COLOR_LINE_PLAYER3 = 0xFFF2BE28;//黄色
    private static final int COLOR_LINE_PLAYER4 = 0xFF39DF1D;//绿色
    private static final int COLOR_LINE_PLAYER5 = 0xFFC700DE;//紫色

    private static final int[] _2_PLAYER = {COLOR_LINE_PLAYER1, COLOR_LINE_PLAYER2};
    private static final int[] _3_PLAYER = {COLOR_LINE_PLAYER1, COLOR_LINE_PLAYER2, COLOR_LINE_PLAYER3};
    private static final int[] _4_PLAYER = {COLOR_LINE_PLAYER1, COLOR_LINE_PLAYER2, COLOR_LINE_PLAYER3, COLOR_LINE_PLAYER4};
    private static final int[] _5_PLAYER = {COLOR_LINE_PLAYER1, COLOR_LINE_PLAYER2, COLOR_LINE_PLAYER3, COLOR_LINE_PLAYER4, COLOR_LINE_PLAYER5};
    private static final int[] _5_PLAYER_MSG = {R.string.player1, R.string.player2, R.string.player3, R.string.player4, R.string.player5};

    public static int[] getPlayerColorArray(int playerCount) {
        if (playerCount == 2) {
            return _2_PLAYER;
        } else if (playerCount == 3) {
            return _3_PLAYER;
        } else if (playerCount == 4) {
            return _4_PLAYER;
        } else if (playerCount == 5) {
            return _5_PLAYER;
        }
        return _2_PLAYER;
    }

    /**
     * 获取Title中显示的文字
     *
     * @param playerIndex index
     * @return String id
     */
    public static int getTitleMsg(int playerIndex) {
        return _5_PLAYER_MSG[playerIndex % _5_PLAYER_MSG.length];
    }
}
