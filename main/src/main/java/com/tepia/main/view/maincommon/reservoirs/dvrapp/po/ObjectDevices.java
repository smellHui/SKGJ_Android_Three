package com.tepia.main.view.maincommon.reservoirs.dvrapp.po;

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
