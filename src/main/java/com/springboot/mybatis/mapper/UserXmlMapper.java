package com.springboot.mybatis.mapper;

import com.springboot.mybatis.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserXmlMapper {

    int addUser(TestUser user);
}
