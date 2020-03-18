package cn.com.web;


import cn.com.model.Organization;
import cn.com.model.User;
import cn.com.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController{
	@Autowired MemberService memberService;

	@RequestMapping("/index")
	public String index(Model model){
		User user = LoginController.getUser();
		model.addAttribute("user",user);
		//此处展示组织列表
		List<Organization> organizationList = memberService.getAllOrganizations();
		model.addAttribute("organizationList",organizationList);
		return "common/index";
	}

	@RequestMapping("/index.json")
	public @ResponseBody
	Map<String,Object> indexJson(Model model){
		//此处展示组织列表
		Map<String,Object> res=new HashMap<String,Object>();
		List<Organization> organizationList = memberService.getAllOrganizations();
		model.addAttribute("organizationList",organizationList);
		res.put("organizationList",organizationList);
		return res;
	}

}
