package club.nwsuaf.envi.config;

import java.lang.invoke.MethodHandles;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;

@Aspect
@Component
public class LogAcpect {

    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 设置切入点
     */
    @Pointcut("execution(public * club.nwsuaf.envi.controller..*.*(..))")
    public void webController() {
    }

    /**
     * 统计方法耗时（ms）
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("webController()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.nanoTime();
        String className = pjp.getTarget().getClass().getCanonicalName();
        String method = pjp.getSignature().getName();

        Object output = pjp.proceed();
        long interval = System.nanoTime() - startTime;
        LOG.info("{} {}#{}", (double) interval / 1_000_000, className, method);

        return output;
    }

    /**
     * 在请求之前打印HTTP请求等信息
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webController()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        LOG.info("[请求信息]\n"
                + "方法名称:\t{}#{}\n"
                + "请求URL:\t{}\n"
                + "请求方法:\t{}\n"
                + "远程地址:\t{}\n"
                + "参数列表:\t{}\n",
                signature.getDeclaringTypeName(), signature.getName(),
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getRemoteAddr(),
                Arrays.toString(joinPoint.getArgs()));
    }

}
