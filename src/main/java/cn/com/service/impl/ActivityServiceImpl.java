package cn.com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dao.ActivityMapper;
import cn.com.model.Activity;
import cn.com.model.Member;
import cn.com.model.MemberFight;
import cn.com.service.ActivityService;
import cn.com.service.MemberService;
import cn.com.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired MemberService memberService;
	@Autowired ActivityService activityService;
	
	@Override
	public List<Map<String, Object>> getActTypeByOrganizationId(int organizationId) {
		return activityMapper.getActTypeByOrganizationId(organizationId);
	}
	@Override
	public List<Map<String, Object>> getActDateByOrganizationId(int organizationId) {
		return activityMapper.getActDateByOrganizationId(organizationId);
	}

	@Override
	public List<Activity> getActByOrganizationId(int organizationId,String activityType,String activityDate,String activityEndDate) {
		return activityMapper.getActByOrganizationId(organizationId,activityType,activityDate,activityEndDate);
	}
	@Transactional
	@Override
	public int saveActivity(Activity activity) {
		int add_flag = activityMapper.saveActivity(activity);
		if(add_flag >0){
			Integer activityId = activity.getActivityId();
			Integer activityType = activity.getActivityType();
			Integer organizationId = activity.getOrganizationId();
			if(StringUtil.isEmpty(activityId)){
				add_flag = -1;
				return add_flag;
			}
			//查询组织有效成员列表
			List<Member> memberList = memberService.getMemberList(organizationId,1,1);
			//活动类型(0战力涨幅,1本服要塞,2跨服要塞,3天地战场,4本服争霸,5跨服争霸,6叛忍)
			if(activityType == 0){//0战力涨幅
				//战力涨幅比较特殊,活动时间为范围,以7天为例
				for(Member member: memberList){
					//先查询上次统计的记录用于回填上次战力数据
					MemberFight memberFight = activityMapper.getMemberFightBy7Day(member.getMemberId(), activity.getActivityDate());
					int flag = 0;
					if(null != memberFight){
						flag = activityService.saveFightScore(activityId,member.getMemberId(),memberFight.getEndDate(),activity.getActivityDate(),memberFight.getMemberNowFight());
					}else{
						flag = activityService.saveFightScore(activityId,member.getMemberId(),activity.getActivityDate(),activity.getActivityDate(),0d);
					}
					if (flag <=0 ) {
						add_flag = -1;
						return add_flag;
					}
				};
				
			}else if(activityType == 1){//1本服要塞
				//要塞表中每人插入一条基础数据
				for(Member member: memberList){
					if (activityService.saveActFortScore(activityId, member.getMemberId()) <=0 ) {
						add_flag = -1;
						return add_flag;
					}
				};
			}else if(activityType == 2){//2跨服要塞
				//跨服要塞表中每人插入一条基础数据
				for(Member member: memberList){
					if (activityService.saveActSpanFortScore(activityId, member.getMemberId()) <=0 ) {
						add_flag = -1;
						return add_flag;
					}
				};
			}else if(activityType == 3){//3天地战场
				//跨服要塞表中每人插入一条基础数据
				for(Member member: memberList){
					if (activityService.saveSkyLand(activityId, member.getMemberId()) <=0 ) {
						add_flag = -1;
						return add_flag;
					}
				};
			}else if(activityType == 4){//4本服争霸
				//本服组织争霸表中每人插入一条基础数据(包含本周四场的记录)
				for(Member member: memberList){
					if (activityService.saveConquest(activityId, member.getMemberId(),0) <=0 ) {
						add_flag = -1;
						return add_flag;
					}

				};
			}else if(activityType == 5){//5跨服争霸
				//跨服组织争霸表中每人插入一条基础数据(包含本周四场的记录)
				for(Member member: memberList){
					if (activityService.saveConquest(activityId, member.getMemberId(),1) <=0 ) {
						add_flag = -1;
						return add_flag;
					}

				};
			}else if(activityType == 6){//6叛忍
				//叛忍活动表中每人插入一条基础数据
				for(Member member: memberList){
					if (activityService.saveDefectNinja(activityId, member.getMemberId()) <=0 ) {
						add_flag = -1;
						return add_flag;
					}

				};
			}else{
				return -1;
			}
		}
		return add_flag;
		
	}
	@Override
	public List<Map<String, Object>> getFortsByActivityId(int activityId) {
		return activityMapper.getFortsByActivityId(activityId);
	}
	@Override
	public Activity getActivityById(int activityId) {
		return activityMapper.getActivityById(activityId);
	}
	@Override
	public int updateFortScore(Map<String, Object> params) {
		return activityMapper.updateFortScore(params);
	}
	@Override
	public int saveActFortScore(int activityId, int memberId) {
		return activityMapper.saveActFortScore(activityId, memberId);
	}
	@Override
	public int saveActSpanFortScore(int activityId, int memberId) {
		return activityMapper.saveActSpanFortScore(activityId, memberId);
	}
	@Override
	public List<Map<String, Object>> getSpanFortsByActivityId(int activityId) {
		return activityMapper.getSpanFortsByActivityId(activityId);
	}
	@Override
	public int updateSpanFortScore(Map<String, Object> params) {
		return activityMapper.updateSpanFortScore(params);
	}
	@Override
	public List<Map<String, Object>> getFightByActivityId(int activityId) {
		return activityMapper.getFightByActivityId(activityId);
	}
	@Override
	public MemberFight getMemberFightBy7Day(int memberId, Date  endDate) {
		return activityMapper.getMemberFightBy7Day(memberId, endDate);
	}
	@Override
	public int saveFightScore(int activityId, int memberId,Date beginDate,Date endDate,Double memberBeginFight) {
		return activityMapper.saveFightScore(activityId, memberId, beginDate,endDate,memberBeginFight);
	}
	@Override
	public int saveConquest(int activityId,int memberId,int conType) {
		return activityMapper.saveConquest(activityId,memberId,conType);
	}
	@Override
	public List<Map<String, Object>>  getConquestByActivityId(int activityId) {
		return activityMapper.getConquestByActivityId(activityId);
	}
	@Override
	public int updateConquest(Map<String, Object> params) {
		return activityMapper.updateConquest(params);
	}
	@Override
	public List<Map<String, Object>>  getSkyLandByActivityId(int activityId) {
		return activityMapper.getSkyLandByActivityId(activityId);
	}
	@Override
	public int saveSkyLand(int activityId,int memberId) {
		return activityMapper.saveSkyLand(activityId,memberId);
	}
	@Override
	public int updateSkyLand(Map<String, Object> params) {
		return activityMapper.updateSkyLand(params);
	}

	@Override
	public int saveDefectNinja(int activityId,int memberId) {
		return activityMapper.saveDefectNinja(activityId,memberId);
	}
	@Override
	public List<Map<String, Object>>  getDefectNinjaByActivityId(int activityId) {
		return activityMapper.getDefectNinjaByActivityId(activityId);
	}
	@Override
	public int updateNinja(Map<String, Object> params) {
		return activityMapper.updateNinja(params);
	}
	@Override
	public int deleteActivity(int activityId) {
		return activityMapper.deleteActivity(activityId);
	}

}
