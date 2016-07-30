package co.kr.YDCAT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before; 
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//추가적으로 response에 접근하는 방법.
/*
 * ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
	HttpServletRequest request = servletContainer.getRequest();
	HttpServletResponse response = servletContainer.getResponse();
 */

//AOP에서 HTTPSERVLETREQUEST에 접근하는 방법.
	//ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//HttpServletRequest request = attr.getRequest();


@Aspect 
public class LoggingAdvice {
 
	
	//글목록에서 글 쓰기를 할때 포인트컷을 잡는다.
    @Pointcut("execution(* co.kr.YDCAT.BoardController.writeForm(..))")
     public void logging() {};
     
    
    @Before("logging()") 
    public String before(JoinPoint joinPoint) {
    	    	    	
    	//AOP에서 HTTPSERVLETREQUEST에 접근하는 방법.
       	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = attr.getRequest();
    	
    	HttpSession session=request.getSession();
		
		System.out.println("AOP체크 세션ID:"+session.getAttribute("loginId"));	
		
		//System.out.println("세션"+session);		
		String str="";
		
		if(session.getAttribute("loginId") != null){
			System.out.println("로그인 필요하다.");
			return "redirect:login.do";
		}
		//str=(String)joinPoint.proceed();		
    	return str;
    	
    } 
    
}//end class 