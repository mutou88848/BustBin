package dust.clientUI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dust.R;
import dust.clientBase.base.BaseActivity;
import dust.clientBase.config.Consts;
import dust.clientBase.db.MemberUserUtils;
import dust.clientBase.model.ResponseEntry;
import dust.clientBase.model.UserModel;
import dust.clientBase.util.CustomToast;
import dust.clientBase.util.LoadingDialog;
import dust.clientBase.util.ToastUtil;
import dust.zxing.util.Constant;

import net.tsz.afinal.http.AjaxParams;

public class LoginActivity extends BaseActivity {
    // title
    private TextView mTvTitle;
    // 登录用户名称
    private EditText mLoginNumber;
    // 登录密码
    private EditText mLoginPswd;
    // 登录按钮
    private Button mLogin;
    private Button mEnterpriseQuery;
    private LinearLayout mllTop;
    private UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
    }




    /**
     * 控件初始化
     */
    @Override
    public void initWidget() {

        mdialog = new LoadingDialog(this, "正在登录");
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("登录");
        mLoginNumber = (EditText) findViewById(R.id.mLoginNumber);
        mLoginPswd = (EditText) findViewById(R.id.mLoginPswd);
        mLogin = (Button) findViewById(R.id.mLogin);
        mEnterpriseQuery = (Button) findViewById(R.id.mEnterpriseQuery);
        // mLoginNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        // 事件的监听
        mLogin.setOnClickListener(this);
        mEnterpriseQuery.setOnClickListener(this);
        // 给输入框设置默认的测试数据
        mLoginNumber.setSelection(mLoginNumber.getText().length());

        mLoginNumber.setText("17609544017");
        mLoginPswd.setText("123456");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mLogin:
                if (TextUtils.isEmpty(mLoginNumber.getText().toString())) {
                    ToastUtil.ShowCentre(LoginActivity.this, "请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(mLoginPswd.getText().toString())) {
                    ToastUtil.ShowCentre(LoginActivity.this, "请输入登录密码");
                    return;
                }
                LoginUserPost(true);
                break;

            case R.id.mEnterpriseQuery:
                Intent mEnterpriseQuery = new Intent(LoginActivity.this, FrameworkMenuActivity.class);
                startActivity(mEnterpriseQuery);
            default:
                break;
        }
    }

    @Override
    public void initData() {
    }

    /**
     * 用户的登录
     *
     * @param isShow
     */
    private void LoginUserPost(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "login");
        params.put("userPhone", mLoginNumber.getText().toString());
        params.put("userPswd", mLoginPswd.getText().toString());
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在登录...");
    }
    /**RegisterAction要与exlipse中的RegisterAction对应**/

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:

                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
                    userModel = mGson.fromJson(entry.getData(), UserModel.class);
                    MemberUserUtils.setUid(LoginActivity.this, userModel.getUserId());
                    MemberUserUtils.setName(LoginActivity.this, userModel.getUserName());
                    MemberUserUtils.setLoginFlag(LoginActivity.this, "food");
                    MemberUserUtils.putBean(LoginActivity.this, "user_messgae", userModel);
                    Intent intent = new Intent(LoginActivity.this, FrameworkMenuActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(LoginActivity.this, strMsg);

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
        // 申请文件读写权限（可有可无）
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