package cn.com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACTIVITY")
public class Activity {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "activityId", unique = true, nullable = false)
	public Integer activityId;//活动ID
	public Integer activityType;//活动类型(0战力涨幅,1本服要塞,2跨服要塞,3天地战场,4本服争霸,5跨服争霸,6叛忍)
	public Date activityDate;//活动日期
	public String activityName;//活动名称
	public Integer organizationId;//组织ID
	public Integer examine;//考核分数(适用于要塞)
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityType() {
		return activityType;
	}
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getExamine() {
		return examine;
	}
	public void setExamine(Integer examine) {
		this.examine = examine;
	}
	
	

}
