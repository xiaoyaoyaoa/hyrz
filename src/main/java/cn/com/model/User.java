package cn.com.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    public static final String role_user = "ROLE_USER";

    public static final String role_admin = "ROLE_ADMIN";
    @Transient
    private List<GrantedAuthority> authorities;
    private Integer id;
    private String username;    //用户名
    private String password;    //用户密码
    private Integer roles_id;   //权限ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(Integer roles_id) {
        this.roles_id = roles_id;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}