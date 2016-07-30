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
	
	//글쓰기 폼
	@RequestMapping("/writeForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response){
		return ".main.board.writeForm"; //뷰.
	}//writeForm()
	
	
	//DB에 글쓰기 insert
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String writePro(@ModelAttribute("BoardDto") BoardDto boardDto, HttpServletRequest request)
		throws NamingException, IOException {
		
		MultipartFile uploadfile = boardDto.getUploadfile();
	        
		 if (uploadfile != null) {
	            String fileName = uploadfile.getOriginalFilename();
	            boardDto.setFileName(fileName);
	            try {
	                // 1. FileOutputStream 사용
	                // byte[] fileData = file.getBytes();
	                // FileOutputStream output = new FileOutputStream("C:/images/" + fileName);
	                // output.write(fileData);
	                 
	                // 2. File 사용
	            
	                File file = new File("C:/images/" + fileName);
	                uploadfile.transferTo(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try - catch
	        } // if
		
		String ip=request.getRemoteAddr(); //ip 구하고
		boardDto.setIp(ip); ////ip 받아줌
		sqlSession.update("board.upRef"); //ref 값 증가		
		int ref_step=0;
		boardDto.setRef_step(ref_step);
		int ref_level=0;
		boardDto.setRef_level(ref_level);
		
		sqlSession.insert("board.insertBoard", boardDto);
		
		return "redirect:list.do";
	}//userWritePro	
	
	
	//리스트
	@RequestMapping("/list.do")
	public ModelAndView boardList(Integer pageNum){		

		//int page_Num = Integer.parseInt(pageNum);
		if(pageNum==null){ pageNum=1; }
		int startNum = (pageNum-1)*10; 
		
		List<HashMap<String, String>> list = sqlSession.selectList("board.selectAll", new Integer(startNum));
				
		int totalList= sqlSession.selectOne("board.countNum"); //총 글 갯수
		//System.out.println(totalList);
	
		int totalPage = (totalList/10)+1; //전체 페이지수
		
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
		
		sqlSession.update("board.readCount", new Integer(no)); //조회수
		
		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", no);
		
		return new ModelAndView(".main.board.content", "boardDto", boardDto);
	}//getOneBoard() end
	
	
	//답글쓰기 폼
	@RequestMapping("/replyForm.do")
	public String replyForm(HttpServletRequest request, HttpServletResponse response){
		return ".main.board.replyForm"; //뷰.
	}//writeForm()
	
	
	//DB에 답글쓰기 insert
	@RequestMapping(value="/reply.do", method=RequestMethod.POST)
	public String replyPro(@ModelAttribute("BoardDto") BoardDto boardDto, int no, HttpServletRequest request) 
		throws NamingException, IOException {
		
		MultipartFile uploadfile = boardDto.getUploadfile();
        
		 if (uploadfile != null) {
	            String fileName = uploadfile.getOriginalFilename();
	            boardDto.setFileName(fileName);
	            try {
	                // 2. File 사용
	            	
	                File file = new File("C:/images/" + fileName);
	                uploadfile.transferTo(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try - catch
	        } // if
		
		String ip=request.getRemoteAddr(); //ip 구하고
		boardDto.setIp(ip); ////ip 받아줌
		
		BoardDto boardDto2 = (BoardDto)sqlSession.selectOne("board.selectOne", new Integer(no));
			
		boardDto.setRef(boardDto2.getRef());
		boardDto.setRef_step(boardDto2.getRef_step()+1);
		boardDto.setRef_level(boardDto2.getRef_level()+1);
		
		sqlSession.insert("board.insertBoard", boardDto);
		
		return "redirect:list.do";
	}//userWritePro	
	
	
	//글수정폼
	@RequestMapping("/update.do")
	public ModelAndView userEdit(int no){
		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", no);
		return new ModelAndView(".main.board.update", "boardDto", boardDto); //뷰.
	}//userEdit() end
	
	
	//글수정 DB
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
	                // 2. File 사용
	            	
	                File file = new File("C:/images/" + fileName);
	                uploadfile.transferTo(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try - catch
	        } // if
		
		sqlSession.update("board.updateBoard", boardDto);
		return "redirect:list.do";
	}//editBoard() end
	
	
	//글삭제
	@RequestMapping("/delete.do")
	public String deleteBoard(int no) throws NamingException, IOException {
		sqlSession.delete("board.deleteBoard", no);
		return "redirect:list.do";
	}//deleteBoard()
	
		
		//글 검색
		@RequestMapping(value="/search.do", method=RequestMethod.POST)
		public ModelAndView searchBoard(Integer pageNum, @ModelAttribute("BoardDto") BoardDto boardDto){
			
			System.out.println("서치 메서드 실행");
			System.out.println("필드: "+boardDto.getSearchField());
			System.out.println("검색: "+boardDto.getSearchValue());
			
			
			//int page_Num = Integer.parseInt(pageNum);
			if(pageNum==null){ pageNum=1; }
			int startNum = (pageNum-1)*10; 
			boardDto.setStartNum(startNum);
			
			List<HashMap<String, String>> list = sqlSession.selectList("board.selectSearch", boardDto);
					
			int totalList= list.size(); //총 글 갯수
			System.out.println("검색된 글 갯수"+totalList);
		
			int totalPage = (totalList/10)+1; //전체 페이지수
			
			ModelAndView listModel = new ModelAndView();
			
			listModel.setViewName(".main.board.list");
			listModel.addObject("list", list);
			listModel.addObject("totalPage", totalPage);
			
			return listModel;
		}//deleteBoard()	
		
}//class
