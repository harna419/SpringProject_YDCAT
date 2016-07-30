package co.kr.YDCAT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before; 
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
     
    
    @Before("logging()") 
    public String before(JoinPoint joinPoint) {
    	    	    	
    	//AOP���� HTTPSERVLETREQUEST�� �����ϴ� ���.
       	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = attr.getRequest();
    	
    	HttpSession session=request.getSession();
		
		System.out.println("AOPüũ ����ID:"+session.getAttribute("loginId"));	
		
		//System.out.println("����"+session);		
		String str="";
		
		if(session.getAttribute("loginId") != null){
			System.out.println("�α��� �ʿ��ϴ�.");
			return "redirect:login.do";
		}
		//str=(String)joinPoint.proceed();		
    	return str;
    	
    } 
    
}//end class 