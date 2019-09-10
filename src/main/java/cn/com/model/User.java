package cn.com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class User {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer id;
	public String name;
	public String password;
	public String phone;
	public Date lasttime;
}
