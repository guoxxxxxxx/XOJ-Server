/**
 * @Time: 2024/8/28 17:24
 * @Author: guoxun
 * @File: Problem
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("problem")
public class Problem {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目作者
     */
    private String author;

    /**
     * 时间限制
     */
    private Long timeLimit;

    /**
     * 内存限制
     */
    private Long memLimit;

    /**
     * 栈限制
     */
    private Long stackLimit;

    /**
     * 题目内容描述
     */
    private String description;

    /**
     * 示例
     */
    private String examples;

    /**
     * 提示信息
     */
    private String tips;

    /**
     * 题目难度 0：简单 1：中等 2：困难
     */
    private Integer difficulty;

    /**
     * 题目创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 删除标志位
     */
    private Boolean deleteBit;
}
