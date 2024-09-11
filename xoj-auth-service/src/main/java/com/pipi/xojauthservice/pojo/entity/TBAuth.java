/**
 * @Time: 2024/9/11 14:12
 * @Author: guoxun
 * @File: AuthInfo
 * @Description:
 */

package com.pipi.xojauthservice.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "auth")
public class TBAuth {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO, value = "id")
    @Column(name = "id", unique = true, columnDefinition = "INT8 AUTO_INCREMENT")
    private Long id;

    /**
     * 用户主键
     */
    @Column(name = "uid", unique = true, columnDefinition = "INT8")
    private Long uid;

    /**
     * 用户密码
     */
    @Column(name = "password", columnDefinition = "VARCHAR(128)")
    private String password;

    /**
     * 最后一次修改时间
     */
    @Column(name = "last_modify_time", columnDefinition = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-8")
    private Date lastModifyTime;

    /**
     * 最后一次修改ip
     */
    @Column(name = "last_modify_ip", columnDefinition = "VARCHAR(128)")
    private String lastModifyIp;

    /**
     * 用户角色
     */
    @Column(name = "role", columnDefinition = "TINYINT DEFAULT 2")
    private Integer role;

    /**
     * 是否被锁定
     */
    @Column(name = "lock_bit", columnDefinition = "BOOL DEFAULT FALSE")
    private Boolean lockBit;

    /**
     * 删除位
     */
    @Column(name = "delete_bit", columnDefinition = "BOOL DEFAULT FALSE")
    private Boolean delete_bit;
}
