/**
 * @Time: 2024/9/20 13:54
 * @Author: guoxun
 * @File: TbProblemTemplate
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "problem_template")
public class TbProblemTemplate {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id", unique = true, columnDefinition = "INT8 AUTO_INCREMENT")
    private Long id;

    /**
     * 所对应题目的id
     */
    @Column(name = "pid", columnDefinition = "INT8")
    private Long pid;

    /**
     * 所对应的编程语言
     */
    @Column(name = "language", columnDefinition = "VARCHAR(32)")
    private String language;

    /**
     * 代码模板
     */
    @Column(name = "template", columnDefinition = "TEXT")
    private String template;

    /**
     * 删除位
     */
    @Column(name = "delete_bit", columnDefinition = "TINYINT")
    private Integer deleteBit;
}
