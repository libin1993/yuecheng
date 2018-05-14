package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/5/14 17:37
 * Email：1993911441@qq.com
 * Describe：
 */
public class TabIconBean {
    private int check_img;
    private int normal_img;

    public int getCheck_img() {
        return check_img;
    }

    public TabIconBean(int check_img, int normal_img) {
        this.check_img = check_img;
        this.normal_img = normal_img;
    }

    public void setCheck_img(int check_img) {

        this.check_img = check_img;
    }

    public int getNormal_img() {
        return normal_img;
    }

    public void setNormal_img(int normal_img) {
        this.normal_img = normal_img;
    }
}
