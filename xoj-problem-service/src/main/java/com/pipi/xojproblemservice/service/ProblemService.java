package com.pipi.xojproblemservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pipi.xojcommon.common.PageResult;
import com.pipi.xojproblemservice.pojo.domain.ProblemInfo;
import com.pipi.xojproblemservice.pojo.dto.ProblemDTO;
import com.pipi.xojproblemservice.pojo.dto.ProblemQueryConditionDTO;

public interface ProblemService extends IService<ProblemInfo> {

    /**
     * 分页条件查询题目信息
     * @param dto
     * @return
     */
    PageResult<ProblemInfo> queryByCondition(ProblemQueryConditionDTO dto);


    /**
     * 插入新的题目
     * @param dto 题目信息
     */
    void insert(ProblemDTO dto);
}
