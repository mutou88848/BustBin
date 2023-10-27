package dust.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dust.R;
import dust.clientBase.base.BaseActivity;
import dust.clientBase.config.Consts;
import dust.clientBase.model.ResponseEntry;
import dust.clientBase.model.StationModel;
import dust.clientBase.util.CustomToast;

import net.tsz.afinal.http.AjaxParams;

public class BinCodeMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	private Button mExit,baoxiuCode;
	private TextView CodeInfor;
	private String msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bincode_message);
		initWidget();
		initData();
	}

	private int bikeFlag=1;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;
			case R.id.mExit:
				bikeFlag = 1;
				queryBikeInfor(true);
				break;

			case R.id.baoxiuCode:
				bikeFlag = 2;
				queryBikeInfor(true);
				break;
		}
	}

	@Override
	public void initWidget() {


		CodeInfor = (TextView) findViewById(R.id.CodeInfor);
		mExit = (Button) findViewById(R.id.mExit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);

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
	}


	/**
	 * 用户的登录
	 *
	 * @param isShow
	 */
	private void queryBikeInfor(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "queryBikeInfor");
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



					if(bikeFlag==1){
						CustomToast.showToast(this,"解锁成功,放入垃圾");
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent intent = new Intent(BinCodeMessageActivity.this, CustomLocationActivity.class);
								intent.putExtra("msg", stationModel);
								BinCodeMessageActivity.this.startActivity(intent);
								finish();
							}
						}, 2000);

					}else{
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
