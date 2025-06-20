package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDAO;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVO;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
    
	//생성자: 디폴트 생성자사용 생략가능해서 삭제
	
	//메소드 gs 필요없음
	
	//메소드 일반
	//--get방식으로 요청이 들어왔을때 실행되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GuestbookController");
		
		//파라미터 action의 값을 꺼내온다   업무구분욜
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("addlist".equals(action)) {
			System.out.println("addlist(등록폼+리스트)");
			
			//등록폼+리스트/////////////////////////////////////////////////////////////////////
			//1.dao를 통해서 전체 리스트데이터 가져오기
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			List<GuestbookVO> guestbookList = guestbookDAO.guestbookSelect();
			
			//System.out.println(guestbookList);
			
			//리스트 화면(html) + data 응답을 해야된다
	        //request에 data를 넣어둔다
			request.setAttribute("guestbookList", guestbookList);
			
			//addList.jsp 에게 시킨다 	(포워드)
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");

			//////////////////////////////////////////////////////////////////////////////
		}else if("delform".equals(action)) {
			System.out.println("delform(삭제폼)");
			
			//삭제폼일때
			
			//no 값은 deleteForm.jsp에서 request.getParameter("no")로 꺼내쓴다
			
			//deleteForm.jsp 에게 시킨다 	(포워드)
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
		}else if("delete".equals(action)) {
			System.out.println("delete(삭제)");
			//삭제일때
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//vo 로 1개로 묶기
			GuestbookVO guestbookVO = new GuestbookVO();
			guestbookVO.setNo(no);
			guestbookVO.setPassword(password);
			
			//dao를 통해서 삭제하기
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			guestbookDAO.guestbookDelete(guestbookVO);
			
			//리스트 출력하기  -->리다이렉트
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addlist");
			
		}else if("write".equals(action)) {
			System.out.println("write(등록)");
			//등록일때
			
			//데이터 모아서 묶기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//vo 로 1개로 묶기
			GuestbookVO guestbookVO = new GuestbookVO(name, password, content);
			
			
			//dao를 통해서 등록하기
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			guestbookDAO.guestbookInsert(guestbookVO);
			
			//리스트 출력하기  -->리다이렉트
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addlist");
		
		}else {
			System.out.println("나머지");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
