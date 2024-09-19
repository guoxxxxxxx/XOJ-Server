/**
 * @Time: 2024/9/18 17:49
 * @Author: guoxun
 * @File: RetrievePasswordDTO
 * @Description:
 */

package com.pipi.xojauthservice.pojo.dto;


import lombok.Data;

@Data
public class RetrievePasswordDTO {

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户新密码
     */
    private String newPassword;

    /**
     * 验证码
     */
    private String authCode;

}
