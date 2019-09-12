package cn.com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.model.Member;
import cn.com.model.MemberFight;
import cn.com.model.Organization;

@Mapper
public interface MemberMapper {
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
	Organization getOrganizationById(@Param("organizationId") int organizationId);
	/**
	 * 通过成员ID获取成员信息
	 * Param memberId	成员ID
	 * @return Member	成员信息
	 */
	Member getMemberById(@Param("memberId") int memberId);
	/**
	 * 通过组织ID获取成员列表
	 * Param organizationId	组织ID
	 * @return List<Member>	成员列表
	 */	
	List<Member> getMemberList(@Param("organizationId") int organizationId,@Param("memberType") int memberType,@Param("memberStatus") int memberStatus);
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
	List<MemberFight> getMemberFightList(@Param("memberId") int memberId);
	/**
	 * 修改成员战力信息
	 * Param Map<String, Object> params	成员信息
	 * @return int 操作标示 0失败其余成功
	 */	
	int updateMemberFight(Map<String, Object> params);
	/**
	 * 更新战力之前一期的战力
	 * Param Map<String, Object> params	战力信息(当前的战力存为上期的之前战力)
	 * @return int 操作标示 0失败其余成功
	 */	
	int updateMemberFightLink(Map<String, Object> params);
	/**
	 * 从战力表中获取当前成员最新的战力信息
	 * Param memberId	成员ID
	 * @return MemberFight 成员战力信息
	 */	
	MemberFight getMemberFightNowById(@Param("memberId") int memberId);
	/**
	 * 更新战力表当前战力
	 * Param fightId	战力流水ID	memberNowFight	当前战力
	 * @return int 操作标示 0失败其余成功
	 */	
	int updateMemberFightNew(@Param("fightId") int fightId,@Param("memberNowFight") double memberNowFight);

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
	int deleteMember(Map<String,Object> params);
}
