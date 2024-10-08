/**
 * @Time: 2024/9/20 17:19
 * @Author: guoxun
 * @File: ProblemDTO
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProblemDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目作者
     */
    private Long uid;

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
     * 题目难度 0：简单 1：中等 2：困难
     */
    private Integer difficulty;
}
