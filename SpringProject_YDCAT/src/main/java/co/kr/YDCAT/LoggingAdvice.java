package co.kr.YDCAT;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before; 
import org.aspectj.lang.annotation.Pointcut;

@Aspect 
public class LoggingAdvice {
 
    @Pointcut("execution(* co.kr.YDCAT.*.*(..))")
     public void logging2() {};
      
    @Before("logging2()") 
    public void before(JoinPoint joinPoint) {
         System.out.println("* AOP ¼º°ø before *"); 
    } 
     
}//end class 