/**
 * @Time: 2024/8/28 16:44
 * @Author: guoxun
 * @File: UserRecord
 * @Description:
 */

package com.pipi.xojuserservice.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@TableName("user_record")
public class UserRecord {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 题目id
     */
    private Long pid;

    /**
     * 通过标志位
     */
    private Boolean passBit;

    /**
     * 用时(毫秒)
     */
    private Long milliseconds;

    /**
     * 所用内存
     */
    private Long memUse;

    /**
     * 删除标志位
     */
    private Boolean deleteBit;
}
