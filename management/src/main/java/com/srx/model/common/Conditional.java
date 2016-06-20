package com.srx.model.common;

import java.util.Date;
import java.util.List;

import com.srx.utils.string.StringUtil;

public class Conditional {
	/**
	 * 属性名
	 */
	private String key;
	/**
	 * 属性值
	 */
	private Object value;
	private List<Conditional> orConditionals;
	/**
	 * 逻辑运算符
	 */
	private LogicOperator logicOperator = LogicOperator.EQUAL;
	/**
	 * 关系运算符
	 */
	private RelationOperator relationOperator = RelationOperator.AND;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	
	
	
	public Conditional() {
		super();
	}
	public Conditional(String key, Object value, LogicOperator logicOperator) {
		super();
		this.key = key;
		this.value = value;
		this.logicOperator = logicOperator;
	}
	public Conditional(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getHql(){
		StringBuffer res = new StringBuffer(" ");
		res.append(this.getRelationOperator()).append(" ");
		res.append(this.getKey()).append(" ");
		res.append(this.getLogicOperator().getValue()).append(" ");
		res.append("? ");
		return res.toString();
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public LogicOperator getLogicOperator() {
		return logicOperator;
	}
	public void setLogicOperator(LogicOperator logicOperator) {
		this.logicOperator = logicOperator;
	}
	public RelationOperator getRelationOperator() {
		return relationOperator;
	}
	public void setRelationOperator(RelationOperator relationOperator) {
		this.relationOperator = relationOperator;
	}
	public List<Conditional> getOrConditionals() {
		return orConditionals;
	}
	public void setOrConditionals(List<Conditional> orConditionals) {
		this.orConditionals = orConditionals;
	}
	public Conditional addOrConditional(Conditional orConditional) {
		this.orConditionals.add(orConditional);
		return this;
	}
}
