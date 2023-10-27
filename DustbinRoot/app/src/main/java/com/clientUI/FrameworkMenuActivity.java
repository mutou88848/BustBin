package com.clientUI;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dustbin.R;
import com.clientBase.adapter.ChoiceAdapter;
import com.clientBase.fragment.MeFragment;
import com.clientBase.fragment.StationFragment;

public class FrameworkMenuActivity extends FragmentActivity implements OnClickListener {
	ChoiceAdapter choiceAdapter;


	/**
	 * 用于展示消息的Fragment
	 */
	private StationFragment stationFragment;

	/**
	 * 用于展示联系人的Fragment
	 */
	private MeFragment imFragment;
	//
	// /**
	// * 用于展示动态的Fragment
	// */
	// private MeFragment newsFragment;

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
	// private View person_layout;

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
	// private ImageView person_image;

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
	// private TextView person_text;

	private long mExitTime;
	private static final int INTERVAL = 2000;
	Intent intent;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	private DrawerLayout mDrawerLayout;

	private ListView mlvChoice;

	private String[] array_msg = new String[] { "个人信息", "报修记录" ,"关于我们" , "软件信息"};
	private List<String> listMsg = new ArrayList<String>();




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_framework_leftmenu);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout2);

		setWindowStatus();
		// 初始化布局元素
		initViews();
		findViewById(R.id.iv_menu).setOnClickListener(clickListener);
		// getSupportFragmentManager().beginTransaction()
		fragmentManager = getSupportFragmentManager();


		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 当点击了消息tab时，改变控件的图片和文字颜色
		if (stationFragment == null) {
			// 如果MessageFragment为空，则创建一个并添加到界面上
			stationFragment = new StationFragment();
			transaction.add(R.id.content, stationFragment);
		} else {
			// 如果MessageFragment不为空，则直接将它显示出来
			transaction.show(stationFragment);
		}
		transaction.commit();
	}

	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.iv_menu:// 打开左边抽屉
					mDrawerLayout.openDrawer(Gravity.RIGHT);
					break;
			}
		}
	};




	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {

		mlvChoice = (ListView) findViewById(R.id.mListMessage);

		for (int i = 0; i < array_msg.length; i++) {
			listMsg.add(array_msg[i]);
		}

		choiceAdapter = new ChoiceAdapter(this, listMsg);
		mlvChoice.setAdapter(choiceAdapter);
		mlvChoice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mDrawerLayout.closeDrawers();

				if(position==0){
					Intent mbtnExame = new Intent(FrameworkMenuActivity.this, UserActivity.class);
					FrameworkMenuActivity.this.startActivity(mbtnExame);
				}else if(position==1){
					Intent mbtnExame = new Intent(FrameworkMenuActivity.this, RepairListActivity.class);
					FrameworkMenuActivity.this.startActivity(mbtnExame);
				}

				else if(position==2){
					Intent mbtnExame = new Intent(FrameworkMenuActivity.this, AboutActivity.class);
					FrameworkMenuActivity.this.startActivity(mbtnExame);
				}else{
					Intent mbtnExame = new Intent(FrameworkMenuActivity.this, SoftMessageActivity.class);
					FrameworkMenuActivity.this.startActivity(mbtnExame);


				}


//				ShowObservable.getInstance().notifyStepChange(listMsg.get(position));

			}
		});
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			/**
			 * 当抽屉滑动状态改变的时候被调用 状态值是STATE_IDLE（闲置--0）, STATE_DRAGGING（拖拽的--1）,
			 * STATE_SETTLING（固定--2）中之一。
			 * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
			 */
			@Override
			public void onDrawerStateChanged(int arg0) {
				Log.i("drawer", "drawer的状态：" + arg0);
			}

			/**
			 * 当抽屉被滑动的时候调用此方法 arg1 表示 滑动的幅度（0-1）
			 */
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				Log.i("drawer", arg1 + "");
			}

			/**
			 * 当一个抽屉被完全打开的时候被调用
			 */
			@Override
			public void onDrawerOpened(View arg0) {
				choiceAdapter.notifyDataSetChanged();
				Log.i("drawer", "抽屉被完全打开了！");
			}

			/**
			 * 当一个抽屉完全关闭的时候调用此方法
			 */
			@Override
			public void onDrawerClosed(View arg0) {
				Log.i("drawer", "抽屉被完全关闭了！");
			}
		});

		/**
		 * 也可以使用DrawerListener的子类SimpleDrawerListener,
		 * 或者是ActionBarDrawerToggle这个子类(详见FirstDemoActivity)
		 */
		mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		});


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {


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
//		        // 申请相机权限
//		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//			// 申请权限
//			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
//			return;
//		}
//		// 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
//		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//			// 申请权限
//			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
//			return;
//		}


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, 10,"添加位置权限，请允许");
		}
	}




}

