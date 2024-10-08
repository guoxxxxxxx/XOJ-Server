/**
 * @Time: 2024/8/28 15:33
 * @Author: guoxun
 * @File: User
 * @Description:
 */

package com.pipi.xojuserservice.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserInfo {
    /**
     * 主键, 自增
     */
    @Id
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
    private Date birthday;

    /**
     * 居住地
     */
    private String residence;

    /**
     * 性别 0: girl 1: boy
     */
    private Integer gender;

    /**
     * 自我介绍
     */
    private String selfIntroduction;

    /**
     * 个人项目地址
     */
    private String selfWebsite;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学校
     */
    private String schoolName;

    /**
     * 专业
     */
    private String major;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * github 地址
     */
    private String githubAddress;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
    private Date registerTime;

    /**
     * 上次登录ip
     */
    private String lastLoginIp;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 删除位
     */
    private Boolean deleteBit;
}
