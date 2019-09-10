package cn.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORGANIZATION")
public class Organization {
	@Id
	@Column(name = "organizationId", unique = true, nullable = false)
	public Integer organizationId;//组织ID
	public String organizationName;//组织名称
	public String currentLeader;//现任首领
	public String notice;//组织公告
	public Integer status;//是否有效0无效1有效
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getCurrentLeader() {
		return currentLeader;
	}
	public void setCurrentLeader(String currentLeader) {
		this.currentLeader = currentLeader;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
