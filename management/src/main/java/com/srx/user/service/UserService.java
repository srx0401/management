package com.srx.user.service;

import java.io.Serializable;

import com.srx.service.base.BaseService;
import com.srx.user.model.User;

/**
 * 用户管理业务处理接口
 * 
 * @author SIMON
 *
 */
public interface UserService extends BaseService<User, Serializable> {
	/**
	 * 登陆
	 * @param username		用户名
	 * @param password		密码
	 * @return				
	 */
	User login(final String username,final String password);
}
