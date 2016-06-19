package com.skill.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: Shinelon
 * Date: 2016/6/19
 * Time:${Time}
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(name = "/list",method = RequestMethod.GET)
    public String list(Model model){

        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail (@PathVariable("seckillId") Long seckilId, Model model){

        return "detail";
    }
}
