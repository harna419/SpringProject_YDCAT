package co.kr.YDCAT;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import model.board.BoardDto;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	//�۾��� ��
	@RequestMapping("/writeForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response){
		return ".main.board.writeForm"; //��.
	}//writeForm()
	
	
	//DB�� �۾��� insert
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String writePro(@ModelAttribute("BoardDto") BoardDto boardDto, HttpServletRequest request)
		throws NamingException, IOException {
		
		MultipartFile uploadfile = boardDto.getUploadfile();
	        
		 if (uploadfile != null) {
	            String fileName = uploadfile.getOriginalFilename();
	            boardDto.setFileName(fileName);
	            try {
	                // 1. FileOutputStream ���
	                // byte[] fileData = file.getBytes();
	                // FileOutputStream output = new FileOutputStream("C:/images/" + fileName);
	                // output.write(fileData);
	                 
	                // 2. File ���
	            
	                File file = new File("C:/images/" + fileName);
	                uploadfile.transferTo(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try - catch
	        } // if
		
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
	public ModelAndView getOneBoard(int no){
		
		sqlSession.update("board.readCount", new Integer(no)); //��ȸ��
		
		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", no);
		
		return new ModelAndView(".main.board.content", "boardDto", boardDto);
	}//getOneBoard() end
	
	
	//��۾��� ��
	@RequestMapping("/replyForm.do")
	public String replyForm(HttpServletRequest request, HttpServletResponse response){
		return ".main.board.replyForm"; //��.
	}//writeForm()
	
	
	//DB�� ��۾��� insert
	@RequestMapping(value="/reply.do", method=RequestMethod.POST)
	public String replyPro(@ModelAttribute("BoardDto") BoardDto boardDto, int no, HttpServletRequest request) 
		throws NamingException, IOException {
		
		MultipartFile uploadfile = boardDto.getUploadfile();
        
		 if (uploadfile != null) {
	            String fileName = uploadfile.getOriginalFilename();
	            boardDto.setFileName(fileName);
	            try {
	                // 2. File ���
	            	
	                File file = new File("C:/images/" + fileName);
	                uploadfile.transferTo(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try - catch
	        } // if
		
		String ip=request.getRemoteAddr(); //ip ���ϰ�
		boardDto.setIp(ip); ////ip �޾���
		
		BoardDto boardDto2 = (BoardDto)sqlSession.selectOne("board.selectOne", new Integer(no));
			
		boardDto.setRef(boardDto2.getRef());
		boardDto.setRef_step(boardDto2.getRef_step()+1);
		boardDto.setRef_level(boardDto2.getRef_level()+1);
		
		sqlSession.insert("board.insertBoard", boardDto);
		
		return "redirect:list.do";
	}//userWritePro	
	
	
	//�ۼ�����
	@RequestMapping("/update.do")
	public ModelAndView userEdit(int no){
		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", no);
		return new ModelAndView(".main.board.update", "boardDto", boardDto); //��.
	}//userEdit() end
	
	
	//�ۼ��� DB
	@RequestMapping(value="/updatePro.do", method=RequestMethod.POST)
	public String editBoard(@ModelAttribute("BoardDto") BoardDto boardDto)
		throws NamingException, IOException{
		//System.out.println("asdf");
		//System.out.println(boardDto.getSubject());
		MultipartFile uploadfile = boardDto.getUploadfile();
        
		 if (uploadfile != null) {
	            String fileName = uploadfile.getOriginalFilename();
	            boardDto.setFileName(fileName);
	            try {
	                // 2. File ���
	            	
	                File file = new File("C:/images/" + fileName);
	                uploadfile.transferTo(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try - catch
	        } // if
		
		sqlSession.update("board.updateBoard", boardDto);
		return "redirect:list.do";
	}//editBoard() end
	
	
	//�ۻ���
	@RequestMapping("/delete.do")
	public String deleteBoard(int no) throws NamingException, IOException {
		sqlSession.delete("board.deleteBoard", no);
		return "redirect:list.do";
	}//deleteBoard()
	
		
		//�� �˻�
		@RequestMapping(value="/search.do", method=RequestMethod.POST)
		public ModelAndView searchBoard(Integer pageNum, @ModelAttribute("BoardDto") BoardDto boardDto){
			
			System.out.println("��ġ �޼��� ����");
			System.out.println("�ʵ�: "+boardDto.getSearchField());
			System.out.println("�˻�: "+boardDto.getSearchValue());
			
			
			//int page_Num = Integer.parseInt(pageNum);
			if(pageNum==null){ pageNum=1; }
			int startNum = (pageNum-1)*10; 
			boardDto.setStartNum(startNum);
			
			List<HashMap<String, String>> list = sqlSession.selectList("board.selectSearch", boardDto);
					
			int totalList= list.size(); //�� �� ����
			System.out.println("�˻��� �� ����"+totalList);
		
			int totalPage = (totalList/10)+1; //��ü ��������
			
			ModelAndView listModel = new ModelAndView();
			
			listModel.setViewName(".main.board.list");
			listModel.addObject("list", list);
			listModel.addObject("totalPage", totalPage);
			
			return listModel;
		}//deleteBoard()	
		
}//class
