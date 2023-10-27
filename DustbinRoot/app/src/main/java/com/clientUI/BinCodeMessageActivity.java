package com.clientUI;

import android.content.Intent;
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
import com.clientBase.model.StationModel;
import com.clientBase.util.CustomToast;
import com.clientBase.util.ToastUtil;

import net.tsz.afinal.http.AjaxParams;

import java.math.BigDecimal;

public class BinCodeMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	private Button mExit,baoxiuCode,confirm_btn;
	private TextView CodeInfor, loglat;
	//输入框

	private EditText baoa,baob,baoc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bincode_message);
		initWidget();
		initData();
		initListener();
	}

	private int BinFlag=1;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;
			case R.id.mExit:
				BinFlag = 1;
				queryBinInfor(true);
				break;

			case R.id.baoxiuCode:
				BinFlag = 2;
				queryBinInfor(true);
				break;
		}
	}

	@Override
	public void initWidget() {


		CodeInfor = (TextView) findViewById(R.id.CodeInfor);
		mExit = (Button) findViewById(R.id.mExit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		loglat = (TextView) findViewById(R.id.loglat);
		confirm_btn = (Button) findViewById(R.id.confirm_btn);
		baoa = (EditText) findViewById(R.id.baoa);
		baob = (EditText) findViewById(R.id.baob);
		baoc = (EditText) findViewById(R.id.baoc);
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mExit.setOnClickListener(this);
		mTvTitle.setText("扔哪去");
		baoxiuCode = (Button) findViewById(R.id.baoxiuCode);
		baoxiuCode.setOnClickListener(this);
	}


	@Override
	public void initData() {
		CodeInfor.setText(this.getIntent().getStringExtra("msg"));
		BigDecimal decimal=new BigDecimal(Double.valueOf(this.getIntent().getStringExtra("log")));
		BigDecimal bigDecimal=new BigDecimal(Double.valueOf(this.getIntent().getStringExtra("lat")));
		loglat.setText(String.valueOf(decimal.setScale(6,BigDecimal.ROUND_HALF_UP))+","+String.valueOf(bigDecimal.setScale(6,BigDecimal.ROUND_HALF_UP)));
	}

	public void initListener(){
		confirm_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if(baoa.getText().toString().isEmpty()){
					ToastUtil.show(BinCodeMessageActivity.this,"请添加垃圾桶状态");
					return;
				}
				if(baob.getText().toString().isEmpty()){
					ToastUtil.show(BinCodeMessageActivity.this,"请添加垃圾桶运营点");
					return;
				}
				if(baoc.getText().toString().isEmpty()){
					ToastUtil.show(BinCodeMessageActivity.this,"请添加故障信息");
					return;
				}
				AjaxParams params = new AjaxParams();
				params.put("action_flag", "addStation");
				params.put("stationTitle",CodeInfor.getText().toString() );
				params.put("stationCoordinate",loglat.getText().toString() );
				params.put("fullEmpty",baoa.getText().toString());
				params.put("actionDump",baob.getText().toString());
				params.put("status",baoc.getText().toString() );
				httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, true, "正在提交...");
			}
		});

	}

	/**
	 * 用户的登录
	 *
	 * @param isShow
	 */
	private void queryBinInfor(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "queryBinInfor");
		params.put("stationId", this.getIntent().getStringExtra("msg"));
		httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在登录...");

	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultFlag:

				if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
					final StationModel stationModel = mGson.fromJson(entry.getData(), StationModel.class);



					if(BinFlag==1){
						CustomToast.showToast(this,"解锁成功,放入垃圾");
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent intent = new Intent(BinCodeMessageActivity.this, ReGeocoderActivity.class);
								intent.putExtra("msg", stationModel);
								BinCodeMessageActivity.this.startActivity(intent);
								finish();
							}
						}, 2000);

					}
					else{
						Intent intent = new Intent(BinCodeMessageActivity.this, CreateTipActivity.class);
						intent.putExtra("msg", stationModel);
						BinCodeMessageActivity.this.startActivity(intent);
						finish();
					}


				}
				break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		CustomToast.showToast(this, strMsg);

	}

}
