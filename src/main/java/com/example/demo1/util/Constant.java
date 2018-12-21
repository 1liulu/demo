package com.example.demo1.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Constant {



    //token生成异常
    public final static String GENERATE_TOKEN_ERROR = "-1";
    /**
     * status状态
     */
    public static final int STATUS_VALID = 1;//有效
    public static final int STATUS_INVALID = 0;//无效
    /**
     * 性别
     */
    public static final int SEX_MAN = 0;
    public static final int SEX_WOMAN = 1;


    /**
     * 学习资料训练指导
     */
    public static final int GUIDANCE = 40;
    /**
     * 学习知识
     */
    public static final int KNOWLEDGE = 41;
    /**
     * 训练记录
     */
    public static final int RECORD = 42;

    /**
     * 请求响应状态
     */
    public static final String RESPONSE_STATE_SUCCESS = "0";
    public static final String RESPONSE_STATE_FAILURE = "1";

    /**
     * 日期时间格式
     *
     * @author Muscleape
     * @date 2018/8/29 11:57
     * @return
     */
    public static final String DATETIME_SHORT = "yyyy-MM-dd";
    public static final String DATETIME_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_CH_SHORT = "yyyy年MM月dd日";
    public static final String DATETIME_CH_LONG = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 文件类型
     *
     * @author Muscleape
     * @date 2018/9/8 11:13
     * @return
     */
    public static final String FILETYPE_PHOTO = "0";
    public static final String FILETYPE_VIDEO = "1";

    /**
     * stream去重强化方法
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
