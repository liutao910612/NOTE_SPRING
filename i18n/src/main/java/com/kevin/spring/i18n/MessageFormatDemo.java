package com.kevin.spring.i18n;

import com.kevin.base.utils.PrintUtil;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} demo
 *
 * @Author:Kevin
 * @Date:Created in 9:58 2021/1/3
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String messageFormatPattern = "At {1,time,long} on {1,date,full}" +
                ", there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(messageFormatPattern);
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        PrintUtil.print(result);

        //重置MessageFormatPattern
        //applyPattern
        messageFormatPattern = "This is a text:{0}";
        messageFormat.applyPattern(messageFormatPattern);
        result = messageFormat.format(new Object[]{"Hello, World"});
        PrintUtil.print(result);

        //重置Locale
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormatPattern = "At {1,time,long} on {1,date,full}" +
                ", there was {2} on planet {0,number,integer}.";
        messageFormat.applyPattern(messageFormatPattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        PrintUtil.print(result);

        //重置Format
        //根据参数索引来设置Pattern
        messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        PrintUtil.print(result);
    }
}
