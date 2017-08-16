package com.cpxiao.dotsandboxes.mode.extra;

/**
 * @author cpxiao on 2017/06/11.
 */

public final class Extra {

//    public static final class GridSize {
//        /**
//         * 方格数量
//         */
//        public static final String SIZE_KEY = "SIZE_KEY";
//        private static final String SIZE_3 = "3 x 3";
//        private static final String SIZE_4 = "4 x 4";
//        private static final String SIZE_5 = "5 x 5";
//        private static final String SIZE_6 = "6 x 6";
//        private static final String[] SIZE_ARRAY = {SIZE_3, SIZE_4, SIZE_5, SIZE_6};
//        public static final String SIZE_DEFAULT = SIZE_4;
//
//        public static int getGridCountX(String size) {
//            if (StringUtils.isEmpty(size)) {
//                return 0;
//            }
//            try {
//                String x = size.substring(0, size.indexOf("x") - 1);
//                return Integer.valueOf(x);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return 0;
//        }
//
//        public static int getGridCountY(String size) {
//            if (StringUtils.isEmpty(size)) {
//                return 0;
//            }
//            try {
//                String x = size.substring(size.indexOf("x") + 2, size.length());
//                return Integer.valueOf(x);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return 0;
//        }
//
//        public static String getNextGridSize(String gridSize) {
//            int index = 0;
//            for (int i = 0; i < SIZE_ARRAY.length; i++) {
//                if (TextUtils.equals(gridSize, SIZE_ARRAY[i])) {
//                    index = i;
//                    break;
//                }
//            }
//            return SIZE_ARRAY[(index + 1) % SIZE_ARRAY.length];
//        }
//    }

    public static final class Key {
        /**
         * Casual Game Best Score
         */
        public static final String CASUAL_GAME_BEST_SCORE = "CASUAL_GAME_BEST_SCORE";
        public static final int CASUAL_GAME_BEST_SCORE_DEFAULT = 999;

        /**
         * 是否有圆角
         */
        public static final String IS_ROUND = "IS_ROUND";
        public static final boolean IS_ROUND_DEFAULT = true;


        /**
         * 音效开关，默认开
         */
        public static final String SETTING_SOUND = "SETTING_SOUND";
        public static final boolean SETTING_SOUND_DEFAULT = true;

        /**
         * 音乐开关，默认开
         */
        public static final String SETTING_MUSIC = "SETTING_MUSIC";
        public static final boolean SETTING_MUSIC_DEFAULT = true;


        /**
         * 边界，即游戏区域方格之间是否需要Padding
         */
        public static final String SETTING_HAS_BORDERS = "SETTING_HAS_BORDERS";
        public static final boolean SETTING_HAS_BORDERS_DEFAULT = false;

    }

    public class Name {
        public static final String BLOCK_COUNT_X = "BLOCK_COUNT_X";
        public static final int BLOCK_COUNT_X_DEFAULT = 4;

        public static final String BLOCK_COUNT_Y = "BLOCK_COUNT_Y";
        public static final int BLOCK_COUNT_Y_DEFAULT = 2;

        public static final String PLAYER_COUNT = "PLAYER_COUNT";
        public static final int PLAYER_COUNT_DEFAULT = 2;
    }
}
