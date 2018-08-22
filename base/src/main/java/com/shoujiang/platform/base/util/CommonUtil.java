package com.shoujiang.platform.base.util;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: DataCollector
 * @Package com.shoujiang.platfor.base.util
 * @Description: 工具类
 * @date Date : 2018年08月21日 15:28
 */
public class CommonUtil {

    public static boolean isNull(Object obj){
        if(obj == null){
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否为空
     * @return
     */
    public static boolean isNotNull(Object obj){
        return !isNull(obj);
    }
}
