package co.kr.YDCAT;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before; 
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

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
     
    
    @Around("logging()") 
    public String before(ProceedingJoinPoint joinPoint) throws Throwable{
    	    	    	    	
    	//AOP 에서 req/res 접근
    	HttpServletRequest request = null;
        HttpServletResponse response = null;
        for ( Object o : joinPoint.getArgs() ){ 
            if ( o instanceof HttpServletRequest ) {
                request = (HttpServletRequest)o;
            } 
            if ( o instanceof HttpServletResponse ) {
                response = (HttpServletResponse)o;
            } 
        }
    	
        System.out.println(request);
        System.out.println(response);
        
        //리스폰스 설정
        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
    	//세션값 받아오기
    	HttpSession session=request.getSession();
		
		System.out.println("AOP체크 세션ID:"+session.getAttribute("loginId"));	
		
		//System.out.println("세션"+session);		
		String str="";
		
		
		if(session.getAttribute("loginId") == null){
			System.out.println("AOP : 로그인 필요하다.");
			str=".main.board.requestLogin";
			return str;
		}else{
			System.out.println("AOP: 로그인 중이다");
		str=(String)joinPoint.proceed();		
		}
		
		return str;
    	
    } 
    
}//end class 