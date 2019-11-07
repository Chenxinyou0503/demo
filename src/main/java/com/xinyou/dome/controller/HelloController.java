package com.xinyou.dome.controller;

import com.xinyou.dome.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/12/516:28
 * @Description:
 */
@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>(16);
        Enumeration<String> keys = request.getParameterNames();
        String key = null;
        String value = "";
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            try {
                value = request.getParameter(key) == null ? "" : request.getParameter(key);
            } catch (Exception e) {

            }
            map.put(key, value);
        }
        System.out.println(map.get("third_refund_back_money"));
        System.out.println(map.get("batch_no"));
        System.out.println(map.get("trade_no"));
        System.out.println("hello world!");
        return "hello world";
    }

    @RequestMapping("/test")
    public String astest(HttpServletRequest request, HttpServletResponse response) {
        logger.info("执行test方法开始");
        String id = getString();
        userService.queryUser(id);
        logger.info("执行test方法结束");
        return "hello world";
    }

    public String getString() {
        String id = "123";
        try {
            id = "456";
            logger.info("id 赋值为" + id);
//            return id;
            throw new Exception();
        } catch (Exception e) {
            logger.info("执行异常方法");
            id = "000";
            logger.info("id 赋值为" + id);
            return id;
        } finally {
            id = "789";
            logger.info("id 赋值为" + id);
            return id;
        }
    }
}
