package com.zhilehuo.exceptiondemo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具方法
 *
 * @author kaiqunxiao
 * @date 2018/11/29 下午2:45
 */
public class ExceptionUtils {
        /**
         * 将错误或异常的堆栈信息转换为字符串
         * @param t
         * @return
         */
        public static String getTraceInfo(Throwable t) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter writer = new PrintWriter(stringWriter);
                t.printStackTrace(writer);
                StringBuffer buffer = stringWriter.getBuffer();
                return buffer.toString();
        }
}
