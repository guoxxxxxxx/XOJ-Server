/**
 * @Time: 2024/8/30 15:37
 * @Author: guoxun
 * @File: UserRegisterDTO
 * @Description:
 */

package com.pipi.xojuserservice.pojo.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String authCode;
}
