/**
 * @Time: 2024/8/30 14:30
 * @Author: guoxun
 * @File: UserService
 * @Description:
 */

package com.pipi.xojuserservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojuserservice.pojo.domain.User;
import com.pipi.xojuserservice.pojo.dto.LoginDTO;
import com.pipi.xojuserservice.pojo.dto.UserRegisterDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.Objects;

public interface UserService extends IService<User> {
    CommonResult register(UserRegisterDTO userRegisterDTO);

    CommonResult registerSendAuthCode(String email);

    Map<String, String> login(LoginDTO loginDTO, HttpServletRequest request);
}
