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
    @Column(name = "id", unique = true, columnDefinition = "INT8 AUTO_INCREMENT")
    private Long id;

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
     * 上次登录ip
     */
    @Column(name = "last_login_ip", columnDefinition = "VARCHAR(128)")
    private String lastLoginIp;

    /**
     * 学号
     */
    @Column(name = "student_id", columnDefinition = "VARCHAR(32)")
    private String studentId;

    /**
     * 删除位
     */
    @Column(name = "delete_bit", columnDefinition = "TINYINT DEFAULT 0")
    private Boolean deleteBit;
}
