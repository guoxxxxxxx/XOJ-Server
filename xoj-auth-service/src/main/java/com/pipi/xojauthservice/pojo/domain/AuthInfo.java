/**
 * @Time: 2024/9/11 14:23
 * @Author: guoxun
 * @File: AuthInfo
 * @Description:
 */

package com.pipi.xojauthservice.pojo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@TableName("auth")
public class AuthInfo {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;

    /**
     * 用户主键
     */
    private Long uid;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 最后一次修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
    private Date lastModifyTime;

    /**
     * 最后一次修改ip
     */
    private String lastModifyIp;

    /**
     * 用户角色
     */
    private Integer role;

    /**
     * 是否被锁定
     */
    private Boolean lockBit;

    /**
     * 删除位
     */
    private Boolean delete_bit;
}
