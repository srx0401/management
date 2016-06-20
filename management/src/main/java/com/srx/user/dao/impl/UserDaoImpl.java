package com.srx.user.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.srx.dao.base.impl.BaseDaoImpl;
import com.srx.user.dao.UserDao;
import com.srx.user.model.User;
/**
 * 用户管理数据处理实现
 * @author SIMON
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Serializable> implements UserDao {

}
