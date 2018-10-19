package com.tepia.main.view.maincommon.reservoirs.dvrapp.po;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :
  * Version :1.0
  * 功能描述 :
 **/
public class ObjectDevices 
{
	public String deviceId;
	public String ipAddr;
	public  int       port;
	public  int       channelAmt;
	public String deviceName;
	public int   monTraLimit;
	
	public ObjectDevices( ObjectDevices _oObjectdDevices)
	{
		deviceId  =  _oObjectdDevices.deviceId;
		ipAddr  =  _oObjectdDevices.ipAddr;
		port  =  _oObjectdDevices.port;
		channelAmt  =  _oObjectdDevices.channelAmt;
		deviceName  =  _oObjectdDevices.deviceName;
		monTraLimit  =  _oObjectdDevices.monTraLimit;
	}
}
