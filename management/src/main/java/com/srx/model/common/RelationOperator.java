package com.srx.model.common;
/**
 * 关系运算符
 * @author SIMON
 *
 */
public enum RelationOperator {
	//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
    //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
	/**
	 * 并且
	 */
	AND("and"), 
	/**
	 * 小于
	 */
	OR("or"),
	
	;
	private final String value;
	//构造器默认也只能是private, 从而保证构造函数只能在内部使用
	RelationOperator(String value) {
        this.value = value;
    }
	public String getValue() {
        return value;
    }
}
