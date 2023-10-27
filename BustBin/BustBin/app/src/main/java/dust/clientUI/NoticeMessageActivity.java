package dust.clientUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dust.R;
import dust.clientBase.base.BaseActivity;
import dust.clientBase.model.NewsModel;

public class NoticeMessageActivity extends BaseActivity {
    // title
    private TextView mTvTitle, mIvStu;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private TextView mtvtitle;
    private TextView mtvcontentZhuSu;
    private NewsModel noticeModel;

    private TextView mtvtime;

    private RelativeLayout mllbottom;

    private Button mbtnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsmsg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {


        mbtnPay = (Button) findViewById(R.id.mbtnPay);

        mllbottom = (RelativeLayout) findViewById(R.id.mllbottom);

        mtvcontentZhuSu = (TextView) findViewById(R.id.mtvcontentZhuSu);
        mtvtitle = (TextView) findViewById(R.id.mtvtitle);
        mtvtime = (TextView) findViewById(R.id.mtvtime);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("信息详情");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mbtnPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                NoticeMessageActivity.this.finish();
                break;

            case R.id.mIvStu:

                break;


        }
    }

    String flagInfor;

    @Override
    public void initData() {


        noticeModel = (NewsModel) this.getIntent().getSerializableExtra("msg");
        mtvtitle.setText(noticeModel.getNewsTitle());
        mtvcontentZhuSu.setText("        " + noticeModel.getNewsMessage());
        mtvtime.setText("发布时间：" + noticeModel.getNewsTime());


    }

}
