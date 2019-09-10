package cn.com.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.model.Organization;
import cn.com.service.MemberService;


@Controller
@RequestMapping("/organization")
public class OrganizationController{
	@Autowired MemberService memberService;
	
	@RequestMapping("/index/{organizationId}/")
	public String index(Model model, @PathVariable int organizationId){
		Organization organization = memberService.getOrganizationById(organizationId);
		if(null == organization){
			model.addAttribute("errorMsg","未找到该组织");
			return "error";
		}
		model.addAttribute("organization",organization);
		return "organization/organization";
	}
}
