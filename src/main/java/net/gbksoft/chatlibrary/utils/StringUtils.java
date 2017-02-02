package net.gbksoft.chatlibrary.utils;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 *
 * Provides useful methods with strings
 */

public class StringUtils {

    /**
     * Return String from array
     *
     * @param list ArrayList
     */
    public static String getStringFromArray(ArrayList list) {
        return getStringFromArray(list, true);
    }

    /**
     * Return String from array
     *
     * @param list ArrayList
     * @param isFromNewLineNeed start each Array item on a new line
     */
    public static String getStringFromArray(ArrayList list, boolean isFromNewLineNeed) {
        StringBuilder builder = new StringBuilder();
        if (list != null) {
            if (list.size() == 1) {
                isFromNewLineNeed = false;
            }

            for (Object listItem : list) {
                if (isFromNewLineNeed) {
                    builder.append("\n  ");
                }
                builder.append("{").append(listItem).append("},");
            }
        }

        String builderString = builder.toString();
        if (!TextUtils.isEmpty(builderString)) {
            return builderString.substring(0, builderString.length() - 1);
        }

        return builderString;
    }
}