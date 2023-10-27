package com.clientUI;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.dustbin.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.StationModel;
import com.clientBase.model.UserModel;
import com.clientBase.util.CustomToast;
import com.clientBase.util.ToastUtil;

import net.tsz.afinal.http.AjaxParams;


public class PayMessageActivity extends BaseActivity  {

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private TextView mtvPrice;
    private Button mPay;
    private int choiceType = 1;
    private RadioGroup mrdChoice;
    private RadioButton mrbWeiXin = null;
    private RadioButton mrbPay = null;

    private TextView BinNO;

    StationModel stationModel;

    private TextView startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_message);
        initWidget();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.mPay:

                UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
//				if (Integer.valueOf(userModel.getUserMoney())<((timeMgs+1)*Integer.valueOf(stationModel.getStationMoney()))) {
////					ToastUtil.ShowCentre(this, "您的余额不足，请充值！");
//					CustomToast.showToast(this,"您的余额不足，请充值！");
//					new Handler().postDelayed(new Runnable() {
//						@Override
//						public void run() {
//							Intent mEnterpriseQuery = new Intent(PayMessageActivity.this, UpdateNameActivity.class);
//							startActivity(mEnterpriseQuery);
//						}
//					}, 2000);
//					return;
//				}


                OrderAction(true);
                break;
        }
    }



    @Override
    public void initWidget() {




        mrdChoice = (RadioGroup) findViewById(R.id.mrdChoice);
        mrbWeiXin = (RadioButton) findViewById(R.id.mrbWeiXin);
        mrbPay = (RadioButton) findViewById(R.id.mrbPay);
        BinNO = (TextView) findViewById(R.id.BinNO);
        mrdChoice = (RadioGroup) findViewById(R.id.mrdChoice);
        mrbWeiXin = (RadioButton) findViewById(R.id.mrbWeiXin);
        mrbPay = (RadioButton) findViewById(R.id.mrbPay);
        mPay = (Button) findViewById(R.id.mPay);
        mtvPrice = (TextView) findViewById(R.id.mtvPrice);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("支付确认");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mPay.setOnClickListener(this);


        startTime = (TextView) findViewById(R.id.startTime);

    }

    int timeMgs;
    String latLists;
    @Override
    public void initData() {

        stationModel = (StationModel) this.getIntent().getSerializableExtra("msg");
        latLists = this.getIntent().getStringExtra("options");

        timeMgs = ((Integer.valueOf(this.getIntent().getStringExtra("longTime"))) / 3600);
        startTime.setText(this.getIntent().getStringExtra("TimeInfor")+"");

		mtvPrice.setText(((timeMgs+1)*1)+"元");

        BinNO.setText("点击了解详细信息");


        String startAddress  = this.getIntent().getStringExtra("startAddress");
        String endAddress  = this.getIntent().getStringExtra("endAddress");
        Log.i("pony_log",startAddress);
        Log.i("pony_log",endAddress);

        mrdChoice.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mrbWeiXin.getId()) {
                    choiceType = 1;
                } else if (checkedId == mrbPay.getId()) {
                    choiceType = 2;
                }
            }
        });

    }

    /**
     * 订单的添加
     *
     * @param isShow
     */
    private void OrderAction(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addOrder");
        params.put("orderMessageId", stationModel.getStationId() + "");

        params.put("orderMessageName", stationModel.getstationTitle());
        params.put("orderMessageName", stationModel.getfullEmpty());
        params.put("orderMessageName", stationModel.getactionDump());
        params.put("orderMessageName", stationModel.gettype());
        params.put("orderMessageName", stationModel.getstatus());
		params.put("orderMoney",((timeMgs+1)*1)+"");
        params.put("orderLatLng",latLists);
        params.put("orderUserId", MemberUserUtils.getUid(this));
        params.put("orderUserName", MemberUserUtils.getName(this));
        params.put("orderTimeLong", this.getIntent().getStringExtra("TimeInfor"));

        params.put("orderAddressStart", this.getIntent().getStringExtra("startAddress"));
        params.put("orderAddressEnd", this.getIntent().getStringExtra("endAddress"));




        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在支付...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        CustomToast.showToast(PayMessageActivity.this, entry.getRepMsg() + "，可在订单查看");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PayMessageActivity.this.finish();
            }
        }, 2000);
    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(PayMessageActivity.this, strMsg);
    }


}
