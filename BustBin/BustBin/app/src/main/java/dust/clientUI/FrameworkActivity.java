package dust.clientUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dust.R;
import dust.clientBase.app.PonyApplication;
import dust.clientBase.fragment.MeFragment;
import dust.clientBase.fragment.StationFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameworkActivity extends FragmentActivity implements OnClickListener, AMapLocationListener {

	/**
	 * 用于展示消息的Fragment
	 */
	private StationFragment mainFragment;

//	private MainMessageFragment imFragment;

	private MeFragment meFragment;

	/**
	 * 消息界面布局
	 */
	private View messageLayout;

	/**
	 * 设置界面布局
	 */
	private View settingLayout;
//
	/**
	 * 设置界面布局
	 */
	private View person_layout;

	/**
	 * 在Tab布局上显示消息图标的控件
	 */
	private ImageView messageImage;

	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	private ImageView settingImage;

	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	private ImageView person_image;

	/**
	 * 在Tab布局上显示消息标题的控件
	 */
	private TextView messageText;

	/**
	 * 在Tab布局上显示设置标题的控件
	 */
	private TextView settingText;
//
	/**
	 * 在Tab布局上显示设置标题的控件
	 */
	private TextView person_text;

	private long mExitTime;
	private static final int INTERVAL = 2000;
	Intent intent;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	private Button mivCreateMessage;


	// 声明mlocationClient对象
	public AMapLocationClient mlocationClient;
	// 声明mLocationOption对象
	public AMapLocationClientOption mLocationOption = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_framework);


		setWindowStatus();
		// 初始化布局元素
		initViews();

		// getSupportFragmentManager().beginTransaction()
		fragmentManager = getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}



	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {

		mlocationClient = new AMapLocationClient(this);
		// 初始化定位参数
		mLocationOption = new AMapLocationClientOption();
		// 设置定位监听
		mlocationClient.setLocationListener(this);
		// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		// 设置定位间隔,单位毫秒,默认为2000ms
		mLocationOption.setInterval(2000);
		mLocationOption.setOnceLocation(true);
		// 设置定位参数
		mlocationClient.setLocationOption(mLocationOption);
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用onDestroy()方法
		// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
		// 启动定位
		mlocationClient.startLocation();



		mivCreateMessage = (Button) findViewById(R.id.mivCreateMessage);
		messageLayout = findViewById(R.id.message_layout);
		settingLayout = findViewById(R.id.setting_layout);
		person_layout = findViewById(R.id.person_layout);

		messageImage = (ImageView) findViewById(R.id.message_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		person_image = (ImageView) findViewById(R.id.person_image);

		messageText = (TextView) findViewById(R.id.message_text);
		settingText = (TextView) findViewById(R.id.setting_text);
		person_text = (TextView) findViewById(R.id.person_text);

		messageLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
		person_layout.setOnClickListener(this);
		mivCreateMessage.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.message_layout:
				// 当点击了消息tab时，选中第1个tab
				setTabSelection(0);
				break;
			case R.id.setting_layout:
				// 当点击了设置tab时，选中第4个tab
				setTabSelection(1);
				break;
			case R.id.person_layout:
				// 当点击了设置tab时，选中第4个tab
				setTabSelection(2);
				break;



			default:
				break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 *
	 * @param index
	 *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
			case 0:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				messageImage.setImageResource(R.mipmap.scenic_true);
				messageText.setTextColor(Color.parseColor("#393a3f"));
				if (mainFragment == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					mainFragment = new StationFragment();
					transaction.add(R.id.content, mainFragment);
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(mainFragment);
				}
				break;
			case 1:

//				// 当点击了联系人tab时，改变控件的图片和文字颜色
//				settingImage.setImageResource(R.mipmap.travels_true);
//				settingText.setTextColor(Color.parseColor("#393a3f"));
//				if (imFragment == null) {
//					// 如果ContactsFragment为空，则创建一个并添加到界面上
//					imFragment = new MainMessageFragment();
//					transaction.add(R.id.content, imFragment);
//				} else {
//					// 如果ContactsFragment不为空，则直接将它显示出来
//					transaction.show(imFragment);
//				}
				break;
			case 2:
				// 当点击了动态tab时，改变控件的图片和文字颜色
				person_image.setImageResource(R.mipmap.me_true);
				person_text.setTextColor(Color.parseColor("#393a3f"));
				if (meFragment == null) {
					// 如果NewsFragment为空，则创建一个并添加到界面上
					meFragment = new MeFragment();
					transaction.add(R.id.content, meFragment);
				} else {
					// 如果NewsFragment不为空，则直接将它显示出来
					transaction.show(meFragment);
				}
				break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		messageImage.setImageResource(R.mipmap.scenic_false);
		messageText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.mipmap.travels_false);
		settingText.setTextColor(Color.parseColor("#82858b"));
		person_image.setImageResource(R.mipmap.me_false);
		person_text.setTextColor(Color.parseColor("#82858b"));

	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 *
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mainFragment != null) {
			transaction.hide(mainFragment);
		}
//		if (imFragment != null) {
//			transaction.hide(imFragment);
//		}
		if (meFragment != null) {
			transaction.hide(meFragment);
		}

	}

	// 设置状态栏
	private void setWindowStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// 设置状态栏颜色
			getWindow().setBackgroundDrawableResource(R.color.main_color);
		}
	}

	public void onBackPressed() {
		if (System.currentTimeMillis() - mExitTime > INTERVAL) {
			Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			this.startActivity(intent);
			System.exit(0);
		}
	}



	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (amapLocation != null) {
			if (amapLocation.getErrorCode() == 0) {
				// 定位成功回调信息，设置相关消息
				amapLocation.getLocationType();// 获取当前定位结果来源，如网络定位结果，详见定位类型表
				amapLocation.getLatitude();// 获取纬度
				amapLocation.getLongitude();// 获取经度
				amapLocation.getAccuracy();// 获取精度信息
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(amapLocation.getTime());
				df.format(date);// 定位时间

				PonyApplication.getInstance().aMapLocation = amapLocation;

				Log.i("pony_log", "获取定位数据：" + amapLocation.getLatitude() + "," + amapLocation.getLongitude());
			} else {
				// 显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
				Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
			}
		}
	}

}