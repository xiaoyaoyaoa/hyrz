package cn.com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_MEMBER_FIGHT")
public class MemberFight {
	@Id
	@Column(name = "fightId", unique = true, nullable = false)
	public Integer fightId;//战力流水ID
	public String memberId;//成员ID
	public Double memberBeginFight;//之前战力(单位:万)
	public Double memberNowFight;//目前战力(单位:万)
	public Date beginDate;//统计开始日期
	public Date endDate;//统计结束日期
	public Integer activityId;//活动ID
	public Integer score;//活动打分
	public Integer getFightId() {
		return fightId;
	}
	public void setFightId(Integer fightId) {
		this.fightId = fightId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Double getMemberBeginFight() {
		return memberBeginFight;
	}
	public void setMemberBeginFight(Double memberBeginFight) {
		this.memberBeginFight = memberBeginFight;
	}
	public Double getMemberNowFight() {
		return memberNowFight;
	}
	public void setMemberNowFight(Double memberNowFight) {
		this.memberNowFight = memberNowFight;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
}
