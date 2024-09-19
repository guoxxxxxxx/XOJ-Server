package com.pipi.xojauthservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pipi.xojauthservice.pojo.domain.AuthInfo;
import com.pipi.xojauthservice.pojo.dto.ChangePasswordDTO;
import com.pipi.xojauthservice.pojo.dto.LoginDTO;
import com.pipi.xojauthservice.pojo.dto.RetrievePasswordDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface AuthService extends IService<AuthInfo> {

    /**
     * 用户登录
     * @param loginDTO 登录参数
     * @param request request
     * @return
     */
    Map<String, String> login(LoginDTO loginDTO, HttpServletRequest request);


    /**
     * 注册时保存用户密码
     * @param infoJson 加密后的用户密码，uid, lastModifyIp
     * @return 插入状态
     */
    Boolean savePassword(String infoJson);


    /**
     * 修改用户密码
     * @param dto 信息
     * @return 是否成功
     */
    Boolean changePassword(ChangePasswordDTO dto);


    /**
     * 获取找回密码的随机验证码
     * @param email 用户邮箱
     */
    void getRetrievePasswordAuthCode(String email) throws Exception;


    /**
     * 找回密码
     * @param dto dto
     */
    void retrievePassword(RetrievePasswordDTO dto);
}
