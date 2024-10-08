/**
 * @Time: 2024/9/20 14:10
 * @Author: guoxun
 * @File: ProblemController
 * @Description:
 */

package com.pipi.xojproblemservice.controller;

import com.pipi.xojcommon.aop.annotation.Logger;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojcommon.common.PageResult;
import com.pipi.xojproblemservice.pojo.domain.ProblemInfo;
import com.pipi.xojproblemservice.pojo.dto.ProblemDTO;
import com.pipi.xojproblemservice.pojo.dto.ProblemQueryConditionDTO;
import com.pipi.xojproblemservice.service.ProblemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;


    @Logger("分页条件查询所有题目")
    @ApiOperation("分页条件查询所有题目")
    @GetMapping("")
    public CommonResult queryByCondition(ProblemQueryConditionDTO dto){
        PageResult<ProblemInfo> result = problemService.queryByCondition(dto);
        return new CommonResult().success().message("查询成功").data(result);
    }


    @Logger("新增题目信息")
    @ApiOperation("新增题目信息")
    @PostMapping("/insert")
    public CommonResult insert(@RequestBody ProblemDTO dto){
        problemService.insert(dto);
        return new CommonResult().success().message("插入成功");
    }

}
