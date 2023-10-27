package com.clientBase.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dustbin.R;


public class DialogTopicMsg {

	private Context context;
	private View loadingView;
	private Dialog loading;
	private EditText metInput;
	private LinearLayout mllDialog;
	private ImageView mivPraise;
	
	public DialogTopicMsg(Context context) {
		super();
		this.context = context;
		
		

		loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_input_topic, null);
		InitData();
	}

	private void InitData() {
		loading = new Dialog(context, R.style.Dialog_image);
		loading.setContentView(loadingView);
		loading.setCanceledOnTouchOutside(true);
		loading.setCancelable(true);
        Window window = loading.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        


        WindowManager.LayoutParams lp = window.getAttributes();
        
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp); 
	}


	public LinearLayout outSideDialog() {
		return mllDialog = (LinearLayout) loadingView.findViewById(R.id.mllDialog);
	}

	public EditText Set_Msg() {
		return metInput = (EditText) loadingView.findViewById(R.id.metInput);
	}

	public ImageView sendReview() {
		return mivPraise = (ImageView) loadingView.findViewById(R.id.mivPraise);
	}


	public void Show() {

		if (loading != null) {
			loading.show();
		}
	}

	public void Close() {

		if (loading != null) {
			loading.dismiss();
		}
	}
}
