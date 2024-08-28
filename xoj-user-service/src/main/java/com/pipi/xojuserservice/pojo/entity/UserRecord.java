/**
 * @Time: 2024/8/28 16:44
 * @Author: guoxun
 * @File: UserRecord
 * @Description:
 */

package com.pipi.xojuserservice.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_record")
@Entity
public class UserRecord {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id", unique = true, columnDefinition = "INT8")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "uid", columnDefinition = "INT8")
    private Long uid;

    /**
     * 题目id
     */
    @Column(name = "pid", columnDefinition = "INT8")
    private Long pid;

    /**
     * 通过标志位
     */
    @Column(name = "pass_bit", columnDefinition = "BOOL")
    private Boolean passBit;

    /**
     * 用时(毫秒)
     */
    @Column(name = "milliseconds", columnDefinition = "INT")
    private Long milliseconds;

    /**
     * 所用内存
     */
    @Column(name = "mem_use", columnDefinition = "INT")
    private Long memUse;

    /**
     * 删除标志位
     */
    @Column(name = "delete_bit", columnDefinition = "BOOL DEFAULT FALSE")
    private Boolean deleteBit;
}
