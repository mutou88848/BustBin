package dust.clientBase.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dust.R;
import dust.clientBase.model.NewsModel;


/**
 * 消息适配器
 */
public class NoticeAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<NewsModel> list_result;
	private int posIndex;
	private Context mContext;

	public NoticeAdapter(Context context, List<NewsModel> list_result) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_notice_index, null);
			holder = new ViewHolder();
			holder.jobTitle = (TextView) convertView.findViewById(R.id.jobTitle);
			holder.jobMsg = (TextView) convertView.findViewById(R.id.jobMsg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.jobTitle.setText(list_result.get(position).getNewsTitle());
		holder.jobMsg.setText("发布时间："+list_result.get(position).getNewsTime());
		return convertView;

	}

	private class ViewHolder {
		private TextView jobMsg;
		private TextView jobTitle;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
