package com.chao.ssmvue.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.UUID;

public class StringUtil extends StringUtils {

    /**
     * 将Long数组进行逗号分隔并返回
     */
    public static String getSplitCommaLong(Long[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Long id : ids) {
            if (stringBuilder.length() > 0)
                stringBuilder.append("," + id);
            else
                stringBuilder.append(id);
        }
        return stringBuilder.toString();
    }

    /**
     * 将String数组进行逗号分隔并返回
     */
    public static String getSplitCommaString(String[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String id : ids) {
            if (stringBuilder.length() > 0)
                stringBuilder.append("," + "'" + id + "'");
            else
                stringBuilder.append("'" + id + "'");
        }
        return stringBuilder.toString();
    }

    public static Long[] stringArrayToLongArray(String[] original) {
        Assert.notEmpty(original, "数组不能为空");
        Long[] longArr = new Long[original.length];

        int i = 0;
        for (String s : original) {
            longArr[i] = Long.valueOf(s);
            i++;
        }
        return longArr;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
