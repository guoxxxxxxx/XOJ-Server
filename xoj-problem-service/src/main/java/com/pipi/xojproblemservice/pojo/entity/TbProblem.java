/**
 * @Time: 2024/8/28 17:24
 * @Author: guoxun
 * @File: Problem
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.entity;

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
@Table(name = "problem")
@Entity
public class TbProblem {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id", unique = true, columnDefinition = "INT8")
    private Long id;

    /**
     * 题目标题
     */
    @Column(name = "title", columnDefinition = "VARCHAR(256)")
    private String title;

    /**
     * 题目作者
     */
    @Column(name = "author", columnDefinition = "VARCHAR(64)")
    private String author;

    /**
     * 时间限制
     */
    @Column(name = "time_limit", columnDefinition = "INT")
    private Long timeLimit;

    /**
     * 内存限制
     */
    @Column(name = "mem_limit", columnDefinition = "INT")
    private Long memLimit;

    /**
     * 栈限制
     */
    @Column(name = "stack_limit", columnDefinition = "INT")
    private Long stackLimit;

    /**
     * 题目内容描述
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * 示例
     */
    @Column(name = "examples", columnDefinition = "TEXT")
    private String examples;

    /**
     * 提示信息
     */
    @Column(name = "tips", columnDefinition = "TEXT")
    private String tips;

    /**
     * 题目难度 0：简单 1：中等 2：困难
     */
    @Column(name = "difficulty", columnDefinition = "TINYINT")
    private Integer difficulty;

    /**
     * 题目创建时间
     */
    @Column(name = "create_time", columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 删除标志位
     */
    @Column(name = "delete_bit", columnDefinition = "TINYINT DEFAULT 0")
    private Boolean deleteBit;
}
