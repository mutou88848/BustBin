package dust.clientUI;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.dust.R;
import dust.clientBase.base.BaseActivity;
import dust.clientBase.config.Consts;
import dust.clientBase.db.MemberUserUtils;
import dust.clientBase.model.ResponseEntry;
import dust.clientBase.model.StationModel;
import dust.clientBase.util.CustomToast;
import dust.clientBase.util.ToastUtil;
import dust.zxing.util.Constant;

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

	/**
	 * 检查是否拥有权限
	 * @param thisActivity
	 * @param permission
	 * @param requestCode
	 * @param errorText
	//     */
	protected void checkPermission (Activity thisActivity, String permission, int requestCode, String errorText) {
		//判断当前Activity是否已经获得了该权限
		if(ContextCompat.checkSelfPermission(thisActivity,permission) != PackageManager.PERMISSION_GRANTED) {
			//如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
			if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
					permission)) {
				Toast.makeText(this,errorText,Toast.LENGTH_SHORT).show();
				//进行权限请求
				ActivityCompat.requestPermissions(thisActivity,
						new String[]{permission},
						requestCode);
			} else {
				//进行权限请求
				ActivityCompat.requestPermissions(thisActivity,
						new String[]{permission},
						requestCode);
			}
		} else {

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		//        // 申请相机权限
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			// 申请权限
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
			return;
		}
		// 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			// 申请权限
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
			return;
		}


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, 10,"添加位置权限，请允许");
		}
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case Constant.REQ_PERM_CAMERA:
				// 摄像头权限申请
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// 获得授权
//                    startQrCode();
				} else {
					// 被禁止授权
					CustomToast.showToast(this,"请至权限中心打开本应用的相机访问权限");
				}
				break;
			case Constant.REQ_PERM_EXTERNAL_STORAGE:
				// 文件读写权限申请
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// 获得授权
//                    startQrCode();
				} else {
					// 被禁止授权
					CustomToast.showToast(this,"请至权限中心打开本应用的文件读写权限");
				}
				break;
		}
	}


}
