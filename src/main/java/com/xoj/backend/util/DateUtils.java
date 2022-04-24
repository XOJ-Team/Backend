package com.xoj.backend.util;

import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date string2Date(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = dateFormat.parse(time);
            return date;
        } catch (ParseException e) {
            throw new BizException(CommonErrorType.PARSE_ERROR);
        }
    }

    public static String date2String(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.format(date);
        } catch (Exception e) {
            throw new BizException(CommonErrorType.PARSE_ERROR);
        }
    }

}
