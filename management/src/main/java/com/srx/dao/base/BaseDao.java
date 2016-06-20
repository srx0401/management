package com.srx.dao.base;

import java.io.Serializable;
import java.util.List;

import com.srx.model.common.Conditional;
import com.srx.model.common.Page;

public interface BaseDao<T,PK extends Serializable> {
	
	PK save(T t);
	T update(T t);
	T saveOrUpdate(T t);
	boolean remove(T t);
	boolean remove(PK id);
	
	T get(PK id);
	/**
	 * 根据指定条件查询
	 * @param conditionals	查询条件
	 * @return
	 */
	List<T> list(final List<Conditional> conditionals);
	/**
	 * 根据指定条件查询(分页)
	 * @currentPage			当前页码
	 * @pageSize			每页显示量
	 * @param conditionals	查询条件
	 * @return
	 */
	Page<T> list(int currentPage,int pageSize,final List<Conditional> conditionals);
	/**
	 * 	指定属性值(=)查询
	 * @param propertyName	属性名
	 * @param propertyValue	属性值
	 * @return
	 */
	List<T> listByPropertyEqual(String propertyName,String propertyValue);
	/**
	 * 	指定属性值(=)分页查询
	 * @param propertyName	属性名
	 * @param propertyValue	属性值
	 * @currentPage			当前页码
	 * @pageSize			每页显示量
	 * @return
	 */
	Page<T> listByPropertyEqual(String propertyName,String propertyValue,int currentPage,int pageSize);
	
	//	finance
}
