package com.example.shiro;


/**
 * 
 * Shiro Filter 工具类
 * 
 */
public class ShiroFilterUtils {
	final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;
	//登录页面
	static final String LOGIN_URL = "/u/login.shtml";
	//踢出登录提示
	final static String KICKED_OUT = "/open/kickedOut.shtml";
	//没有权限提醒
	final static String UNAUTHORIZED = "/shiro-unauthorized.jsp";
	/**
	 *  表示这是一个静态的、不可变的字符串常量。final 关键字确保一旦赋值，该常量的值不能被修改
	 * */
}
