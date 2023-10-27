package dust.clientUI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

import com.dust.R;
import dust.clientBase.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {
	
	private AlphaAnimation start_anima;
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		view = View.inflate(this, R.layout.activity_welcome, null);
		setContentView(view);
		
		
		start_anima = new AlphaAnimation(0.3f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redirectTo();
			}
		});
		
		


	}
	
	

	private void redirectTo() {
		startActivity(new Intent(getApplicationContext(), FrameworkMenuActivity.class));
		finish();
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	 // 检查是否拥有权限

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


//	@Override
//	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//		switch (requestCode) {
//			case Constant.REQ_PERM_CAMERA:
//				// 摄像头权限申请
//				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//					// 获得授权
////                    startQrCode();
//				} else {
//					// 被禁止授权
//					CustomToast.showToast(this,"请至权限中心打开本应用的相机访问权限");
//				}
//				break;
//			case Constant.REQ_PERM_EXTERNAL_STORAGE:
//				// 文件读写权限申请
//				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//					// 获得授权
////                    startQrCode();
//				} else {
//					// 被禁止授权
//					CustomToast.showToast(this,"请至权限中心打开本应用的文件读写权限");
//				}
//				break;
//		}
//	}
}
