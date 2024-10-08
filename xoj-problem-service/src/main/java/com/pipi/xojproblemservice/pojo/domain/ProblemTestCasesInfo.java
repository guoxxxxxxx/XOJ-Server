/**
 * @Time: 2024/9/20 13:59
 * @Author: guoxun
 * @File: ProblemTestCases
 * @Description:
 */

package com.pipi.xojproblemservice.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProblemTestCasesInfo {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属题目id
     */
    private Long pid;

    /**
     * 输入用例
     */
    private String input;

    /**
     * 用例输出
     */
    private String output;

}
