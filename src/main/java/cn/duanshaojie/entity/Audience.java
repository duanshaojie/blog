package cn.duanshaojie.entity;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <b>类名：</b>Audience.java<br>
 * <p><b>标题：</b>AOP测试</p>
 * <p><b>描述：</b>aop</p>
 * @author <font color='blue'>edison_dsj@163.com</font>
 * @date
 * AOP
 * 桃之夭夭,灼灼其华
 */

@Component
@Aspect
public class Audience {
	@Pointcut("execution(* com.yibao.insurance..*(..))")
	public void aspect(){
		
	}
	@Before("aspect()")
	public void takeSeats(){
		System.out.println("before.");
	}
	@After("aspect()")
	public void turnOffCellPhones(){
		System.out.println("after");
	}
	@AfterReturning("aspect()")
	public void applaud(){
		System.out.println("CLAP CLAP CLAP CLAP CLAP");
	}
	@AfterThrowing(pointcut="aspect()",throwing="ex")
	public void demandRefund(){
		System.out.println("Boo!we want our money back!");
	}
}
