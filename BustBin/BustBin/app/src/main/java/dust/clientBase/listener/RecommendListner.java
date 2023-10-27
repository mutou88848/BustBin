package dust.clientBase.listener;


import dust.clientBase.model.FriendModel;
import dust.clientBase.model.UserModel;

public interface RecommendListner {
	void setRecommend(int pos, UserModel userModel);
	void setRecommendDelete(int pos, FriendModel userModel);
}
