package com.skill.service;

import com.skill.dto.Exposer;
import com.skill.dto.SeckillExecution;
import com.skill.entity.Seckill;
import com.skill.exception.RepeatKillException;
import com.skill.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: Shinelon
 * Date: 2016/6/18
 * Time:${Time}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);

    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);

        logger.info("exposer = {}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        long id =1000;
        long phone = 18645693021L;
        String MD5 = "8aaa3217d9dbc18d9b344e8b7dcd0128";

        try {
            SeckillExecution execution = seckillService.executeSeckill(id,phone,MD5);
            logger.info("result:{}",execution);

        }catch (RepeatKillException e){
            logger.error(e.getMessage());

        } catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }

    }

    @Test
    public void testTotalSeckill() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer .isExposed()){

            long phone = 18645693021L;
            String MD5 = exposer.getMd5();
            try {

                SeckillExecution execution = seckillService.executeSeckill(id,phone,MD5);
                logger.info("result test:{}",execution);

            }catch (RepeatKillException e){
                logger.error(e.getMessage());

            } catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }

    }

}