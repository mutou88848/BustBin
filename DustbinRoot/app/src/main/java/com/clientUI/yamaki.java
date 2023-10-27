//package com.clientUI;
//
//import net.tsz.afinal.http.AjaxParams;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.client.R;
//import com.clientBase.base.BaseActivity;
//import com.clientBase.config.Consts;
//import com.clientBase.model.ResponseEntry;
//import com.clientBase.util.ToastUtil;
//
//
///**
// * 用户注册
// */
//public class yamaki extends BaseActivity {
//    // 标题
//    private TextView mTvTitle;
//    // 返回
//    private ImageView mIvBack;
//    //注册
//    private Button mSubmit;
//
//    private TextView CodeInfor,loglag;
//    private EditText yamaki,yamaki1;
//    private Button mE;
//
//    private EditText metName;
//    private EditText metPhone;
//    private EditText metPswd;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_creat_register);
//        initWidget();
//        initData();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.mIvBack:
//                finish();
//                break;
//            case R.id.mSubmit:
//                if (TextUtils.isEmpty(yamaki.getText().toString())) {
//                    ToastUtil.ShowCentre(this, "请输入状态");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(yamaki1.getText().toString())) {
//                    ToastUtil.ShowCentre(this, "请输入地点");
//                    return;
//                }
//
//
//                if (TextUtils.isEmpty(CodeInfor.getText().toString())) {
//                    return;
//                }
//
//                if (TextUtils.isEmpty(loglag.getText().toString())) {
//                    return;
//                }
//
//                createTopicPost(true);
//
//                break;
//
//        }
//    }
//
//    @Override
//    public void initWidget() {
//
//
//        yamaki= (EditText) findViewById(R.id.yamaki);
//        yamaki1 = (EditText) findViewById(R.id.yamaki1);
//        mSubmit = (Button) findViewById(R.id.mE);
//        CodeInfor = (TextView) findViewById(R.id.CodeInfor);
//        loglag = (TextView) findViewById(R.id.loglat);
//        mTvTitle.setText("更新数据库");
//        mIvBack.setVisibility(View.VISIBLE);
//        mIvBack.setOnClickListener(this);
//        mE.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void initData() {
//
//
//    }
//
//
//    /**
//     * 添加注册的参数信息
//     *
//     * @param isShow
//     */
//    private void createTopicPost(boolean isShow) {
//
//        AjaxParams params = new AjaxParams();
//        params.put("action_flag", "addUser");
//        params.put("yamaki", yamaki.getText().toString());
//        params.put("yamaki1", yamaki1.getText().toString());
//        params.put("uphone", metPhone.getText().toString());
//        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在更新...");
//
//    }
//
//    /**
//     * 注册成功的数据回调
//     *
//     * @param entry
//     * @param actionId
//     */
//    @Override
//    protected void callBackSuccess(ResponseEntry entry, int actionId) {
//        super.callBackSuccess(entry, actionId);
//        ToastUtil.show(yamaki.this, entry.getRepMsg());
//        new Handler().postDelayed(new Runnable() {
//            //
//            @Override
//            public void run() {
//                finish();
//            }
//        }, 2000);
//    }
//
//    /**
//     * 注册失败的数据回调
//     *
//     * @param actionId
//     */
//    @Override
//    protected void callBackAllFailure(String strMsg, int actionId) {
//        super.callBackAllFailure(strMsg, actionId);
//        ToastUtil.show(yamaki.this, strMsg);
//
//    }
//}
