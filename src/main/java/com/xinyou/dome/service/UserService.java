package com.xinyou.dome.service;

import com.xinyou.dome.template.CallResult;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/917:20
 * @Description:
 */
public interface UserService {

    public CallResult<String> queryUser(String id);
}
