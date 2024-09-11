package com.pipi.xojauthservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pipi.xojauthservice.pojo.domain.AuthInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuthMapper extends BaseMapper<AuthInfo> {
}
