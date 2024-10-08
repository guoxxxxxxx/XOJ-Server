/**
 * @Time: 2024/9/20 14:20
 * @Author: guoxun
 * @File: ProblemQueryConditionDTO
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ProblemQueryConditionDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 页面尺寸
     */
    private Integer pageSize;

    /**
     * 页面
     */
    private Integer pageNumber;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 难度
     */
    private Integer difficulty;

    /**
     * 题目
     */
    private String title;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 创建时间左区间
     */
    private Date geCreateTime;

    /**
     * 创建时间右区间
     */
    private Date leCreateTime;

}
