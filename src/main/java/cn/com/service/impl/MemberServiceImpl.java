package cn.com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dao.MemberMapper;
import cn.com.model.Member;
import cn.com.model.MemberFight;
import cn.com.model.Organization;
import cn.com.service.MemberService;



@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<Organization> getAllOrganizations() {
		return memberMapper.getAllOrganizations();
	}

	@Override
	public Organization getOrganizationById(int organizationId) {
		return memberMapper.getOrganizationById(organizationId);
	}

	@Override
	public Member getMemberById(int memberId) {
		return memberMapper.getMemberById(memberId);
	}

	@Override
	public List<Member> getMemberList(int organizationId, int memberType, int memberStatus) {
		return memberMapper.getMemberList(organizationId, memberType, memberStatus);
	}

	@Override
	public int updateMember(Map<String, Object> params) {
		return memberMapper.updateMember(params);
	}

	@Override
	public List<MemberFight> getMemberFightList(int memberId) {
		return memberMapper.getMemberFightList(memberId);
	}

	@Override
	public int updateMemberFight(Map<String, Object> params) {
		int fight = memberMapper.updateMemberFight(params);
		memberMapper.updateMemberFightLink(params);
		return fight;
	}

	@Override
	public MemberFight getMemberFightNowById(int memberId) {
		return memberMapper.getMemberFightNowById(memberId);
	}

	@Override
	public int saveMember(Map<String, Object> params) {
		return memberMapper.saveMember(params);
	}
	@Override
	public int deleteMember(int memberId) {
		return memberMapper.deleteMember(memberId);
	}
}