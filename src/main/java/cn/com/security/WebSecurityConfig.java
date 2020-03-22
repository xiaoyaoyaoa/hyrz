package cn.com.security;

import cn.com.dao.UserMapper;
import cn.com.model.Role;
import cn.com.model.SecurityUser;
import cn.com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration      // 声明为配置类
@EnableWebSecurity      // 启用 Spring Security web 安全的功能
@EnableGlobalMethodSecurity(jsr250Enabled = true) //启用权限管理
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Value("${server.context-path}")
    private String path;

    @Override
    protected void configure(HttpSecurity http) throws Exception { //配置策略
        http.csrf().disable().headers().defaultsDisabled().disable().authorizeRequests().anyRequest().permitAll().and()
                .formLogin().loginPage("/login").defaultSuccessUrl(path+"index").permitAll().successHandler(loginSuccessHandler()).
                and().logout().permitAll().logoutSuccessHandler(logoutSuccessHandler()).invalidateHttpSession(true).
                deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
                and().sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
        auth.eraseCredentials(false);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    SecurityUser user = (SecurityUser) authentication.getPrincipal();
                    logger.info("用户: " + user.getUsername() + "登出成功");
                } catch (Exception e) {
                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }
                httpServletResponse.sendRedirect(path+"login");
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                logger.info("用户: " + userDetails.getUsername() + "登陆成功");

                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    public static final List<GrantedAuthority> USER = AuthorityUtils.createAuthorityList(User.role_user);
    public static final List<GrantedAuthority> ADMIN = AuthorityUtils.createAuthorityList(User.role_admin);

    @Bean
    public UserDetailsService userDetailsService() {    //用户登录实现
        return new UserDetailsService() {
            @Autowired
            private UserMapper userMapper;

            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                User user = userMapper.findByUsername(s);
                if (user == null) throw new UsernameNotFoundException("Username " + s + " not found");
                Role role = userMapper.findById(user.getRoles_id());
                if(null != role){
                    user.setAuthorities(AuthorityUtils.createAuthorityList(role.getRolename()));
                }else{
                    user.setAuthorities(USER);
                }
                return new SecurityUser(user);
            }
        };
    }
}