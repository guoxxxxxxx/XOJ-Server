/**
 * @Time: 2024/8/28 15:33
 * @Author: guoxun
 * @File: User
 * @Description:
 */

package com.pipi.xojuserservice.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "user")
@Entity
public class TbUser {


    /**
     * 主键, 自增
     */
    @Id
    @TableId(type = IdType.AUTO, value = "id")
    @Column(name = "id", unique = true, columnDefinition = "INT8")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(128)")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", columnDefinition = "VARCHAR(512)")
    private String password;

    /**
     * salt
     */
    @Column(name = "salt", columnDefinition = "VARCHAR(512)")
    private String salt;

    /**
     * 昵称
     */
    @Column(name = "nickname", columnDefinition = "VARCHAR(64)")
    private String nickname;

    /**
     * 生日
     */
    @Column(name = "birthday", columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
    private Date birthday;

    /**
     * 居住地
     */
    @Column(name = "residence", columnDefinition = "VARCHAR(512)")
    private String residence;

    /**
     * 性别 0: girl 1: boy
     */
    @Column(name = "gender", columnDefinition = "TINYINT DEFAULT 1")
    private Integer gender;

    /**
     * 自我介绍
     */
    @Column(name = "self_introduction", columnDefinition = "VARCHAR(1024)")
    private String selfIntroduction;

    /**
     * 个人项目地址
     */
    @Column(name = "self_website", columnDefinition = "VARCHAR(8192)")
    private String selfWebsite;

    /**
     * 手机号
     */
    @Column(name = "phone_number", columnDefinition = "VARCHAR(16)")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(256)")
    private String email;

    /**
     * 学校
     */
    @Column(name = "school_name", columnDefinition = "VARCHAR(64)")
    private String schoolName;

    /**
     * 专业
     */
    @Column(name = "major", columnDefinition = "VARCHAR(64)")
    private String major;

    /**
     * 真实姓名
     */
    @Column(name = "real_name", columnDefinition = "VARCHAR(16)")
    private String realName;

    /**
     * github 地址
     */
    @Column(name = "github_address", columnDefinition = "VARCHAR(512)")
    private String githubAddress;

    /**
     * 注册时间
     */
    @Column(name = "register_time", columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
    private Date registerTime;

    /**
     * 删除位
     */
    @Column(name = "delete_bit", columnDefinition = "BOOL DEFAULT FALSE")
    private Boolean deleteBit;

    /**
     * 用户角色
     */
    @Column(name = "role", columnDefinition = "VARCHAR(16)")
    private String role;

    /**
     * 用户权限
     */
    @Column(name = "authorities", columnDefinition = "INT2")
    private Integer authorities;

    /**
     * 最近登录的ip地址
     */
    @Column(name = "login_ip", columnDefinition = "VARCHAR(256)")
    private String loginIP;

    /**
     * 最近登录的时间
     */
    @Column(name = "login_time", columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;


}
