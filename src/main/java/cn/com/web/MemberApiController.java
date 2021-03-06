package cn.com.web;


import cn.com.model.Member;
import cn.com.model.MemberFight;
import cn.com.model.Organization;
import cn.com.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/member")
public class MemberApiController {
	@Autowired MemberService memberService;
	@RequestMapping("/list/{organizationId}")
	public @ResponseBody Map<String,Object> list(Model model, @PathVariable int organizationId){
		Map<String,Object> res=new HashMap<String, Object>();
		Organization organization = memberService.getOrganizationById(organizationId);
		if(null == organization){
			model.addAttribute("errorMsg","未找到该组织");
			return null;
		}
		List<Member> memberList = memberService.getMemberList(organizationId,-1,-1);
		model.addAttribute("memberList",memberList);
		model.addAttribute("organization",organization);
		res.put("memberList",memberList);
		res.put("organization",organization);
		return res;
	}
	@RequestMapping("/update/{memberId}/")
	public String updateMember(Model model, @PathVariable int memberId){
		Member member = memberService.getMemberById(memberId);
		if(null == member){
			model.addAttribute("errorMsg","未找到该成员");
			return "error";
		}
		model.addAttribute("member",member);
		return "member/member";
	}
	@ResponseBody
	@RequestMapping("/doUpdate")
	public int doUpdateMember(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && memberService.updateMember(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}
	@RequestMapping("/fight/{memberId}/")
	public String fight(Model model, @PathVariable int memberId){
		Member member = memberService.getMemberById(memberId);
		if(null == member){
			model.addAttribute("errorMsg","未找到该成员");
			return "error";
		}
		List<MemberFight> memberFights = memberService.getMemberFightList(memberId);
		model.addAttribute("member",member);
		model.addAttribute("memberFights",memberFights);
		return "member/member-fight";
	}
	@ResponseBody
	@RequestMapping("/fight/udpate")
	public int fightUdpate(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && memberService.updateMemberFight(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}
}
