package dust.clientBase.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dust.R;


public class ChoiceAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<String> list_result;
	private int posIndex = -1;
	private Context mContext;
	public ChoiceAdapter(Context context, List<String> list_result) {
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
			convertView = inflater.inflate(R.layout.reviewuser_item, null);
			holder = new ViewHolder();
			holder.mrbMsg = (TextView) convertView.findViewById(R.id.mrbMsg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// try {
		holder.mrbMsg.setText(list_result.get(position));


		return convertView;

	}

	private class ViewHolder {
		private TextView mrbMsg;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
