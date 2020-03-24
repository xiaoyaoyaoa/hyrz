package cn.com.web;


import cn.com.model.Organization;
import cn.com.model.User;
import cn.com.service.MemberService;
import cn.com.util.StringUtil;
import com.alibaba.fastjson.JSON;
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
	Map<String,Object> indexJson(Model model,HttpServletResponse response,HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Token,Accept, Connection, User-Agent, Cookie");
        Map<String,Object> res=new HashMap<String,Object>();

        System.out.println(request.getMethod());
        String auth=request.getHeader("Authorization");
        if(StringUtil.isEmpty(auth)){
            res.put("code",401);
            return res;
        }else{
            System.err.println("auth:"+auth);
        }
		
		List<Organization> organizationList = memberService.getAllOrganizations();
		model.addAttribute("organizationList",organizationList);
		res.put("organizationList",organizationList);
		return res;
	}


	@RequestMapping("/jsonpTest")
	public @ResponseBody
	String jsonpTest(Model model,HttpServletRequest request,HttpServletResponse response){
		String callback =request.getParameter("callback");
		List<Organization> organizationList = memberService.getAllOrganizations();
        String ooo = JSON.toJSONString(organizationList);
		System.err.println(callback+"("+ooo+")");
		return callback+"("+ooo+")";
	}
}
