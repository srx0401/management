package com.srx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srx.dao.CategoryDao;
import com.srx.model.Category;
import com.srx.service.CategoryService;
import com.srx.service.base.impl.BaseServiceImpl;
/**
 * 类型管理业务处理实现
 * @author SIMON
 *
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category, Serializable> implements CategoryService {
	private static Log log = LogFactory.getLog(CategoryServiceImpl.class);
	@Autowired
	private CategoryDao categoryDao;
}
