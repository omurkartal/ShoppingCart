package edu.omur.shoppingcart.aspect;

import edu.omur.shoppingcart.annotation.TrackCartItem;
import edu.omur.shoppingcart.service.CartServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Aspect
@Component
public class TrackCartItemAspect {
    private static final Logger logger = LoggerFactory.getLogger(TrackCartItemAspect.class);

    @Before("@annotation(edu.omur.shoppingcart.annotation.TrackCartItem)")
    public void trackCartItem(JoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        Object[] methodArguments = joinPoint.getArgs();

        Method method = joinPoint.getTarget().getClass().getMethod(methodName, methodSignature.getMethod().getParameterTypes());
        TrackCartItem trackCartItem = method.getAnnotation(TrackCartItem.class);
        String methodParameters = getMethodParameters(trackCartItem, methodArguments);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentTime.format(formatter);
        //write data to kafka, mq, etc.
        logger.info(String.format("class.method:%s.%s; parameters:%s -> datetime:%s", className, methodName, methodParameters, formattedDateTime));
        Object targetObject = joinPoint.getTarget();
        if (targetObject instanceof CartServiceImpl) {
            CartServiceImpl cartServiceImpl = (CartServiceImpl) targetObject;
            logger.info(cartServiceImpl.getCartInfoList().toString()); // current cartServiceImpl object
        }
    }

    private String getMethodParameters(TrackCartItem trackCartItem, Object[] methodArguments) {
        StringJoiner sj = new StringJoiner("|", "[", "]");
        if (trackCartItem != null && trackCartItem.logParameters()) {
            if (methodArguments != null && methodArguments.length > 0) {
                for (Object o : methodArguments) {
                    if (o instanceof String) {
                        sj.add(o.toString());
                    }
                }
            }
        }
        return sj.toString();
    }
}