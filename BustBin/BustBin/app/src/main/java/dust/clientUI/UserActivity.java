package dust.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dust.R;
import dust.clientBase.base.BaseActivity;
import dust.clientBase.db.MemberUserUtils;
import dust.clientBase.model.UserModel;

/**
 *
 * @author wangxuan 用户信息的展示
 */
public class UserActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private RelativeLayout mtvUserName;
	private RelativeLayout mrlAddress;
	private TextView mIvStu;

	private TextView userName;
	private TextView userPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				UserActivity.this.finish();
				break;
			case R.id.mrlAddress:
				Intent intent = new Intent(this, UpdatePswdActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.mtvUserName:
				Intent mtvUserName = new Intent(this, UpdateNameActivity.class);
				startActivity(mtvUserName);
				finish();
				break;
		}
	}

	@Override
	public void initWidget() {

		userName = (TextView) findViewById(R.id.userName);
		userPhone = (TextView) findViewById(R.id.userPhone);



		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mTvTitle.setText("我的资料");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
		mIvStu.setText("修改");
		mIvStu.setVisibility(View.GONE);

		mtvUserName = (RelativeLayout) findViewById(R.id.mtvUserName);
		mtvUserName.setOnClickListener(this);

		mrlAddress = (RelativeLayout) findViewById(R.id.mrlAddress);
		mrlAddress.setOnClickListener(this);

	}

	@Override
	public void initData() {
		userName = (TextView) findViewById(R.id.userName);
		userPhone = (TextView) findViewById(R.id.userPhone);
		try {
			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
			userName.setText("你的姓名： " + userModel.getUserName());
			userPhone.setText("你的手机： " + userModel.getUserPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}
