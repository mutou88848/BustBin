package com.clientBase.db;

public interface IService
{
	public void play();
	public boolean isPlay();
	public void playNext();
	public void playFront();
	public void playPath(String path);
	public void playPos(int pos);
	public void playNotifi();
	public void playModeCheck();
	
	

}
