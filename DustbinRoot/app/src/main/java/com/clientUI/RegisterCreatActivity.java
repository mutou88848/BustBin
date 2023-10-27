package com.clientUI;

import net.tsz.afinal.http.AjaxParams;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dustbin.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.model.ResponseEntry;
import com.clientBase.util.ToastUtil;


/**
 * 用户注册
 */
public class RegisterCreatActivity extends BaseActivity {
    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private Button mSubmit;

    private EditText metName;
    private EditText metPhone;
    private EditText metPswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_register);
        initWidget();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;


            case R.id.mSubmit:

                if (TextUtils.isEmpty(metName.getText().toString())) {
                    ToastUtil.ShowCentre(this, "请输入用户名称");
                    return;
                }


                if (TextUtils.isEmpty(metPhone.getText().toString())) {
                    ToastUtil.ShowCentre(this, "请输入手机号码");
                    return;
                }




                if (TextUtils.isEmpty(metPswd.getText().toString())) {
                    ToastUtil.ShowCentre(this, "请输入登录密码");
                    return;
                }

                createTopicPost(true);

                break;

        }
    }

    @Override
    public void initWidget() {


        metName = (EditText) findViewById(R.id.metName);
        metPhone = (EditText) findViewById(R.id.metPhone);
        metPswd = (EditText) findViewById(R.id.metPswd);

        mSubmit = (Button) findViewById(R.id.mSubmit);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("注册");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

    }

    @Override
    public void initData() {


    }


    /**
     * 添加注册的参数信息
     *
     * @param isShow
     */
    private void createTopicPost(boolean isShow) {

        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addUser");
        params.put("uname", metName.getText().toString());
        params.put("upswd", metPswd.getText().toString());
        params.put("uphone", metPhone.getText().toString());
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在注册...");

    }

    /**
     * 注册成功的数据回调
     *
     * @param entry
     * @param actionId
     */
    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        ToastUtil.show(RegisterCreatActivity.this, entry.getRepMsg());
        new Handler().postDelayed(new Runnable() {
            //
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    /**
     * 注册失败的数据回调
     *
     * @param actionId
     */
    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(RegisterCreatActivity.this, strMsg);

    }
}
