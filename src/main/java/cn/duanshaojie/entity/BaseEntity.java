package cn.duanshaojie.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import cn.duanshaojie.utils.DataUtil;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	//已用于保存创建保单用户userId
	@Column(name = "created_by")
	protected String createdBy;

	@Column(name = "creation_time", columnDefinition = "datetime(3)")
	protected Date creationTime;

	@Column(name = "modified_by")
	protected String modifiedBy;

	@Column(name = "modification_time", columnDefinition = "datetime(3)")
	protected Date modificationTime;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	@PrePersist
	protected void prePersist() {
		this.creationTime = DataUtil.getDate();
		this.modificationTime = DataUtil.getDate();
		this.modifiedBy = "admin";
	}

	@PreUpdate
	protected void preUpdate() {
		this.modificationTime = DataUtil.getDate();
		this.modifiedBy = "admin";
	}
}
