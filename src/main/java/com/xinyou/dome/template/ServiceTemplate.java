package com.xinyou.dome.template;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/914:04
 * @Description:
 */
public interface ServiceTemplate {
    <T> CallResult<T> doSomething(TemplateAction<T> templateAction);
}
