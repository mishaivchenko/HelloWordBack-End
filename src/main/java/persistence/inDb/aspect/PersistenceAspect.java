package persistence.inDb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PersistenceAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(persistence.inDb.aspect.PersistenceAdvice)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            logger.info("Get All from DB");
            Object o = proceedingJoinPoint.proceed();
            logger.info("data from DB received successfully");
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}