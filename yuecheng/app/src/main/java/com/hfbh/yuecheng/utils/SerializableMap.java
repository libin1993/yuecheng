package com.hfbh.yuecheng.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Author：Libin on 2018/7/16 16:22
 * Email：1993911441@qq.com
 * Describe：Map序列化
 */
public class SerializableMap implements Serializable {

    private static final long serialVersionUID = 4280895701713315504L;
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

}
