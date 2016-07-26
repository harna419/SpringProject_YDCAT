package co.kr.YDCAT;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

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
	
	
	//ȸ��������
	@RequestMapping("/insertForm.do")
	public String userWrite(){
		return ".main.member.insertForm"; //��
	}//userWrite
	
	
	//idCheck.do
	@RequestMapping(value="/idCheck.do", method=RequestMethod.POST)
	public ModelAndView idCheck(String id) throws NamingException, IOException {
		int check=-1;
		MemberDto memberDto = sqlSession.selectOne("member.selectOne", id);
	
		if(memberDto == null){
			check=1; //��� ������ id
		}
		
		return new ModelAndView("member/idCheck", "check", check);
	}//idCheck
	
	
	//ȸ������
	@RequestMapping(value="/insertPro.do", method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute("memberDto") MemberDto memberDto, String num1, String num2, String address, String details)
		throws NamingException, IOException {
		memberDto.setAddr(address+details);
				
		sqlSession.insert("member.insertMember", memberDto);
		
		return ".main.member.login"; //���̸�
	}//memberInsert
	
	
	//�α��� ��
	@RequestMapping("/login.do")
	public String userLogin(){
		return ".main.member.login";//��
	}
	
	
	//�α���
	@RequestMapping(value="/loginPro.do", method=RequestMethod.POST)
	public ModelAndView memberLogin(String id, String passwd)
		throws NamingException, IOException{
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd", passwd);
		
		MemberDto memberDto = sqlSession.selectOne("member.selectLogin", map);
				
		if(memberDto==null){ //�α��� ����
			return new ModelAndView(".main.member.loginSuccess", "memberDto", memberDto);
		}
		return new ModelAndView(".main.member.loginSuccess", "memberDto", memberDto);
	}
	
	
	//�α׾ƿ�
	@RequestMapping("/logOut.do")
	public String userOut(){
		return "member/logOut"; //��
	}
	
	
	//ȸ������ ���� ��
	@RequestMapping(value="/editForm.do", method=RequestMethod.POST)
	public ModelAndView editForm(String id) throws NamingException, IOException {
		MemberDto memberDto = sqlSession.selectOne("member.selectOne", id);
		return new ModelAndView(".main.member.editForm", "memberDto", memberDto);
	}
	
	
	//ȸ������ ����
	@RequestMapping(value="/editPro.do", method=RequestMethod.POST)
	public String editPro(@ModelAttribute("MemberDto") MemberDto memberDto, String num1, String num2, String address, String details)
	throws NamingException, IOException {
		
		memberDto.setAddr(address+details);
		
		sqlSession.update("member.memberUpdate", memberDto);
		
		return ".main.member.login";
	}
		
	
	//ȸ������
	@RequestMapping("memberDelete.do")
	public String userDelete(String id) throws NamingException, IOException {
		sqlSession.delete("member.memberDelete", id);
		return ".main.member.login";
	}
	
}//class
