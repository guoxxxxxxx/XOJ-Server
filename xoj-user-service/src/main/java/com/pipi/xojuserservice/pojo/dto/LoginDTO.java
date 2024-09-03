/**
 * @Time: 2024/9/3 14:27
 * @Author: guoxun
 * @File: LoginDTO
 * @Description:
 */

package com.pipi.xojuserservice.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    /**
     * 用户名
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}
