package cn.com.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dao.MemberMapper;
import cn.com.model.Member;
import cn.com.model.MemberFight;
import cn.com.model.Organization;
import cn.com.service.MemberService;
import org.springframework.transaction.annotation.Transactional;


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
	public PageResult getMemberPage(int organizationId, int memberType, int memberStatus,int pageNum,int pageSize) {
		PageResult pageResult = new PageResult();
		//此处为分页逻辑
		//查询总记录数
		Integer count = memberMapper.getMemberCount(organizationId, memberType, memberStatus);
		//总页码数 count/pageSize 除法进一
		int totalPage = (int)Math.ceil((double)count/(double)pageSize);
		//如果当前页码大于总页码，则当前页码等于总页码
		if(pageNum>totalPage){
			pageNum = totalPage;
		}
		//计算limit开始位置 (pageNum-1)*pageSize
		int start = (pageNum-1)*pageSize;
		//查询数据总条数
		List<Member> list = memberMapper.getMemberPage(organizationId, memberType, memberStatus,start,pageSize);
		pageResult.setContent(list);//记录List
		pageResult.setTotalSize(count);//总记录数
		pageResult.setPageNum(pageNum);//当前页码
		pageResult.setPageSize(pageSize);//每页数量
		pageResult.setTotalPages(totalPage);//总页码数
		//下一页页码
		if (totalPage>pageNum){
			pageResult.setNextPageNum(pageNum+1);//下一页页码
			pageResult.setIsNextPage(true);//是否还有下一页
		}else{
			pageResult.setIsNextPage(false);//是否还有下一页
		}
		//上一页页码
		if (pageNum>1){
			pageResult.setPrevPageNum(pageNum-1); //上一页页码
			pageResult.setIsPrevPage(true);//是否还有上一页
		}else{
			pageResult.setIsPrevPage(false);//是否还有上一页
		}
		//查询所有数据
		return pageResult;
	}

	@Override
	public int updateMember(Map<String, Object> params) {
		return memberMapper.updateMember(params);
	}

	@Override
	public List<MemberFight> getMemberFightList(int memberId) {
		return memberMapper.getMemberFightList(memberId);
	}

	@Transactional
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