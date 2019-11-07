package com.xinyou.dome.service.impl;

import com.xinyou.dome.service.BeanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/17 10:25
 * @Description:
 */
@Service
public class BeanServiceImpl implements BeanService, InitializingBean, DisposableBean, BeanNameAware {
    private Logger logger = LoggerFactory.getLogger(BeanServiceImpl.class);

    @Override
    public void beanTest(String string) {
        logger.info("BeanServiceImpl beanTest 调用中 :" + string);
    }

    @Override
    public void destroy() throws Exception {
        logger.info("BeanServiceImpl destroy 销毁之后");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("BeanServiceImpl init 初始化之前");
    }

    @Override
    public void setBeanName(String s) {
        logger.info("BeanServiceImpl setBeanName 设置beanName:" + s);
    }
}
