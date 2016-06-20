package com.srx.model.common;
/**
 * 逻辑运算符
 * @author SIMON
 *
 */
public enum LogicOperator {
	//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
    //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
	/**
	 * 大于
	 */
	GREATER(">"), 
	/**
	 * 小于
	 */
	LESS("<"),
	/**
	 * 等于
	 */
	EQUAL("="),
	/**
	 * 不等于
	 */
	NOT_EQUAL("!="),
	/**
	 * 大于等于
	 */
	GREATER_OR_EQUAL(">="),
	/**
	 * 小于等于
	 */
	LESS_OR_EQUAL(">="),
	/**
	 * 存在于一个数据集合中
	 */
	IN("in"),
	/**
	 * 不存在于一个数据集合中
	 */
	NOT_IN("not in"),
	/**
	 * 	模糊匹配
	 */
	LIKE("like"),
	;
	private final String value;
	//构造器默认也只能是private, 从而保证构造函数只能在内部使用
	LogicOperator(String value) {
        this.value = value;
    }
	public String getValue() {
        return value;
    }
}
