package com.tepia.main.view.main.detail.vedio;

import java.io.Serializable;

/**
 * copy通鸭溪水务app
 * @author ly
 * @date 2018/8/1
 */
public class VideoInfo implements Serializable {

	private int channelID;
	private String data;
	private String chaName;
	private String chaStatus;

	public VideoInfo(int channelID, String chaName, String chaStatus) {
		this.channelID = channelID;
		this.chaName = chaName;
		this.chaStatus = chaStatus;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId() {
		return channelID;
	}

	public void setId(int id) {
		this.channelID = id;
	}

	public String getChaName() {
		return chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	public String getChaStatus() {
		return chaStatus;
	}

	public void setChaStatus(String chaStatus) {
		this.chaStatus = chaStatus;
	}

	@Override
	public String toString() {
		return "Video{" +
				"id=" + channelID +
				", chaName='" + chaName + '\'' +
				", chaStatus='" + chaStatus + '\'' +
				'}';
	}
}
