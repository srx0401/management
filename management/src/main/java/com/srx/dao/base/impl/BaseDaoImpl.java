package com.srx.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.srx.dao.base.BaseDao;
import com.srx.model.common.Conditional;
import com.srx.model.common.Page;
import com.srx.utils.date.DateUtil;
import com.srx.utils.string.StringUtil;

@Repository("baseDao")
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	private static Log log = LogFactory.getLog(BaseDaoImpl.class);
	protected Class<T> entityClass;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	} 
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		T t = null;
		log.debug(t);

		this.entityClass = null;
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();

			this.entityClass = (Class<T>) parameterizedType[0];
			log.info("entityClass=" + entityClass.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		Object res = getSession().get(entityClass, id);
		return res == null ? null : (T) res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK save(T t) {
		Assert.notNull(t, "t is required");
		Object res = sessionFactory.getCurrentSession().save(t);
		return res == null ? null : (PK) res;
	}

	public T update(T t) {
		getSession().update(t);
		return t;
	}

	public T saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
		return t;
	}

	public List<T> list(final List<Conditional> conditionals) {
		StringBuffer hql = new StringBuffer("FROM ");
		hql.append(entityClass.getName());
		if (conditionals != null && conditionals.size() > 0) {
			hql.append(" where 1=1");
			for(Conditional c : conditionals){
				if (c != null && !StringUtil.isEmpty(c.getKey())) {
					hql.append(c.getHql());
				}
			}
		}
		List<T> res = getQuery(hql.toString(),conditionals).list();
		return res;
	}

	@Override
	public boolean remove(T t) {
		try {
			getSession().delete(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(PK id) {
		return remove(get(id));
	}

	@Override
	public Page<T> list(int currentPage,int pageSize,final List<Conditional> conditionals) {
		Page<T> res = new Page<T>();
		res.setCurrentPage(currentPage);
		res.setPageSize(pageSize);
		StringBuffer hql = new StringBuffer("FROM ");
		hql.append(entityClass.getName());
		if (conditionals != null && conditionals.size() > 0) {
			hql.append(" where 1=1");
			for(Conditional c : conditionals){
				if (c != null && !StringUtil.isEmpty(c.getKey())) {
					hql.append(c.getHql());
				}
			}
		}
		Long count =  (Long) getQuery("SELECT COUNT(ID) " + hql.toString(),conditionals).uniqueResult();
		res.setTotal(count);
		log.info("totalCount:" + count);
		if (count > 0) {
			res.setRows(getQuery(hql.toString(),conditionals).setMaxResults(pageSize).setFirstResult(currentPage * pageSize - pageSize).list());
		}
		return res;
	}

	@Override
	public List<T> listByPropertyEqual(String propertyName, String propertyValue) {
		List<Conditional> conditionals = new ArrayList<Conditional>();
		conditionals.add(new Conditional(propertyName,propertyValue));
		return list(conditionals);
	}
	@Override
	public Page<T> listByPropertyEqual(String propertyName, String propertyValue, int currentPage, int pageSize) {
		List<Conditional> conditionals = new ArrayList<Conditional>();
		conditionals.add(new Conditional(propertyName,propertyValue));
		return list(currentPage, pageSize, conditionals);
	}
	protected Query getQuery(final String hql,final List<Conditional> conditionals) {
		if (StringUtil.isEmpty(hql)) {
			return null;
		}
		Query res = getSession().createQuery(hql);
		if (conditionals != null && conditionals.size() > 0) {
			for(int i = 0;i < conditionals.size();i ++){
				Conditional c = conditionals.get(i);
				if (c != null && !StringUtil.isEmpty(c.getKey())) {
					res.setParameter(i, c.getValue());
				}
			}
		}
		return res;
	}

}
