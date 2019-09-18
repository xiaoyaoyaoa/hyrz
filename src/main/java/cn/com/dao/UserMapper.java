package cn.com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.model.User;

@Mapper
public interface UserMapper {
	User getUserById(@Param("id") int id);
	User findByUsername(@Param("username") String id);

}
