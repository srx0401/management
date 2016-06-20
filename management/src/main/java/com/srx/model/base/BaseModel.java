package com.srx.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
@MappedSuperclass
public class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4796253249533328263L;
	@Id
//	@Column(length = 64, nullable = true)  
    @GeneratedValue(generator = "uuid")  
    @GenericGenerator(name = "uuid", strategy = "uuid")  
	private String id;
	@Column(updatable=false)
	private Date createTime;
	@Column
	private Date modifyTime;
	@Column
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public int hashCode() {
		return id == null || id.trim().length() < 1 
				? System.identityHashCode(this) : id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {  
            return true;  
        }  
        if (obj == null) {  
            return false;  
        }  
        if (getClass().getPackage() != obj.getClass().getPackage()) {  
            return false;  
        }  
        final BaseModel other = (BaseModel) obj;  
        if (id == null) {  
            if (other.getId() != null) {  
                return false;  
            }  
        } else if (!id.equals(other.getId())) {  
            return false;  
        }  
        return true;  
	}
	
	
}
