package com.hfbh.yuecheng.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

import com.smarttop.library.utils.LogUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Libin on 2018/7/17 10:01
 * Email：1993911441@qq.com
 * Describe：
 */
public class MoneyInputFilter implements InputFilter {

    //输入的最大金额
    private double maxValue;


    public MoneyInputFilter(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        String sourceText = source.toString();
        String destText = dest.toString();
        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            if (dstart == 0 && destText.indexOf(".") == 1) {//保证小数点不在第一个位置
                return "0";
            }
            return "";
        }

        Pattern pattern = Pattern.compile("([0-9]|\\.)*");
        Matcher matcher = pattern.matcher(source);
        //已经输入小数点的情况下，只能输入数字
        if (destText.contains(".")) {
            if (!matcher.matches()) {
                return "";
            } else {
                if (".".equals(source)) { //只能输入一个小数点
                    return "";
                }
            }
            //验证小数点精度，保证小数点后只能输入两位
            int index = destText.indexOf(".");
            int length = destText.trim().length() - index;
            if (length > 2 && dstart > index) {
                return "";
            }
        } else {
            //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
            if (!matcher.matches()) {
                return "";
            } else {
                if ((".".equals(source)) && dstart == 0) {//第一个位置输入小数点的情况
                    return "0.";
                }
            }
        }
        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + sourceText);
        if (sumText > maxValue) {
            return dest.subSequence(dstart, dend);
        }

        return dest.subSequence(dstart, dend) + sourceText;
    }
}
