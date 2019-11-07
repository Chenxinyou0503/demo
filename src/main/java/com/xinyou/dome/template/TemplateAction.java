package com.xinyou.dome.template;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/914:23
 * @Description:
 */
public interface TemplateAction<T> {

    CallResult<T> checkParam();

    CallResult<T> checkBiz();

    CallResult<T> doAction();

    void finishUp(CallResult<T> result);
}
