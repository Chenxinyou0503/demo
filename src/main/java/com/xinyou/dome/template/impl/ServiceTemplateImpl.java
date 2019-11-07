package com.xinyou.dome.template.impl;

import com.xinyou.dome.template.CallResult;
import com.xinyou.dome.template.ServiceTemplate;
import com.xinyou.dome.template.TemplateAction;
import org.springframework.stereotype.Service;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/914:38
 * @Description:
 */
@Service
public class ServiceTemplateImpl implements ServiceTemplate {
    @Override
    public <T> CallResult<T> doSomething(TemplateAction<T> templateAction) {
        try {
            CallResult<T> result = templateAction.checkParam();
            if (null == result) {
                return CallResult.failure("null result checkParam");
            } else if (!result.isSuccess()) {
                return result;
            } else {
                result = templateAction.checkBiz();
                if (null == result) {
                    return CallResult.failure("null result checkBiz");
                } else if (!result.isSuccess()) {
                    return result;
                } else {
                    result = templateAction.doAction();
                    if (null == result) {
                        return CallResult.failure("null result doAction");
                    } else if (!result.isSuccess()) {
                        return result;
                    } else {
                        templateAction.finishUp(result);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return CallResult.failure("error :" + e.getMessage());
        }

    }
}
