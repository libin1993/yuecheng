package com.hfbh.yuecheng.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author：Libin on 2018/5/14 17:25
 * Email：1993911441@qq.com
 * Describe：Gson工具类
 */
public class GsonUtils {
    private static Gson gson;

    static {
        gson = new GsonBuilder().serializeNulls().create();
    }

    private GsonUtils() {

    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String jsonToString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成json
     *
     * @return
     */
    public static String mapToJson(Map<String, String> map) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(map);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */

    public static <T> List<T> jsonToArray(String json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        if (gson != null) {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }

}
