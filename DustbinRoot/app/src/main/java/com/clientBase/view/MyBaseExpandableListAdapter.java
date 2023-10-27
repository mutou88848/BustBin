package com.clientBase.view;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dustbin.R;
import com.clientBase.listener.DeleteUserListner;
import com.clientBase.model.UserModel;
import com.clientBase.model.UserTypeBean;

/**
 * ExpandListView的适配器，继承自BaseExpandableListAdapter
 *
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter implements OnClickListener {

	private Context mContext;
	private List<UserTypeBean> groupTitle;
	//子项是一个map，key是group的id，每一个group对应一个ChildItem的list
	private Map<String, List<UserModel>> childMap;
	private Button groupButton;//group上的按钮
	private DeleteUserListner mDeleteUserListner;
	public MyBaseExpandableListAdapter(Context context, List<UserTypeBean> groupTitle, Map<String, List<UserModel>> childMap, DeleteUserListner deleteUserListner) {
		this.mContext = context;
		this.groupTitle = groupTitle;
		this.childMap = childMap;
		this.mDeleteUserListner= deleteUserListner;
	}
	/*
	 *  Gets the data associated with the given child within the given group
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		//我们这里返回一下每个item的名称，以便单击item时显示
		return childMap.get(groupPosition).get(childPosition).getUserName();
	}
	/*
	 * 取得给定分组中给定子视图的ID. 该组ID必须在组中是唯一的.必须不同于其他所有ID（分组及子项目的ID）
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	/*
	 *  Gets a View that displays the data for the given child within the given group
	 */
	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
							 ViewGroup parent) {
		ChildHolder childHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.childitem, null);
			childHolder = new ChildHolder();
			childHolder.childImg = (ImageView) convertView.findViewById(R.id.img_child);
			childHolder.childText = (TextView) convertView.findViewById(R.id.tv_child_text);
			childHolder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);

			convertView.setTag(childHolder);
		}else {
			childHolder = (ChildHolder) convertView.getTag();
		}


		final String key = groupTitle.get(groupPosition).getUsertypeName();
		childHolder.childText.setText(childMap.get(key).get(childPosition).getUserName());


		childHolder.imgDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDeleteUserListner.setDelete(childMap.get(key).get(childPosition));
			}
		});


		return convertView;
	}

	/*
	 * 取得指定分组的子元素数
	 */
	@Override
	public int getChildrenCount(int groupPosition) {


		try {
			String key = groupTitle.get(groupPosition).getUsertypeName();
			Log.i("pony_log", key);
			int size = childMap.get(key).size();
			return size;
//
		} catch (Exception e) {
		}
		return 0;

//		return childMap.get(groupPosition).size();
	}

	/**
	 * 取得与给定分组关联的数据
	 */
	@Override
	public Object getGroup(int groupPosition) {
		return groupTitle.get(groupPosition);
	}

	/**
	 * 取得分组数
	 */
	@Override
	public int getGroupCount() {
		return groupTitle.size();
	}

	/**
	 * 取得指定分组的ID.该组ID必须在组中是唯一的.必须不同于其他所有ID（分组及子项目的ID）
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	/* 
	 *Gets a View that displays the given group
	 *return: the View corresponding to the group at the specified position 
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupHolder groupHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.groupitem, null);
			groupHolder = new GroupHolder();
			groupHolder.groupImg = (ImageView) convertView.findViewById(R.id.img_indicator);
			groupHolder.groupText = (TextView) convertView.findViewById(R.id.tv_group_text);
			convertView.setTag(groupHolder);
		}else {
			groupHolder = (GroupHolder) convertView.getTag();
		}
		if (isExpanded) {
			groupHolder.groupImg.setBackgroundResource(R.drawable.icon_dakai);
		}else {
			groupHolder.groupImg.setBackgroundResource(R.drawable.icon_zhedie);
		}
		groupHolder.groupText.setText(groupTitle.get(groupPosition).getUsertypeName());

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// Indicates whether the child and group IDs are stable across changes to the underlying data
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// Whether the child at the specified position is selectable
		return true;
	}
	/**
	 * show the text on the child and group item
	 */
	private class GroupHolder
	{
		ImageView groupImg;
		TextView groupText;
	}
	private class ChildHolder
	{
		ImageView childImg;
		TextView childText;
		ImageView imgDelete;

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			default:
				break;
		}

	}
}
