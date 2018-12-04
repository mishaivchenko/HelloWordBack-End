package web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerControllerAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(web.aspect.ControllerAdvice)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            logger.info("user call controller method " + proceedingJoinPoint.getSignature().getName() + " with arg: " + proceedingJoinPoint.getArgs()[0]);
            Object o = proceedingJoinPoint.proceed();
            logger.info("user successfully get data");
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
