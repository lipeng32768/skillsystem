package com.skill.service.impl;

import com.skill.dao.SeckillDao;
import com.skill.dao.SuccessKilledDao;
import com.skill.dto.Exposer;
import com.skill.dto.SeckillExecution;
import com.skill.entity.Seckill;
import com.skill.entity.SuccessKilled;
import com.skill.exception.RepeatKillException;
import com.skill.exception.SeckillCloseException;
import com.skill.exception.SeckillException;
import com.skill.service.SeckillService;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.security.cert.TrustAnchor;
import java.util.Date;
import java.util.List;

/**
 * User: Shinelon
 * Date: 2016/6/17
 * Time:${Time}
 */
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SeckillDao seckillDao;
    private SuccessKilledDao successKilledDao;
    //用于混淆MD5
    private final String slat = "sfshagfgashafgsafavcd88***87&&";
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);

    }

    public Seckill getById(long seckillId) {

        return seckillDao.queryById(seckillId);

    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null){
            return new Exposer(false,seckillId);
        }
        Date StartTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < StartTime.getTime() ||
                nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),StartTime.getTime(),endTime.getTime());
        }

        String md5 = getMD5(seckillId);

        return new Exposer(true , md5, seckillId);

    }

    private String getMD5(long seckillId){
        String base = seckillId+"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {

            if(md5 == null || md5.equals(getMD5(seckillId))){
                throw  new SeckillException("seckill data rewrite");
            }
        //执行秒杀逻辑：减库存+记录购买行为
        Date nowTime = new Date();
        //减库存
        try {

            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill is closed");

            } else {
                // 记录购买行为
                int insertcount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertcount <= 0) {
                    throw new RepeatKillException("seckill repeat");
                } else {
                    //秒杀成
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId);
                    return new SeckillExecution(seckillId, 1, "秒杀成功",successKilled);
                }
            }
        }catch (SeckillCloseException eclose) {
            throw eclose;
        }catch (RepeatKillException erepeat){
            throw erepeat;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译期异常转换成运行期异常
            throw  new SeckillException("seckill inner error:"+e.getMessage());
        }
    }


}
