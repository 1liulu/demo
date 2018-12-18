package com.example.demo1.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Constant {


    public final static String ZERO = "0";


    public final static String Redis_ScoreSumary = "Redis_ScoreSumary";

    public final static String SCORE_WEEK = "week";

    public final static String SCORE_MONTH = "month";

    public final static String SCORE_QUARTER = "quarter";



    public final static String ABNORMAL_MONTH = "abnormal_month";

    public final static String ABNORMAL_TYPE_MONTH = "abnormal_type_month";

    public final static String HEARTRATE_MONTH = "heartrate_month";










    public final static String SYS_TRAINID = "999";


    public  final  static String SEX_MAN ="1";

    public final static Integer TimeType = 2;

    public final static String GENERATE_TOKEN_ERROR = "-1";

    // xf_person.position:官兵职位-指挥官
    public final static String PERSON_POSITION_COMMANDER = "766";

    // xf_person.status:官兵状态-备勤
    public final static String PERSON_STATUS_RELIEF = "566";

    // xf_clxx.clzt:车辆状态-备勤
    public final static String CLXX_CLZT_RELIEF = "800";

    // 组织类型
    //总队
    public static final int ZONGDUI = 1;
    //支队
    public static final int ZHIDUI = 2;
    //大队
    public static final int DADUI = 3;
    //中队
    public static final int ZHONGDUI = 4;

    //状态值
    public static final String STATUS_ANLE = "0";
    public static final String STATUS_ENANLE = "1";

    //今日警情
    //选择日期
    public static final String TYPE_DATE = "2";
    //本周
    public static final String TYPE_WEEK = "week";
    //本月
    public static final String TYPE_MONTH = "month";
    //本年
    public static final String TYPE_YEAR = "year";
    //山东省编码
    public static final String SD_CODE = "370000.";
    //总队的orgid
    public static final String ORG_ID = "1";

    //山东省区域代码
    public static final String SHANDONG_CODE = "370000";
    //地图等级
    public static final String SECOND_LEVEL = "2";
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
