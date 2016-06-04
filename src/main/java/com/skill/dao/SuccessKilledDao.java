package com.skill.dao;

import com.skill.entity.SuccessKilled;

/**
 * Created by Computer on 2016/6/4.
 */
public interface SuccessKilledDao {
    /**
     * c插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    public int insertSuccessKilled(long seckillId, long userPhone);

    /**
     * 根据id c查询 SuccessSeckill 并携带出 Seckill
     * @param seckillId
     * @return SuccessKilled
     */
    public SuccessKilled queryByIdWithSeckill(long seckillId);
}
