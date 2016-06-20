package com.srx.account.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.srx.account.dao.AccountDao;
import com.srx.account.model.Account;
import com.srx.dao.base.impl.BaseDaoImpl;
/**
 * 账户管理数据处理实现
 * @author SIMON
 *
 */
@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl<Account, Serializable> implements AccountDao{
	
}
