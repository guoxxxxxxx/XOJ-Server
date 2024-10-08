package com.pipi.xojproblemservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pipi.xojproblemservice.pojo.domain.ProblemInfo;
import com.pipi.xojproblemservice.pojo.dto.ProblemQueryConditionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProblemMapper extends BaseMapper<ProblemInfo> {

    /**
     * 分页条件查询
     * @param dto 条件
     */
    IPage<ProblemInfo> queryByCondition(@Param("params") ProblemQueryConditionDTO dto, Page<ProblemInfo> page);


    /**
     * 条件查询
     * @param dto 条件
     */
    List<ProblemInfo> queryByCondition(@Param("params") ProblemQueryConditionDTO dto);


}
