package com.tepia.main.view.maincommon.reservoirs.dvrapp.po;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :
  * Version :1.0
  * 功能描述 :
 **/
public class HttpPacketReq 
{

	public String success;
	public Object msg;

	public HttpPacketReq(HttpPacketReq _oPkt)
	{
		success = _oPkt.success;
		msg = _oPkt.msg;

	}

}
