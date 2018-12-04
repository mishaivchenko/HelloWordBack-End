package services.impl.aspect;

import domain.Contact;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class ServiceAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(services.impl.aspect.ServiceAdvice)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            List<Contact> contactList = (List<Contact>) proceedingJoinPoint.proceed();
            logger.info("filtered data:" + contactList);
            return contactList;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}