package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVO;


public class GuestbookDAO {

	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/guestbook_db";
	private String id = "guestbook";
	private String pw = "guestbook";
	
	//생성자
	public GuestbookDAO() {
	
	}
	
	//메소드gs
	
	//메소드일반
	private void connect() { // 메인에서는 사용하지 못함

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	// 자원정리 메소드-공통
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//전체 리스트 가져오기
	public List<GuestbookVO> guestbookSelect(){
		
		//리스트 준비
		List<GuestbookVO> guestbookList = new ArrayList<GuestbookVO>();
		
		this.connect();
		System.out.println("guestbookSelect()");
		try {
			//3. SQL문준비 / 바인딩 / 실행
			// SQL문준비
			String query= "";
			query +=" select  no, ";
			query +="		  name, ";
			query +="         password, ";
			query +="         text, ";
			query +=" 		  reg_date ";
			query +=" from guestbook ";
			query +=" order by no desc ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			//결과처리
			while(rs.next()) {
				//resultset에서 각각의 값을 꺼내서 자바 변수를 담는다
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String text = rs.getString("text");
				String regDate = rs.getString("reg_date");
				
				//VO로 묶어주기
				GuestbookVO guestbookVO = new GuestbookVO(no, name, password, text, regDate);
				
				guestbookList.add(guestbookVO);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		
		return guestbookList;
			
	}
	// 
	
	
	
	
	
	
	
	
	
	
	
}
