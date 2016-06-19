package com.skill.dao;

import com.skill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Computer on 2016/6/12.
 * 配置Spring和junit 整合 ，junit 启动时加载springIOC 容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void testReduceNumber() throws Exception {
        long id= 1000;
        int result = seckillDao.reduceNumber(id,new Date());
        System.out.println("result:"+result);

    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> list = seckillDao.queryAll(1,4);
        if(list != null && list.size() != 0){
            for (Seckill seckill : list ){
                System.out.println(" "+seckill.toString());
            }

        }

    }

}