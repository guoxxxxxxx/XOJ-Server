package com.pipi.xojuserservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pipi.xojcommon.pojo.domain.User;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface UserMapper extends BaseMapper<User> {
}
