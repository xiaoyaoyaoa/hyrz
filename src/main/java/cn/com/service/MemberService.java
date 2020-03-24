package cn.com.service;

import java.util.List;
import java.util.Map;

import cn.com.model.Member;
import cn.com.model.MemberFight;
import cn.com.model.Organization;
import cn.com.page.PageResult;

public interface MemberService{
	/**
	 * 获取组织列表
	 * @return List<Organization>	组织列表
	 */
	List<Organization> getAllOrganizations();
	/**
	 * 通过组织ID获取组织详情
	 * Param organizationId	组织ID
	 * @return Organization	组织详情
	 */
	Organization getOrganizationById(int organizationId);
	/**
	 * 通过成员ID获取成员信息
	 * Param memberId	成员ID
	 * @return Member	成员信息
	 */
	Member getMemberById(int memberId);
	/**
	 * 通过组织ID获取成员列表
	 * Param organizationId	组织ID type 成员类型(-1所有 0离开,1存在) 所有 status 成员状态(-1所有 0学员,1成员) 
	 * @return List<Member>	成员列表
	 */	
	List<Member> getMemberList(int organizationId,int memberType,int memberStatus);

	/**
	 * 通过组织ID获取成员列表 page
	 * Param organizationId	组织ID type 成员类型(-1所有 0离开,1存在) 所有 status 成员状态(-1所有 0学员,1成员)
	 * @return PageResult	成员列表
	 */
	PageResult getMemberPage(int organizationId, int memberType, int memberStatus,int pageNum,int pageSize);
	/**
	 * 修改成员信息
	 * Param Map<String, Object> params	成员信息
	 * @return int 操作标示 0失败其余成功
	 */	
	int updateMember(Map<String, Object> params);
	/**
	 * 通过成员ID获取成员战力列表
	 * Param memberId	成员ID
	 * @return List<MemberFight> 成员战力列表
	 */	
	List<MemberFight> getMemberFightList(int memberId);
	/**
	 * 修改成员战力信息
	 * Param Map<String, Object> params	成员信息
	 * @return int 操作标示 0失败其余成功
	 */		
	int updateMemberFight(Map<String, Object> params);
	/**
	 * 从战力表中获取当前成员最新的战力信息
	 * Param memberId	成员ID
	 * @return MemberFight 成员战力信息
	 */	
	MemberFight getMemberFightNowById(int memberId);

	/**
	 * 新增成员信息
	 * Param Map<String, Object> params	成员信息
	 * @return int 操作标示 0失败其余成功
	 */
	int saveMember(Map<String, Object> params);
	/**
	 * 删除成员信息
	 * Param Map<String, Object> params	成员信息
	 * @return int 操作标示 0失败其余成功
	 */
	int deleteMember(int memberId);
}
