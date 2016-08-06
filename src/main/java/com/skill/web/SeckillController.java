package com.skill.web;

import com.skill.dto.Exposer;
import com.skill.dto.SeckillExecution;
import com.skill.dto.SeckillResult;
import com.skill.entity.Seckill;
import com.skill.enums.SeckillStatEnum;
import com.skill.exception.RepeatKillException;
import com.skill.exception.SeckillCloseException;
import com.skill.exception.SeckillException;
import com.skill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * User: Shinelon
 * Date: 2016/6/19
 * Time:${Time}
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(name = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckilId, Model model) {

        if (seckilId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckilId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"aplication/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId) {

        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());

        }
        return result;
    }
    @RequestMapping(value ="/{seckillId}/{md5}/execution")
    public SeckillResult<SeckillExecution> execute(
           @PathVariable("seckillId") Long seckillId,
           @PathVariable("md5") String md5,
           @CookieValue(value = "killPhone",required = false) Long phone
    ){
        if(phone == null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }

        SeckillResult<SeckillExecution> result;
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (RepeatKillException e){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false,execution);

        }catch (SeckillCloseException e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(false,execution);

        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            //
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false,execution);

        }

    }
    @RequestMapping(value = "time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time(){

        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
