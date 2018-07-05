package pro.finance.ormdemo.demo;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by s.vasnev (s.vasnev@advcash.com) on 05.07.18.
 */
@Aspect
@Configuration
public class PerformanceMonitoringAspect {

	@Pointcut(value = "execution(* pro.finance.ormdemo.demo.SimpleComponent.*(..))")
	public void monitor() {
	}

	@Bean
	public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
		return new PerformanceMonitorInterceptor();
	}

	@Bean
	public Advisor performanceMonitorAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("pro.finance.ormdemo.demo.PerformanceMonitoringAspect.monitor()");
		return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
	}

}
