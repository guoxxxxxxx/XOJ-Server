/**
 * @Time: 2024/9/20 14:12
 * @Author: guoxun
 * @File: ProblemServiceImpl
 * @Description:
 */

package com.pipi.xojproblemservice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.xojcommon.common.CommonException;
import com.pipi.xojcommon.common.CustomHttpStatus;
import com.pipi.xojcommon.common.PageResult;
import com.pipi.xojproblemservice.mapper.ProblemMapper;
import com.pipi.xojproblemservice.pojo.domain.ProblemInfo;
import com.pipi.xojproblemservice.pojo.dto.ProblemDTO;
import com.pipi.xojproblemservice.pojo.dto.ProblemQueryConditionDTO;
import com.pipi.xojproblemservice.service.ProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, ProblemInfo> implements ProblemService {


    @Override
    public PageResult<ProblemInfo> queryByCondition(ProblemQueryConditionDTO dto) {
        if (dto.getPageSize() != null && dto.getPageNumber() != null) {
             IPage<ProblemInfo> pageInfo = baseMapper
                    .queryByCondition(dto, new Page<>(dto.getPageNumber(), dto.getPageSize()));
             return new PageResult<>(pageInfo);
        }
        else {
            List<ProblemInfo> infoList = baseMapper.queryByCondition(dto);
            return new PageResult<>(infoList);
        }
    }


    @Override
    public void insert(ProblemDTO dto) {
        ProblemInfo problemInfo = new ProblemInfo();
        BeanUtils.copyProperties(dto, problemInfo);
        int insert = baseMapper.insert(problemInfo);
        if (insert == 0){
            throw new CommonException(CustomHttpStatus.INSERT_ERROR);
        }
    }
}
