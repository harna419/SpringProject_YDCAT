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

import model.board.BoardDto;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AdminController {

	@Autowired
	private SqlSession sqlSession;
		
	@RequestMapping("/memberInfo.do")
	public ModelAndView memberInfo(Integer pageNum){		

		if(pageNum==null){ pageNum=1; }
		int startNum = (pageNum-1)*10; 
		
		List<HashMap<String, String>> list = sqlSession.selectList("member.allMember", new Integer(startNum));
						
		int totalList= sqlSession.selectOne("member.countNum"); //ÃÑ È¸¿ø °¹¼ö
		int totalPage = (totalList/10)+1; //ÀüÃ¼ ÆäÀÌÁö¼ö
		
		ModelAndView listModel = new ModelAndView();
		
		listModel.setViewName(".main.admin.memberInfo");
		listModel.addObject("list", list);
		listModel.addObject("totalPage", totalPage);
		
		//return new ModelAndView(".main.board.list", "list", list);
		return listModel;
	}//boardList() end
	
	
	//¿¢¼¿ »ðÀÔ
	@RequestMapping(value="/insertmember.do", method=RequestMethod.POST)
	public String insertExcel(String location){
		sqlSession.insert("admin.insertExcel", new String(location)); 
		
		return "redirect:memberInfo.do";
	}//getOneBoard() end */
	
}
