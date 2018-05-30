package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/30 10:47
 * Email：1993911441@qq.com
 * Describe：修改昵称
 */
public class UpdateNameActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.et_set_nickname)
    EditText etSetNickname;
    @BindView(R.id.tv_update_name)
    TextView tvUpdateName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("设置昵称");

    }


    @OnClick({R.id.iv_header_back, R.id.tv_update_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_update_name:
                updateName();
                break;
        }
    }

    /**
     * 设置昵称
     */
    private void updateName() {
        final String nickName = etSetNickname.getText().toString().trim();
        if (!TextUtils.isEmpty(nickName)) {
            OkHttpUtils.post()
                    .url(Constant.UPDATE_USER_INFO)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                    .addParams("memberNickName", nickName)
                    .addParams("memberId", SharedPreUtils.getStr(this, "member_id"))
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                    if (responseBean.isFlag()) {
                        EventBus.getDefault().post(nickName);
                        finish();
                    } else {
                        ToastUtils.showToast(UpdateNameActivity.this, "设置昵称失败");
                    }
                }
            });
        } else {
            ToastUtils.showToast(this, "请输入昵称");
        }

    }
}
