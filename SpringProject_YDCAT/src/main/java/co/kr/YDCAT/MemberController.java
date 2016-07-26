package co.kr.YDCAT;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.member.MemberDto;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//회원가입폼
	@RequestMapping("/insertForm.do")
	public String userWrite(){
		return ".main.member.insertForm"; //뷰
	}//userWrite
	
	
	//idCheck.do
	@RequestMapping(value="/idCheck.do", method=RequestMethod.POST)
	public ModelAndView idCheck(String id) throws NamingException, IOException {
		int check=-1;
		MemberDto memberDto = sqlSession.selectOne("member.selectOne", id);
	
		if(memberDto == null){
			check=1; //사용 가능한 id
		}
		
		return new ModelAndView("member/idCheck", "check", check);
	}//idCheck
	
	
	//회원가입
	@RequestMapping(value="/insertPro.do", method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute("memberDto") MemberDto memberDto,String jumin1, String jumin2, String num1, String num2, String address, String details)
		throws NamingException, IOException {
		
		memberDto.setJumin(jumin1+"-"+jumin2);
		memberDto.setAddr(num1+"-"+num2+"-"+address+"-"+details);
				
		sqlSession.insert("member.insertMember", memberDto);
		
		return ".main.member.login"; //뷰이름
	}//memberInsert
	
	
	//로그인 폼
	@RequestMapping("/login.do")
	public String userLogin(){
		return ".main.member.login";//뷰
	}
	
	
	//로그인
	@RequestMapping(value="/loginPro.do", method=RequestMethod.POST)
	public ModelAndView memberLogin(String id, String password, HttpServletRequest request)
		throws NamingException, IOException{
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		System.out.println("로그인ID "+id);
		System.out.println("로그인PWD "+password);
		
		MemberDto memberDto = sqlSession.selectOne("member.selectLogin", map);
		System.out.println("로그인 시도!");		
		if(memberDto==null){ //로그인 실패
			System.out.println("로그인 실패");
			return new ModelAndView(".main.member.loginSuccess", "memberDto", memberDto);
		}
		
		//세션 생성
		
			HttpSession session= request.getSession();
			session.setAttribute("loginId", id);
			System.out.println("세션생성"+ id);
		
		
		return new ModelAndView(".main.member.loginSuccess", "memberDto", memberDto);
	}
	
	
	//로그아웃
	@RequestMapping("/logOut.do")
	public String userOut(){
		return "member/logOut"; //뷰
	}
	
	
	//회원정보 수정 폼
	@RequestMapping(value="/editForm.do", method=RequestMethod.POST)
	public ModelAndView editForm(String id) throws NamingException, IOException {
		MemberDto memberDto = sqlSession.selectOne("member.selectOne", id);
		return new ModelAndView(".main.member.editForm", "memberDto", memberDto);
	}
	
	
	//회원정보 수정
	@RequestMapping(value="/editPro.do", method=RequestMethod.POST)
	public String editPro(@ModelAttribute("MemberDto") MemberDto memberDto, String num1, String num2, String address, String details)
	throws NamingException, IOException {
		
		memberDto.setAddr(address+details);
		
		sqlSession.update("member.memberUpdate", memberDto);
		
		return ".main.member.login";
	}
		
	
	//회원삭제
	@RequestMapping("memberDelete.do")
	public String userDelete(String id) throws NamingException, IOException {
		sqlSession.delete("member.memberDelete", id);
		return ".main.member.login";
	}
	
}//class
