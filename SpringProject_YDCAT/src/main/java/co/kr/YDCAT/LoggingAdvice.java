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

//�߰������� response�� �����ϴ� ���.
/*
 * ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
	HttpServletRequest request = servletContainer.getRequest();
	HttpServletResponse response = servletContainer.getResponse();
 */

//AOP���� HTTPSERVLETREQUEST�� �����ϴ� ���.
	//ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//HttpServletRequest request = attr.getRequest();


@Aspect 
public class LoggingAdvice {
 
	
	//�۸�Ͽ��� �� ���⸦ �Ҷ� ����Ʈ���� ��´�.
    @Pointcut("execution(* co.kr.YDCAT.BoardController.writeForm(..))")
     public void logging() {};
     
    
    @Around("logging()") 
    public String before(ProceedingJoinPoint joinPoint) throws Throwable{
    	    	    	    	
    	//AOP ���� req/res ����
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
        
        //�������� ����
        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
    	//���ǰ� �޾ƿ���
    	HttpSession session=request.getSession();
		
		System.out.println("AOPüũ ����ID:"+session.getAttribute("loginId"));	
		
		//System.out.println("����"+session);		
		String str="";
		
		
		if(session.getAttribute("loginId") == null){
			System.out.println("AOP : �α��� �ʿ��ϴ�.");
			str=".main.board.requestLogin";
			return str;
		}else{
			System.out.println("AOP: �α��� ���̴�");
		str=(String)joinPoint.proceed();		
		}
		
		return str;
    	
    } 
    
}//end class 