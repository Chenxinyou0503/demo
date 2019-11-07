package com.xinyou.dome.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/4/25 16:13
 * @Description:
 */
@Aspect
@Component
public class AdviceService {
    private Logger logger = LoggerFactory.getLogger(AdviceService.class);

    @Pointcut("execution(public * com.xinyou.dome.service.impl.UserServiceImpl.queryUser(..))")
    public void pointCut() {
    }

    //@Before 前置通知：运行方法前通知
    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info("执行 beforeMethod ");
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                logger.info("JoinPoint params:{}", args[i]);
            }
        }
    }

    //@After 后置通知：最终通知,方法执行完执行,(出现异常依然执行)
    @After("pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        logger.info("执行 afterMethod ");
    }

    //@AfterReturning:后置通知, 方法执行完执行,(出现异常不执行)
    @AfterReturning(value = "pointCut()", returning = "object")
    public void afterReturning(JoinPoint joinPoint, Object object) {
        logger.info("执行 AfterReturning ");
    }

    //@AfterThrowing:异常通知,方法出现异常后执行
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        logger.info("执行 AfterThrowing ：调用结果出现了异常");
    }

    //@Around:环绕通知,可以一次性完成所有通知,可以修改形参,一般配合注解使用
    // 在开始执行目标方法之前，先执行around在执行before 然后执行目标方法里面的功能，目标方法
    //执行完成以后，首先执行around 后执行after 最后在执行afterReturning
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("执行 around 之前");
        Object object = pjp.proceed();


        logger.info(pjp.getSignature().getName());
        logger.info("执行 around 之后");
        return object;
    }

}
