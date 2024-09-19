/**
 * @Time: 2024/9/14 16:57
 * @Author: guoxun
 * @File: ChangePasswordDTO
 * @Description:
 */

package com.pipi.xojauthservice.pojo.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 老密码
     */
    private String oldPassword;
}
