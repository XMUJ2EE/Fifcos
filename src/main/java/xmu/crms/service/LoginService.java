package xmu.crms.service;

import java.math.BigInteger;

import xmu.crms.entity.*;

/**
 * @author ModuleStandardGroup/YeHongjie
 * @version 2.00
 */
public interface LoginService {
	
	/**
	 * 微信登录.
	 * @author qinlingyun
	 * @param userId 用户Id
	 * @param code 微信小程序/OAuth2授权的Code
	 * @param state 微信OAuth2授权的state。对于小程序，值恒为 MiniProgram
	 * @param successUrl 微信OAuth2授权后跳转到的网址
	 * @return user 该用户信息
	 */
	 User signInWeChat(BigInteger userId,String code,String state,String successUrl);

}
