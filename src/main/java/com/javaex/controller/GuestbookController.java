package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pbc")
public class GuestbookController extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//작동했는데 확인용 
		System.out.println("GuestbookController");
		
		//action 파라미터의 값이 뭔지 알아야함
		String action = request.getParameter("action");
		System.out.println(action);//업무구분
		
		
		if("list".equals(action)) {
			
			//db데이터 가져온다 --> list
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			List<GuestbookVO> guestbookList = guestbookDAO.guestbookSelect();
			
			System.out.println();
			
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
