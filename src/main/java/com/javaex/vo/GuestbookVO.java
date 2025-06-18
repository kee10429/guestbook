package com.javaex.vo;

public class GuestbookVO {
	
	//필드
	private int no;
	private String name;
	private String password;
	private String text;
	private String reg_date;
	
	
	//생성자
	public GuestbookVO() {
		
	}
	
	public GuestbookVO(int no, String name, String password, String text, String reg_date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.text = text;
		this.reg_date = reg_date;
	}

	//메소드gs
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	//메소드일반
	@Override
	public String toString() {
		return "GuestbookVO [no=" + no + ", name=" + name + ", password=" + password + ", text=" + text + ", reg_date="
				+ reg_date + "]";
	}
}
