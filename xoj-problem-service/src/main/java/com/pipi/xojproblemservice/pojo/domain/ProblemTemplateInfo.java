/**
 * @Time: 2024/9/20 13:54
 * @Author: guoxun
 * @File: ProblemTemplate
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProblemTemplateInfo {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所对应题目的id
     */
    private Long pid;

    /**
     * 所对应的编程语言
     */
    private String language;

    /**
     * 代码模板
     */
    private String template;

    /**
     * 删除位
     */
    private Integer deleteBit;
}
