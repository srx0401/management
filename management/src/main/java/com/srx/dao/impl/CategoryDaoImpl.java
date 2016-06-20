package com.srx.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.srx.dao.CategoryDao;
import com.srx.dao.base.impl.BaseDaoImpl;
import com.srx.model.Category;
/**
 * 类别管理数据处理实现
 * @author SIMON
 *
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category, Serializable> implements CategoryDao {

}
