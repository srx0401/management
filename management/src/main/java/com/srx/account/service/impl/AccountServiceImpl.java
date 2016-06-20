package com.srx.account.service.impl;

import java.io.Serializable;
import java.security.InvalidKeyException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.srx.account.dao.AccountDao;
import com.srx.account.model.Account;
import com.srx.account.service.AccountService;
import com.srx.dao.base.BaseDao;
import com.srx.service.base.impl.BaseServiceImpl;
import com.srx.utils.security.DESUtil;
import com.srx.utils.string.StringUtil;

/**
 * 账户管理业务处理实现
 * 
 * @author SIMON
 *
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account, Serializable> implements AccountService {
	private static Log log = LogFactory.getLog(AccountServiceImpl.class);
	@Autowired
	private AccountDao accountDao;

	@Override
	public Serializable save(Account t) throws Exception{
		return saveOrUpdate(t);
	}

	@Override
	public Account saveOrUpdate(Account t) throws Exception {
		String pwd = t.getPassword();
		if (StringUtils.isEmpty(t.getPrompt())) {
			t.setPrompt(StringUtil.replace(pwd, null, pwd.length()/4, pwd.length()-pwd.length()/4));
		}
		t.setPassword(DESUtil.encrypt(pwd, "", null));
		if (StringUtil.isEmpty(t.getId())) {
			Serializable id =  accountDao.save(t);
			t.setId(id.toString());
			return t;
		}
		return accountDao.update(t);
		
	}
}
