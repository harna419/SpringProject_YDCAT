package co.kr.YDCAT;

import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before; 
import org.aspectj.lang.annotation.Pointcut; 
 
@Aspect 
public class LoggingAdvice {
 
    @Pointcut("execution(* co.kr.YDCAT.*controller.*(..))")
     public void logging() {    }
      
    @Before("logging()") 
    public void before() {
         System.out.println("* AOP ¼º°ø before *"); 
    } 
     
}//end class 