package cn.com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.model.Activity;
import cn.com.model.MemberFight;

public interface ActivityService{
	
	/**
	 * 通过组织ID获取组织活动类型
	 * Param organizationId	组织ID
	 * @return List<Map<String,Object>> 组织活动类型
	 */
	List<Map<String,Object>> getActTypeByOrganizationId(int organizationId);
	/**
	 * 通过组织ID获取组织活动日期
	 * Param organizationId	组织ID
	 * @return List<Map<String,Object>> 组织活动日期
	 */
	List<Map<String,Object>> getActDateByOrganizationId(int organizationId);
	/**
	 * 通过组织ID获取组织活动详情
	 * Param organizationId	组织ID
	 * @return List<Activity> 组织活动详情
	 */
	List<Activity> getActByOrganizationId(int organizationId,String activityType,String activityDate,String activityEndDate);
	/**
	 * 新增活动记录
	 * Map<String, Object> params 活动信息
	 * @return int 操作标示 0失败其余成功
	 */
	int saveActivity(Activity activity);
	/**
	 * 通过活动ID获取本服要塞数据
	 * Param activityId	活动ID
	 * @return List<Map<String,Object>> 本服要塞数据
	 */
	List<Map<String,Object>> getFortsByActivityId(int activityId);
	/**
	 * 通过活动ID获取活动详情
	 * Param activityId	活动ID
	 * @return Activity 活动详情
	 */
	Activity getActivityById(int activityId);
	/**
	 * 更新本服要塞活动记录
	 * Map<String, Object> params 活动信息
	 * @return int 操作标示 0失败其余成功
	 */
	int updateFortScore(Map<String, Object> params);
	/**
	 * 新增本服要塞活动记录
	 * Param int activityId 活动ID,int memberId	成员ID
	 * @return int 操作标示 0失败其余成功
	 */
	int saveActFortScore(int activityId,int memberId);
	/**
	 * 新增跨服要塞活动记录
	 * Param int activityId 活动ID,int memberId	成员ID
	 * @return int 操作标示 0失败其余成功
	 */
	int saveActSpanFortScore(int activityId,int memberId);
	/**
	 * 通过活动ID获取跨服要塞数据
	 * Param activityId	活动ID
	 * @return List<Map<String,Object>> 跨服要塞数据
	 */
	List<Map<String,Object>> getSpanFortsByActivityId(int activityId);
	
	/**
	 * 更新跨服要塞活动记录
	 * Map<String, Object> params 活动信息
	 * @return int 操作标示 0失败其余成功
	 */
	int updateSpanFortScore(Map<String, Object> params);	
	/**
	 * 通过活动ID获取战力增长数据
	 * Param activityId	活动ID
	 * @return List<Map<String,Object>> 战力增长数据
	 */
	List<Map<String,Object>> getFightByActivityId(int activityId);
	/**
	 * 通过日期差值获取战力增长数据
	 * Param memberId	成员ID endDate 上次的截止日期
	 * @return MemberFight 战力增长数据
	 */
	MemberFight getMemberFightBy7Day(int memberId,Date endDate);
	/**
	 * 新增战力涨幅活动记录
	 * Param int activityId 活动ID,int memberId	成员ID
	 * @return int 操作标示 0失败其余成功
	 */
	int saveFightScore(int activityId, int memberId,Date beginDate,Date endDate,Double memberBeginFight);

	/**
	 * 新增争霸活动明细记录
	 * Param int activityId 活动ID,int memberId	成员ID type 争霸类型0本服1跨服
	 * @return int 操作标示 0失败其余成功
	 */
	int saveConquest(int activityId,int memberId,int conType);
	/**
	 * 通过活动ID获取组织争霸详细数据
	 * Param activityId	活动ID
	 * @return List<Map<String,Object>> 组织争霸数据
	 */
	List<Map<String,Object>> getConquestByActivityId(int activityId);
	/**
	 * 更新争霸活动记录
	 * Map<String, Object> params 活动信息
	 * @return int 操作标示 0失败其余成功
	 */
	int updateConquest(Map<String, Object> params);

	/**
	 * 通过活动ID获取天地战场数据
	 * Param activityId	活动ID
	 * @return List<Map<String,Object>> 天地战场数据
	 */
	List<Map<String,Object>> getSkyLandByActivityId(int activityId);
	/**
	 * 新增天地战场明细记录
	 * Param int activityId 活动ID,int memberId	成员ID
	 * @return int 操作标示 0失败其余成功
	 */
	int saveSkyLand(int activityId,int memberId);
	/**
	 * 更新天地活动记录
	 * Map<String, Object> params 活动信息
	 * @return int 操作标示 0失败其余成功
	 */
	int updateSkyLand(Map<String, Object> params);
	/**
	 * 新增叛忍活动明细记录
	 * Param int activityId 活动ID,int memberId	成员ID
	 * @return int 操作标示 0失败其余成功
	 */
	int saveDefectNinja(int activityId,int memberId);

	/**
	 * 通过活动ID获取叛忍数据
	 * Param activityId	活动ID
	 * @return List<Map<String,Object>> 叛忍数据
	 */
	List<Map<String,Object>> getDefectNinjaByActivityId(int activityId);
	/**
	 * 更新叛忍活动记录
	 * Map<String, Object> params 活动信息
	 * @return int 操作标示 0失败其余成功
	 */
	int updateNinja(Map<String, Object> params);

    /**
     * 删除活动
     * Param int activityId 活动ID
     * @return int 操作标示 0失败其余成功
     */
    int deleteActivity(int activityId);

}
