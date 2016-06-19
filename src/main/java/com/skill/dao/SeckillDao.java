package com.skill.dao;

import com.skill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

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
    public int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     *
     * @param seckillId
     * @return
     */
    public Seckill queryById(@Param("seckillId") long seckillId);

    /**
     * 根据
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
