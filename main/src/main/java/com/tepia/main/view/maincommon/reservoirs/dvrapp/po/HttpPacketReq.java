package com.tepia.main.view.maincommon.reservoirs.dvrapp.po;

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
