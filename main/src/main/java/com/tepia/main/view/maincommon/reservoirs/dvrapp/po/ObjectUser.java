package com.tepia.main.view.maincommon.reservoirs.dvrapp.po;

public class ObjectUser
{

	public int userId;
	public String account;
	public String userName;
	public String mobile;
	public String expiredDate;
	public int previewLimit;
	public String description;
	public int userState;
	public String roleName;

	
	public ObjectUser(ObjectUser _oObjectUser)
	{
		userId  =  _oObjectUser.userId;
		account  =  _oObjectUser.account;
		userName  =  _oObjectUser.userName;
		mobile  =  _oObjectUser.mobile;
		expiredDate  =  _oObjectUser.expiredDate;
		previewLimit  =  _oObjectUser.previewLimit;
		description  =  _oObjectUser.description;
		userState  =  _oObjectUser.userState;
		roleName  =  _oObjectUser.roleName;

	}

	public ObjectUser(int _userId, String _account, String _userName, String _mobile, String _expiredDate
	, int _previewLimit, String _description, int _userState, String _roleName)
	{
		userId  =  _userId;
		account  =  _account;
		userName  =  _userName;
		mobile  =  _mobile;
		expiredDate  =  _expiredDate;
		previewLimit  =  _previewLimit;
		description  =  _description;
		userState  =  _userState;
		roleName  =  _roleName;
	}

}

