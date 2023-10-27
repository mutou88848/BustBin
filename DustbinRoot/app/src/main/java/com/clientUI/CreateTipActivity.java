package com.clientUI;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.dustbin.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.StationModel;
import com.clientBase.util.CustomToast;
import com.clientBase.util.ToastUtil;

import net.tsz.afinal.http.AjaxParams;



public class CreateTipActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnUpdate;


	private EditText planInfor;

	private StationModel stationModel;
	private int choiceTime= 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_study_guide);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {


		planInfor = (EditText) findViewById(R.id.planInfor);


		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("添加报修信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnUpdate.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.mIvBack:
				CreateTipActivity.this.finish();
				break;
			case R.id.mbtnUpdate:
				addPlan(true);
				break;

		}
	}

	@Override
	public void initData() {
		stationModel = (StationModel)this.getIntent().getSerializableExtra("msg");
		planInfor.setText("垃圾桶机盖打不开，你们安排人员来看看");
	}

	private void addPlan(boolean isShow) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addRepair");
		params.put("repairInfor", planInfor.getText().toString());
		params.put("repairStationId", stationModel.getStationId()+"");
		params.put("repairAddress", stationModel.getStationCoordinate()+"");
		params.put("repairUserId", MemberUserUtils.getUid(this));
		params.put("repairUserName", MemberUserUtils.getName(this));
		httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		CustomToast.showToast(this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				CreateTipActivity.this.finish();
			}
		}, 1000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(CreateTipActivity.this, strMsg);

	}


}
