package com.hfbh.yuecheng.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.EnrollActivityBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;
import com.hfbh.yuecheng.utils.SerializableMap;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.hfbh.yuecheng.view.PermissionDialog;
import com.smarttop.library.utils.LogUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：Libin on 2018/6/7 09:05
 * Email：1993911441@qq.com
 * Describe：活动报名
 */
public class EnrollActionActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;

    @BindView(R.id.tv_enroll_score)
    TextView tvEnrollScore;
    @BindView(R.id.tv_enroll_type)
    TextView tvEnrollType;
    @BindView(R.id.tv_enroll_activity)
    TextView tvEnrollActivity;
    @BindView(R.id.rv_enroll_activity)
    RecyclerView rvActivity;
    //活动id
    private int activityId;
    private EnrollActivityBean activityBean;
    private List<EnrollActivityBean.DataBean.OptionListBean> dataList = new ArrayList<>();
    private EditText etUsername;
    private EditText etPhone;
    private Map<String, String> map = new HashMap<>();

    private ArrayList<String> photoList = new ArrayList<>();

    private String[] permissionStr = {Manifest.permission.CAMERA};

    private int picPosition;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    //必填项数量
    private int totalNum;
    //已填数量
    private SparseArray<Boolean> inputNum = new SparseArray<>();
    //报名类型
    private String type;
    //活动报名id
    private int enrollId;
    //是否报名成功
    private boolean isEnroll;
    private boolean enrollSuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_action);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvHeaderTitle.setText("活动报名");
        getData();
        initData();

    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.ENROLL_ACTIVITY_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("id", String.valueOf(activityId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        activityBean = GsonUtils.jsonToBean(response, EnrollActivityBean.class);
                        if (activityBean.isFlag()) {
                            enrollId = activityBean.getData().getSignupId();
                            if (activityBean.getData().getOptionList() != null && activityBean
                                    .getData().getOptionList().size() > 0) {

                                for (int i = 0; i < activityBean.getData().getOptionList().size(); i++) {
                                    dataList.add(activityBean.getData().getOptionList().get(i));
                                    if (!TextUtils.isEmpty(activityBean.getData().getOptionList()
                                            .get(i).getRequired()) && activityBean.getData()
                                            .getOptionList().get(i).getRequired().equals("true")) {
                                        totalNum++;
                                    }
                                }

                            }
                            initView();
                        } else if (activityBean.getCode() == 4002) {
                            SharedPreUtils.deleteStr(EnrollActionActivity.this, "is_login");
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvActivity.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<EnrollActivityBean.DataBean.OptionListBean> adapter = new CommonAdapter
                <EnrollActivityBean.DataBean.OptionListBean>(
                EnrollActionActivity.this, R.layout.rv_enroll_activity_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, final EnrollActivityBean.DataBean.OptionListBean
                    optionListBean, final int position) {
                holder.setText(R.id.tv_enroll_activity_title, optionListBean.getTitle());
                EditText etValue = holder.getView(R.id.et_enroll_activity_value);
                TextView tvValue = holder.getView(R.id.tv_enroll_activity_value);
                TextView tvNeed = holder.getView(R.id.tv_activity_need);
                RadioGroup rgsValue = holder.getView(R.id.rgs_activity);

                if (!TextUtils.isEmpty(optionListBean.getRequired()) && optionListBean.getRequired().equals("true")) {
                    tvNeed.setVisibility(View.VISIBLE);
                } else {
                    tvNeed.setVisibility(View.INVISIBLE);
                }


                switch (optionListBean.getType()) {
                    case "RADIO":
                        etValue.setVisibility(View.GONE);
                        rgsValue.setVisibility(View.VISIBLE);
                        tvValue.setVisibility(View.GONE);
                        rgsValue.removeAllViews();
                        addView(rgsValue, optionListBean, position - 1);
                        break;
                    case "UPLOAD_IMAGE":
                        etValue.setVisibility(View.GONE);
                        rgsValue.setVisibility(View.GONE);
                        tvValue.setVisibility(View.VISIBLE);
                        if (optionListBean.isFinish()) {
                            tvValue.setText("√");
                        } else {
                            tvValue.setText("+");
                        }

                        tvValue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!EasyPermissions.hasPermissions(EnrollActionActivity.this, permissionStr)) {
                                    EasyPermissions.requestPermissions(EnrollActionActivity.this, "", 123, permissionStr);
                                } else {

                                    picPosition = position - 1;
                                    PhotoPicker.builder()
                                            .setPhotoCount(3)
                                            .setShowCamera(true)
                                            .setSelected(photoList)
                                            .start(EnrollActionActivity.this);
                                }
                            }
                        });
                        break;

                    case "SINGLE":
                    default:
                        etValue.setVisibility(View.VISIBLE);
                        etValue.setHint("请输入" + optionListBean.getTitle());
                        rgsValue.setVisibility(View.GONE);
                        tvValue.setVisibility(View.GONE);
                        etValue.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if (!TextUtils.isEmpty(s.toString().trim())) {
                                    map.put(optionListBean.getTitle(), s.toString().trim());
                                    map.put(optionListBean.getTitle() + "id", String.valueOf(optionListBean.getId()));
                                    if (!TextUtils.isEmpty(optionListBean.getRequired()) &&
                                            optionListBean.getRequired().equals("true")) {
                                        inputNum.put(position - 1, true);
                                    }

                                } else {
                                    if (map.containsKey(optionListBean.getTitle())) {
                                        map.remove(optionListBean.getTitle());
                                        map.remove(optionListBean.getTitle() + "id");
                                        if (!TextUtils.isEmpty(optionListBean.getRequired()) &&
                                                optionListBean.getRequired().equals("true")) {
                                            inputNum.put(position - 1, false);
                                        }
                                    }
                                }
                            }
                        });
                        break;
                }
            }
        };
        headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(initHeader());
        rvActivity.setAdapter(headerAndFooterWrapper);

    }

    /**
     * @param rgsValue
     * @param optionListBean RadioGroup动态添加
     */
    private void addView(final RadioGroup rgsValue, final EnrollActivityBean.DataBean.OptionListBean optionListBean, final int position) {
        for (int i = 0; i < optionListBean.getItemList().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(50, 0, 0, 0);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setButtonDrawable(getResources().getDrawable(R.drawable.cb_register));
            radioButton.setText(optionListBean.getItemList().get(i).getTitle());
            radioButton.setTextColor(getResources().getColor(R.color.gray_10));
            radioButton.setTextSize(13);
            rgsValue.addView(radioButton);
        }

        rgsValue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!TextUtils.isEmpty(optionListBean.getRequired()) &&
                        optionListBean.getRequired().equals("true")) {
                    inputNum.put(position, true);
                }

                for (int i = 0; i < rgsValue.getChildCount(); i++) {
                    if (rgsValue.getChildAt(i).getId() == checkedId) {
                        map.put(optionListBean.getTitle(), optionListBean.getItemList().get(i).getTitle());
                        map.put(optionListBean.getTitle() + "id", String.valueOf(optionListBean.getId()));
                    }
                }
            }
        });
    }

    /**
     * 店铺信息
     */
    private View initHeader() {

        View view = LayoutInflater.from(this).inflate(R.layout.rv_activity_header, null);
        TextView tvActionName = (TextView) view.findViewById(R.id.tv_action_name);
        TextView tvActionTime = (TextView) view.findViewById(R.id.tv_action_time);
        TextView tvActionAddress = (TextView) view.findViewById(R.id.tv_action_address);
        FlowLayout flowLayout = (FlowLayout) view.findViewById(R.id.flow_layout_activity);
        etUsername = (EditText) view.findViewById(R.id.et_action_username);
        etPhone = (EditText) view.findViewById(R.id.et_action_phone);

        etPhone.setText(SharedPreUtils.getStr(EnrollActionActivity.this, "phone"));
        tvActionName.setText(activityBean.getData().getSignupActivity().getActivityTitle());
        if (activityBean.getData().getSignupActivity().getTags() != null &&
                activityBean.getData().getSignupActivity().getTags().size() > 0) {
            addTextView(flowLayout, activityBean.getData().getSignupActivity().getTags());
        }

        tvActionTime.setText(activityBean.getData().getSignupActivity().getActivityStarttime() + " - "
                + activityBean.getData().getSignupActivity().getActivityEndtime());
        tvActionAddress.setText(activityBean.getData().getSignupActivity().getAcivityAddress());

        type = activityBean.getData().getSignupActivity().getAcivityType();
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case "NONEED":
                    tvEnrollScore.setText("无需报名");
                    break;
                case "FREE":
                    tvEnrollScore.setText("免费");
                    break;
                case "SCORE":
                    tvEnrollScore.setText(DisplayUtils.isInteger(activityBean.getData().getSignupActivity()
                            .getEnrollScore()));
                    tvEnrollType.setVisibility(View.VISIBLE);
                    break;
                case "CASH":
                    tvEnrollScore.setText("¥" + DisplayUtils.isInteger(activityBean.getData()
                            .getSignupActivity().getEnrollFee()));
                    break;
            }
        }

        return view;
    }

    /**
     * 动态添加标签
     */
    private void addTextView(FlowLayout flowLayout, List<EnrollActivityBean.DataBean
            .SignupActivityBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup
                    .MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(this, 6),
                    (int) DisplayUtils.dp2px(this, 2));
            tvChild.setLayoutParams(params);
            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
            tvChild.setText(tagsBeans.get(i).getTagName());
            tvChild.setTextSize(12);
            tvChild.setTextColor(getResources().getColor(R.color.red_e6));

            flowLayout.addView(tvChild);
        }
    }

    private void getData() {
        activityId = getIntent().getIntExtra("activity_id", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_enroll_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_enroll_activity:
                if (isEnroll) {
                    Intent intent = new Intent(this, EnrollInfoActivity.class);
                    intent.putExtra("enroll_id", enrollId);
                    startActivity(intent);
                } else {
                    enrollActivity();
                }
                break;
        }
    }


    /**
     * 活动报名
     */
    private void enrollActivity() {
        int num = 0;
        if (activityBean != null && activityBean.getData() != null) {
            for (int i = 0; i < inputNum.size(); i++) {
                if (inputNum.get(i)) {
                    num++;
                }
            }
        }

        if (etUsername != null && etPhone != null && !TextUtils.isEmpty(etUsername.getText().toString().trim())
                && !TextUtils.isEmpty(etPhone.getText().toString().trim()) && num >= totalNum) {
            if (PhoneNumberUtils.judgePhoneNumber(etPhone.getText().toString().trim())) {
                final Map<String, String> paramMap = new HashMap<>();
                paramMap.put("appType", MyApp.appType);
                paramMap.put("appVersion", MyApp.appVersion);
                paramMap.put("organizeId", MyApp.organizeId);
                paramMap.put("hash", SharedPreUtils.getStr(this, "hash"));
                paramMap.put("token", SharedPreUtils.getStr(this, "token"));
                paramMap.put("realname", etUsername.getText().toString().trim());
                paramMap.put("mobile", etPhone.getText().toString().trim());
                paramMap.put("id", String.valueOf(activityId));

                if (map.size() > 0) {
                    StringBuilder info = new StringBuilder("{");
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        info.append("\"").append(entry.getKey()).append("\"=\"").append(entry.getValue()).append("\";");
                    }
                    paramMap.put("appData", info.toString().substring(0, info.length() - 1) + "}\"");
                }

//            if (map.size() > 0) {
//                String info = GsonUtils.mapToJson(map).replaceAll(":", "=");
//                info = info.replaceAll(",", ";");
//                paramMap.put("appData", info);
//            }
                //现金报名
                if (!TextUtils.isEmpty(type) && type.equals("CASH") && activityBean.getData()
                        .getSignupActivity().getEnrollFee() > 0 && enrollId > 0) {
                    cashEnroll();
                } else {
                    OkHttpUtils.post()
                            .url(Constant.POINTS_ENROLL_ACTIVITY)
                            .params(paramMap)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                }

                                @Override
                                public void onResponse(String response, int id) {

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        boolean flag = jsonObject.getBoolean("flag");
                                        if (flag) {
                                            JSONObject data = jsonObject.getJSONObject("data");
                                            enrollId = data.getInt("data");
                                            if (!TextUtils.isEmpty(type) && type.equals("CASH") && activityBean.getData()
                                                    .getSignupActivity().getEnrollFee() > 0) {
                                                cashEnroll();
                                            } else {
                                                enrollResult(true, "活动入场码已放置于“我的-活动”，记得到场参加活动哦！");
                                                tvEnrollActivity.setText("已报名，查看报名信息");
                                                isEnroll = true;
                                            }

                                        } else {
                                            String msg = jsonObject.getString("msg");
                                            enrollResult(false, msg);

                                            if (jsonObject.getInt("code") == 4002) {
                                                SharedPreUtils.deleteStr(EnrollActionActivity.this, "is_login");
                                            }
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }


            } else {
                ToastUtils.showToast(this, "手机号输入格式有误");
            }

        } else {
            ToastUtils.showToast(this, "请完善报名信息");
        }

    }

    /**
     * 现金报名
     */
    private void cashEnroll() {
        Intent intent = new Intent(EnrollActionActivity.this, ConfirmEnrollActivity.class);
        intent.putExtra("activity_id", activityId);
        intent.putExtra("enroll_id", enrollId);
        startActivity(intent);
    }

    /**
     * 兑换结果
     */
    private void enrollResult(final boolean flag, String msg) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_exchange_success, null);
        int widthPixels = DisplayUtils.getMetrics(this).widthPixels;
        final PopupWindow mPopupWindow = new PopupWindow(contentView, (int) (widthPixels
                - DisplayUtils.dp2px(this, 66)), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivResult = (ImageView) contentView.findViewById(R.id.iv_exchange_result);
        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_exchange_cancel);
        TextView tvResult = (TextView) contentView.findViewById(R.id.tv_exchange_result);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.tv_exchange_reason);
        final TextView tvSuccess = (TextView) contentView.findViewById(R.id.tv_exchange_success);
        if (flag) {
            ivResult.setImageResource(R.mipmap.img_success);
            tvResult.setText("报名成功");
            tvSuccess.setText("去查看");
            tvSuccess.setBackgroundResource(R.drawable.bound_gradient_green);
        } else {
            ivResult.setImageResource(R.mipmap.img_failure);
            tvResult.setText("报名失败");
            tvSuccess.setText("返回");
            tvSuccess.setBackgroundResource(R.drawable.bound_gradient_yellow);
        }
        tvMsg.setText(msg);


        tvSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    startActivity(new Intent(EnrollActionActivity.this, MyActionActivity.class));
                }
                mPopupWindow.dismiss();
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(EnrollActionActivity.this, false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE
                || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            photoList.clear();

            if (photos != null) {
                photoList.addAll(photos);
                uploadImg();
            }
        }
    }


    /**
     * 图片上传
     */
    private void uploadImg() {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (int i = 0; i < photoList.size(); i++) {
            File file = new File(photoList.get(i));
            builder.addFormDataPart("img" + i, file.getName(),
                    RequestBody.create(MediaType.parse("image/png"), file));
        }

        MultipartBody requestBody = builder.build();
        //构建请求
        final Request request = new Request.Builder()
                .url(Constant.UPLOAD_FILE)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ResponseBean responseBean = GsonUtils.jsonToBean(response.body().string(), ResponseBean.class);
                            if (responseBean.isFlag() && !TextUtils.isEmpty(responseBean.getData())) {

                                if (!TextUtils.isEmpty(dataList.get(picPosition).getRequired()) &&
                                        dataList.get(picPosition).getRequired().equals("true")) {
                                    inputNum.put(picPosition, true);
                                }
                                dataList.get(picPosition).setFinish(true);
                                headerAndFooterWrapper.notifyItemChanged(picPosition + 1);
                                map.put(dataList.get(picPosition).getTitle(), responseBean.getData());
                                map.put(dataList.get(picPosition).getTitle() + "id", String.valueOf(dataList.get(picPosition).getId()));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return false;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        PermissionDialog.showPermissionDialog(this, "相机");
    }


    /**
     * @param msg 余额全款支付回调
     */
    @Subscribe
    public void enrollSuccess(String msg) {
        if ("enroll_success".equals(msg)) {
            isEnroll = true;
            enrollSuccess = true;
        }
        if ("pay_order".equals(msg)) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (enrollSuccess) {
            enrollSuccess = false;
            enrollResult(true, "活动入场码已放置于“我的-活动”，记得到场参加活动哦！");
            tvEnrollActivity.setText("已报名,查看报名信息");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
