package com.srx.service.base;

import java.io.Serializable;
import java.util.List;

import com.srx.model.common.Conditional;
import com.srx.model.common.Page;

public interface BaseService<T,PK extends Serializable> {
	/**
	 * 保存
	 * @param t
	 * @return
	 * @throws Exception
	 */
	PK save(T t) throws Exception;
	/**
	 * 修改
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T update(T t)throws Exception;
	/**
	 * 保存或修改
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T saveOrUpdate(T t)throws Exception;
	/**
	 * 删除指定对象
	 * @param t
	 * @return
	 * @throws Exception
	 */
	boolean remove(T t)throws Exception;
	/**
	 * 删除指定主键的对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean remove(PK id)throws Exception;
	/**
	 * 删除指定主键集合的对象
	 * @param ids	
	 * @return
	 * @throws Exception
	 */
	boolean remove(List<PK> ids)throws Exception;
	/**
	 * 查询指定主键的对象
	 * @param id
	 * @return
	 */
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
	
}
