package cn.com.web;


import cn.com.model.Organization;
import cn.com.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api/organization")
public class OrganizationApiController {
	@Autowired MemberService memberService;

	@RequestMapping("/index/{organizationId}")
	public @ResponseBody
	Map<String,Object> index(Model model, @PathVariable int organizationId){
		Map<String,Object> res=new HashMap<String, Object>();
		Organization organization = memberService.getOrganizationById(organizationId);
		if(null == organization){
			model.addAttribute("errorMsg","未找到该组织");
			return null;
		}
		model.addAttribute("organization",organization);
		res.put("organization",organization);
		return res;
	}
}
