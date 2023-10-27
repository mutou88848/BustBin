package dust.clientBase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dust.R;
import dust.clientBase.base.BaseFragment;
import dust.clientUI.LoginActivity;
import dust.clientUI.MyOrderActivity;
import dust.clientUI.SoftMessageActivity;
import dust.clientUI.UserActivity;


/**
 * 
 * @author WangXuan
 * 
 */
public class MeFragment extends BaseFragment {

		// 获取view
		private View rootView;
		private RelativeLayout mtvUser;
		private RelativeLayout mrlresume;
		private RelativeLayout mtvSoft;
		private RelativeLayout mrlpay;
		private RelativeLayout mrlGym;
		private TextView mtvMaxMoney;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content, null);

			return rootView;
		}

		/**
		 * 获取控件
		 */
		@Override
		public void initWidget() {
			mrlGym = (RelativeLayout) rootView.findViewById(R.id.mrlGym);
			mrlpay= (RelativeLayout) rootView.findViewById(R.id.mrlpay);
			mtvSoft = (RelativeLayout) rootView.findViewById(R.id.mtvSoft);
			mrlresume = (RelativeLayout) rootView.findViewById(R.id.mrlresume);
			mtvUser = (RelativeLayout) rootView.findViewById(R.id.mtvUser);
			mtvUser.setOnClickListener(this);
			mtvSoft.setOnClickListener(this);
			mrlresume.setOnClickListener(this);
			mrlpay.setOnClickListener(this);
			mrlGym.setOnClickListener(this);
		}

		/**
		 * 处理点击事件
		 */
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
				case R.id.mrlresume:
					Intent mrlresume = new Intent(getActivity(), MyOrderActivity.class);
					getActivity().startActivity(mrlresume);
					break;

				case R.id.mrlpay:

					break;


				case R.id.mtvSoft:
					Intent mtvSoft = new Intent(getActivity(), SoftMessageActivity.class);
					getActivity().startActivity(mtvSoft);
					break;

				case R.id.mtvUser:
					Intent mtvUser = new Intent(getActivity(), UserActivity.class);
					getActivity().startActivity(mtvUser);
					break;

				case R.id.mExit:
					Intent intent = new Intent(getActivity(), LoginActivity.class);
					startActivity(intent);
					getActivity().finish();
					break;
				default:
					break;
			}

		}

		/**
		 * 处理数据
		 */
		@Override
		public void initData() {

		}

		@Override
		public void onResume() {
			super.onResume();
			initWidget();
			initData();
		}
	}
