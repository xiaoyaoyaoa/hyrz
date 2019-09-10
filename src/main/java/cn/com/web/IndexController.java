package cn.com.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.model.Organization;
import cn.com.service.MemberService;


@Controller
public class IndexController{
	@Autowired MemberService memberService;
	
	@RequestMapping("/index")
	public String index(Model model){
		//此处展示组织列表
		List<Organization> organizationList = memberService.getAllOrganizations();
		model.addAttribute("organizationList",organizationList);
		return "common/index";
	}
}
