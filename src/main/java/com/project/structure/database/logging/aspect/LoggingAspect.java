package com.project.structure.database.logging.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("execution(* com.project.structure.database.logging.service..*(..))")
	public void serviceMethods() {
	}

	@Before("serviceMethods()")
	public void before(JoinPoint joinPoint) {
		logger.info("AOP - Entering method: {} with args: {}", joinPoint.getSignature().toShortString(),
				joinPoint.getArgs());
	}

	@AfterReturning(value = "serviceMethods()", returning = "result")
	public void after(JoinPoint joinPoint, Object result) {
		logger.info("AOP - Method {} returned: {}", joinPoint.getSignature().toShortString(), result);
	}

	@Around("serviceMethods()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long timeTaken = System.currentTimeMillis() - start;
		logger.info("AOP - Execution time of {}: {} ms", pjp.getSignature().toShortString(), timeTaken);
		return result;
	}
}