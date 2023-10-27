package dust.clientBase.model;

import java.io.Serializable;

/**
 * 
 * @author wangxuan
 * 
 */
public class UserModel implements Serializable{


	/**
	 * userAddress : xianshi
	 * userCard :
	 * userLike : dota2
	 * userId : 106
	 * userName : 小丸子
	 * userPswd : 123456
	 * userPhone : 15249249696
	 * userTime : 2019-02-11 11:21
	 * userImg : timg-8.jpeg
	 */

	private String userAddress;
	private String userCard;
	private String userLike;
	private String userId;
	private String userName;
	private String userPswd;
	private String userPhone;
	private String userTime;
	private String userImg;

	private String userMoney;

	public String getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}

	public String getUserCarNo() {
		return userCarNo;
	}

	public void setUserCarNo(String userCarNo) {
		this.userCarNo = userCarNo;
	}

	private String userCarNo;



	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getUserLike() {
		return userLike;
	}

	public void setUserLike(String userLike) {
		this.userLike = userLike;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPswd() {
		return userPswd;
	}

	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserTime() {
		return userTime;
	}

	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
}
