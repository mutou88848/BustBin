package com.clientBase.listener;


import com.clientBase.model.FriendModel;
import com.clientBase.model.UserModel;

public interface RecommendListner {
	void setRecommend(int pos, UserModel userModel);
	void setRecommendDelete(int pos, FriendModel userModel);
}
