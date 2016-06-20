package com.srx.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srx.model.common.Conditional;
import com.srx.service.base.impl.BaseServiceImpl;
import com.srx.user.dao.UserDao;
import com.srx.user.model.User;
import com.srx.user.service.UserService;
import com.srx.utils.security.MD5Util;
import com.srx.utils.string.StringUtil;

/**
 * 用户管理业务处理实现
 * 
 * @author SIMON
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Serializable> implements UserService {
	private static Log log = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Override
	public Serializable save(User t) throws Exception{
		return saveOrUpdate(t);
	}

	@Override
	public User login(final String username,final String password) {
		if (StringUtil.isEmpty(username)) {
			log.error("用户名不能为空");
			return null;
		}
		if(StringUtil.isEmpty(password)){
			log.error("密码不能为空");
			return null;
		}
		List<Conditional> cons = new ArrayList<Conditional>();
		cons.add(new Conditional("username",username));
//		cons.add(new Conditional("password",MD5Util.MD5(password)));
		cons.add(new Conditional("password",password));
		List<User> res = userDao.list(cons);
		System.out.println(username);
		System.out.println(MD5Util.MD5(password));
		return res == null || res.size() < 1 ? null : res.get(0);
	}
}
