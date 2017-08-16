package com.cpxiao.dotsandboxes.mode.extra;

import android.text.TextUtils;

import com.cpxiao.androidutils.library.utils.StringUtils;

/**
 * @author cpxiao on 2017/08/16.
 */

public class GridSize {
    /**
     * 方格数量
     */
    public static final String SIZE_KEY = "SIZE_KEY";
    private static final String SIZE_2 = "2 x 2";
    private static final String SIZE_3 = "3 x 3";
    private static final String SIZE_4 = "4 x 4";
    private static final String SIZE_5 = "5 x 5";
    private static final String SIZE_6 = "6 x 6";
    private static final String[] SIZE_ARRAY = {SIZE_2, SIZE_3, SIZE_4, SIZE_5, SIZE_6};
    public static final String SIZE_DEFAULT = SIZE_3;

    public static int getGridCountX(String size) {
        if (StringUtils.isEmpty(size)) {
            return 0;
        }
        try {
            String x = size.substring(0, size.indexOf("x") - 1);
            return Integer.valueOf(x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int getGridCountY(String size) {
        if (StringUtils.isEmpty(size)) {
            return 0;
        }
        try {
            String x = size.substring(size.indexOf("x") + 2, size.length());
            return Integer.valueOf(x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getNextGridSize(String gridSize) {
        int index = 0;
        for (int i = 0; i < SIZE_ARRAY.length; i++) {
            if (TextUtils.equals(gridSize, SIZE_ARRAY[i])) {
                index = i;
                break;
            }
        }
        return SIZE_ARRAY[(index + 1) % SIZE_ARRAY.length];
    }
}
