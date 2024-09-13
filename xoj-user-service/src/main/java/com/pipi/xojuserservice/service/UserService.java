/**
 * @Time: 2024/8/30 14:30
 * @Author: guoxun
 * @File: UserService
 * @Description:
 */

package com.pipi.xojuserservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojuserservice.pojo.domain.UserInfo;
import com.pipi.xojuserservice.pojo.dto.LoginDTO;
import com.pipi.xojuserservice.pojo.dto.UserRegisterDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface UserService extends IService<UserInfo> {

    /**
     * 用户注册
     * @param userRegisterDTO 参数
     * @return
     */
    CommonResult register(UserRegisterDTO userRegisterDTO, HttpServletRequest request);


    /**
     * 用户注册时发送验证码
     * @param email 所要发送验证码的邮箱
     * @return
     */
    CommonResult registerSendAuthCode(String email);


    /**
     * 通过邮箱查询用户详细信息
     * @param email 用户邮箱
     * @return 用户详细信息 json
     */
    String queryByEmail(String email);


    /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserInfo queryById(Integer id);
}
