package cn.com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_MEMBER")
public class Member {
	@Id
	@Column(name = "memberId", unique = true, nullable = false)
	public Integer memberId;//成员ID
	public Integer organizationId;//组织ID
	public String memberName;//成员姓名
	public String memberNick;//成员昵称
	public String memberJob;//成员职位
	public Integer memberType;//成员类型(0离开,1存在)
	public Integer memberStatus;//成员状态(0学员,1成员)
	public Date memberDate;//加入时间
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getMemberJob() {
		return memberJob;
	}
	public void setMemberJob(String memberJob) {
		this.memberJob = memberJob;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}

}
