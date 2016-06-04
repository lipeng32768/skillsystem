package com.skill.dao;

import com.skill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Computer on 2016/6/4.
 */
public interface SeckillDao {
    /**
     * 减库存,如果影响行数 =1，则表示更新成功
     * @param seckillId
     * @param killTime
     * @return
     */
    public int reduceNumber(long seckillId, Date killTime);

    /**
     *
     * @param seckillId
     * @return
     */
    public Seckill queryById(long seckillId);

    /**
     * 根据
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(int offset, int limit);

}
