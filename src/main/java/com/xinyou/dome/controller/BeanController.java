package com.xinyou.dome.controller;

import com.xinyou.dome.service.BeanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/17 10:22
 * @Description:
 */
@RestController
public class BeanController {

    private Logger logger = LoggerFactory.getLogger(BeanController.class);
    @Autowired
    private BeanService beanService;

    @RequestMapping("/xinyou")
    public void test(String str) {
        logger.info("bean hashCode:" + beanService.hashCode());
        beanService.beanTest(str);
    }
    @RequestMapping("/xin")
    public void xin(String str) {
        logger.info("bean hashCode:" + beanService.hashCode());
        beanService.beanTest(str);
    }
}
