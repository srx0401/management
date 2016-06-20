package com.srx.service.base.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srx.dao.base.BaseDao;
import com.srx.model.common.Conditional;
import com.srx.model.common.Page;
import com.srx.service.base.BaseService;
@Transactional
@Service("baseService")
public class BaseServiceImpl<T,PK extends Serializable> implements BaseService<T, PK> {
	private static Log log = LogFactory.getLog(BaseServiceImpl.class);
	@Autowired
	private BaseDao<T,PK> baseDao;
	
	@Override
	public T get(PK id) {
		return baseDao.get(id);
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public PK save(T t) throws Exception {
		return baseDao.save(t);
	}

	@Override
	public List<T> list(final List<Conditional> conditionals) {
		return baseDao.list(conditionals);
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public T update(T t)  throws Exception{
		return baseDao.update(t);
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public T saveOrUpdate(T t)  throws Exception{
		return baseDao.saveOrUpdate(t);
	}

	@Override
	public boolean remove(T t)  throws Exception{
		return baseDao.remove(t);
	}

	@Override
	public boolean remove(PK id)  throws Exception{
		return baseDao.remove(id);
	}

	@Override
	public Page<T> list(int currentPage, int pageSize,final List<Conditional> conditionals) {
		return baseDao.list(currentPage, pageSize,conditionals);
	}

	@Override
	public List<T> listByPropertyEqual(String propertyName, String propertyValue) {
		return baseDao.listByPropertyEqual(propertyName, propertyValue);
	}

	@Override
	public Page<T> listByPropertyEqual(String propertyName, String propertyValue, int currentPage, int pageSize) {
		return baseDao.listByPropertyEqual(propertyName, propertyValue,currentPage,pageSize);
	}

	@Override
	public boolean remove(List<PK> ids) throws Exception {
		try {
			for(PK id : ids){
				remove(id);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
