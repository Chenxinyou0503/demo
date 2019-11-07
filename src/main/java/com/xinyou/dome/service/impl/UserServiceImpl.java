package com.xinyou.dome.service.impl;

import com.xinyou.dome.service.UserService;
import com.xinyou.dome.template.CallResult;
import com.xinyou.dome.template.ServiceTemplate;
import com.xinyou.dome.template.TemplateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/917:21
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ServiceTemplate serviceTemplate;

    @Override
    public CallResult<String> queryUser(String id) {
        logger.info("开始执行queryUser方法");
        CallResult<String> result = serviceTemplate.doSomething(new TemplateAction<String>() {
            @Override
            public CallResult<String> checkParam() {
                if (StringUtils.isEmpty(id)) {
                    return CallResult.failure("param is null");
                } else {
                    return CallResult.success();
                }
            }

            @Override
            public CallResult<String> checkBiz() {
                return CallResult.success();
            }

            @Override
            public CallResult<String> doAction() {
                String a = "123456";
                return CallResult.success(a);
            }

            @Override
            public void finishUp(CallResult<String> result) {

            }
        });
        logger.info("执行queryUser方法结束");
        return result;
    }
}
