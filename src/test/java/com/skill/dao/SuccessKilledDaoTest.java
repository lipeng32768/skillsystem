package com.skill.dao;

import com.skill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * User: Shinelon
 * Date: 2016/6/19
 * Time:${Time}
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SuccessKilledDao successKilledDao;


    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 100;
        long userphone = 18645693021L;
        int resultCount = successKilledDao.insertSuccessKilled(id, userphone);
        logger.info("resultCount:{}", resultCount);

    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000;
        long userphone = 18645693021L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,userphone);
        logger.info("successKilled:{}", successKilled);
    }

}