package cn.com.web;

import cn.com.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,
            @RequestParam(value = "error", required = false) String error) {
        String msg = "";
        if (error != null) {
            msg = "不正确的用户名和密码";

        }
        model.addAttribute("msg", msg);
        return "login";
    }
    public static User getUser() { //为了session从获取用户信息,可以配置如下
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            User user = (User) auth.getPrincipal();
            return user;
        }
        return null;
    }
    /**
     *
     * @description 功能描述: 获取用户信息
     * @return 返回类型:User
     * @createdate 建立日期：2019年9月20日13:52:32
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public @ResponseBody User getUserInfo() {
        return getUser();
    }
}