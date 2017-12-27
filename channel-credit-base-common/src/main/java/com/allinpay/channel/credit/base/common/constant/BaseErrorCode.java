package com.allinpay.channel.credit.base.common.constant;

public class BaseErrorCode {

	public final static String SUCCESS_CODE_0000 = "000000";//查询成功
	public final static String FAIL_CODE_0001 = "000001";//查询失败
	public final static String FAIL_CODE_PARAM = "0105";//查询失败
	public final static String IDCARD_NULL_CODE_2001 = "002001";//身份证号不能为空
	public final static String NAME_NULL_CODE_2002 = "002002";//姓名不能为空
	public final static String PHONE_NULL_CODE_2003 = "002003";//手机号不能为空
	public final static String CARD_NULL_CODE_2004 = "002004";//银行卡号不能为空
	public final static String IDCARD_FORMAT_CODE_2005 = "002005";//身份证格式错误
	public final static String SHNUM_NULL_CODE_2006 = "002006";//涉诉ID号不能为空
	public final static String SHTYPE_NULL_CODE_2007 = "002007";//涉诉类型不能为空
	public final static String PHOTO_NULL_CODE_2008 = "002008";//照片不能为空
	public final static String CONTACTS_NULL_CODE_2009 = "002009";//认证常用联系人不能为空
	public final static String NAME_FORMAT_CODE_2010 = "002010";//姓名格式错误
	public final static String SERVER_EXP_CODE_9999 = "999999";//服务异常,查询失败
	
	public static String errorMsg(String code, String errors){
		return "errorCode:"+code+",errorMsg:"+errors;
	}
	
}
