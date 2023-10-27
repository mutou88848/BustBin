package dust.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dust.R;
import dust.clientBase.model.NoticeBean;

import java.util.List;


public class MessageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<NoticeBean> list_result;
    private int posIndex;
    private Context mContext;

    public MessageAdapter(Context context, List<NoticeBean> list_result) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.list_result = list_result;
    }

    @Override
    public int getCount() {
        return list_result.size();
    }

    @Override
    public Object getItem(int position) {
        return list_result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_message_message, null);
            holder = new ViewHolder();
            holder.noticeType = (TextView) convertView.findViewById(R.id.noticeType);
            holder.mtvMsgContent = (TextView) convertView.findViewById(R.id.mtvMsgContent);
            holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
            holder.mivIsRead = (ImageView) convertView.findViewById(R.id.mivIsRead);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {


            if(list_result.get(position).getNewsType().equals("1")){
                holder.noticeType.setText("类型：社区通知");
            }else{
                holder.noticeType.setText("类型：活动公告");
            }

            holder.mtvMsgContent.setText(list_result.get(position).getNewsTitle());
            holder.mtvTime.setText("时间：" + list_result.get(position).getNewsTime());

                holder.mivIsRead.setVisibility(View.GONE);

        } catch (Exception e) {
            // TODO: handle exception
        }

        return convertView;

    }

    public void setPos(int pos) {
        posIndex = pos;
    }

    private class ViewHolder {
        private TextView noticeType;
        private TextView mtvMsgContent;
        private TextView mtvTime;
        private ImageView mivIsRead;
    }

}
