package com.skill.service;

import com.skill.dto.Exposer;
import com.skill.dto.SeckillExecution;
import com.skill.entity.Seckill;
import com.skill.exception.RepeatKillException;
import com.skill.exception.SeckillCloseException;
import com.skill.exception.SeckillException;

import java.util.List;

/**
 * User: Shinelon
 * Date: 2016/6/17
 * Time:{Time}
 */

public interface SeckillService {

    /**
     * c查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     */

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException,RepeatKillException,SeckillCloseException;
}
