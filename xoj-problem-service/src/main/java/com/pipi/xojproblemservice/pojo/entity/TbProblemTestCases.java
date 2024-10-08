/**
 * @Time: 2024/9/20 13:59
 * @Author: guoxun
 * @File: TbProblemTestCases
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

@Data
@Table(name = "problem_test_cases")
@Entity
public class TbProblemTestCases {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id", unique = true, columnDefinition = "INT8 AUTO_INCREMENT")
    private Long id;

    /**
     * 所属题目id
     */
    @Column(name = "pid", columnDefinition = "INT8")
    private Long pid;

    /**
     * 输入用例
     */
    @Column(name = "input", columnDefinition = "TEXT")
    private String input;

    /**
     * 用例输出
     */
    @Column(name = "output", columnDefinition = "TEXT")
    private String output;

}
