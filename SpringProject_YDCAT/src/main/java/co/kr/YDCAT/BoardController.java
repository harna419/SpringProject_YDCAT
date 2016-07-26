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
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	//�۾��� ��
	@RequestMapping("/writeForm.do")
	public String writeForm(){
		return ".main.board.writeForm"; //��.
	}//writeForm()
	
	
	//DB�� �۾��� insert
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String writePro(@ModelAttribute("BoardDto") BoardDto boardDto, HttpServletRequest request)
		throws NamingException, IOException {
		String ip=request.getRemoteAddr(); //ip ���ϰ�
		boardDto.setIp(ip); ////ip �޾���
		sqlSession.update("board.upRef"); //ref �� ����		
		int ref_step=0;
		boardDto.setRef_step(ref_step);
		int ref_level=0;
		boardDto.setRef_level(ref_level);
		
		sqlSession.insert("board.insertBoard", boardDto);
		
		return "redirect:list.do";
	}//userWritePro	
	
	
	//����Ʈ
	@RequestMapping("/list.do")
	public ModelAndView boardList(Integer pageNum){		

		//int page_Num = Integer.parseInt(pageNum);
		if(pageNum==null){ pageNum=1; }
		int startNum = (pageNum-1)*10; 
		
		List<HashMap<String, String>> list = sqlSession.selectList("board.selectAll", new Integer(startNum));
				
		int totalList= sqlSession.selectOne("board.countNum"); //�� �� ����
		//System.out.println(totalList);
	
		int totalPage = (totalList/10)+1; //��ü ��������
		
		ModelAndView listModel = new ModelAndView();
		
		listModel.setViewName(".main.board.list");
		listModel.addObject("list", list);
		listModel.addObject("totalPage", totalPage);
		
		//return new ModelAndView(".main.board.list", "list", list);
		return listModel;
	}//boardList() end
	
	
	//content
	@RequestMapping("/content.do")
	public ModelAndView getOneBoard(int num){
		sqlSession.update("board.readCount", new Integer(num)); //��ȸ��
		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
		
		return new ModelAndView(".main.board.content", "boardDto", boardDto);
	}//getOneBoard() end
	
	
	//��۾��� ��
	@RequestMapping("/replyForm.do")
	public String replyForm(){
		return ".main.board.replyForm"; //��.
	}//writeForm()
	
	
	//DB�� ��۾��� insert
	@RequestMapping(value="/reply.do", method=RequestMethod.POST)
	public String replyPro(@ModelAttribute("BoardDto") BoardDto boardDto, int num, HttpServletRequest request) 
		throws NamingException, IOException {
		
		String ip=request.getRemoteAddr(); //ip ���ϰ�
		boardDto.setIp(ip); ////ip �޾���
		
		BoardDto boardDto2 = (BoardDto)sqlSession.selectOne("board.selectOne", new Integer(num));
		
		//HashMap<String, Integer> place_ref = new HashMap<String, Integer>();
		//place_ref.put("ref", new Integer(boardDto2.getRef()));
		//place_ref.put("ref_step", new Integer(boardDto2.getRef_step()));
		
		//System.out.println(boardDto2.getRef());
		//System.out.println(boardDto2.getRef_step());
		//System.out.println(boardDto2.getRef_level());
			
		//sqlSession.update("board.replyPos",place_ref);	
				
		boardDto.setRef(boardDto2.getRef());
		boardDto.setRef_step(boardDto2.getRef_step()+1);
		boardDto.setRef_level(boardDto2.getRef_level()+1);
		
		sqlSession.insert("board.insertBoard", boardDto);
		
		return "redirect:list.do";
	}//userWritePro	
	
	
	//�ۼ�����
	@RequestMapping("/update.do")
	public ModelAndView userEdit(int num){
		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
		return new ModelAndView(".main.board.update", "boardDto", boardDto); //��.
	}//userEdit() end
	
	
	//�ۼ��� DB
	@RequestMapping(value="/updatePro.do", method=RequestMethod.POST)
	public String editBoard(@ModelAttribute("BoardDto") BoardDto boardDto)
		throws NamingException, IOException{
		//System.out.println("asdf");
		//System.out.println(boardDto.getSubject());
		sqlSession.update("board.updateBoard", boardDto);
		return "redirect:list.do";
	}//editBoard() end
	
	
	//�ۻ���
	@RequestMapping("/delete.do")
	public String deleteBoard(int num) throws NamingException, IOException {
		sqlSession.delete("board.deleteBoard", num);
		return "redirect:list.do";
	}//deleteBoard()
	
}//class
