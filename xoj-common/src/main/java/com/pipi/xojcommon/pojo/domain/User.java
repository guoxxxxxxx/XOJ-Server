/**
 * @Time: 2024/8/28 15:33
 * @Author: guoxun
 * @File: User
 * @Description:
 */

package com.pipi.xojcommon.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {


    /**
     * 主键, 自增
     */
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 删除位
     */
    private Boolean deleteBit;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 用户权限
     */
    private String authorities;

    /**
     * 最近登录的ip地址
     */
    private String loginIp;

    /**
     * 最近登录的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 用户账户是否被锁定，true锁定， false未被锁定
     */
    private Boolean isLock;

    /**
     * 用于判断当前操作是否成功
     */
    @TableField(exist = false)
    private Boolean isSuccessful;
}
