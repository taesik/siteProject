package com.mvc.board.vo;

public class BoardVO {
	private int num;
	private String author;
	private String title;
	private String content;
	private int readcnt;
	private String writeday;
	private int reproot;//�亯�� �ۼ��� ��� �������� ��ȣ ����
	private int repstep;//�亯���� �鿩���� ����
	private int repindent;//�亯�� �ۼ��� ��� �亯 ���� ���� ����
	private String passwd;//��й�ȣ
	
	public BoardVO () {}
	public BoardVO(int num, String author, String title, String content, int readcnt, String writeday, int reproot,
			int repstep, int repindent, String passwd) {
		super();
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.readcnt = readcnt;
		this.writeday = writeday;
		this.reproot = reproot;
		this.repstep = repstep;
		this.repindent = repindent;
		this.passwd = passwd;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public int getReproot() {
		return reproot;
	}
	public void setReproot(int reproot) {
		this.reproot = reproot;
	}
	public int getRepstep() {
		return repstep;
	}
	public void setRepstep(int repstep) {
		this.repstep = repstep;
	}
	public int getRepindent() {
		return repindent;
	}
	public void setRepindent(int repindent) {
		this.repindent = repindent;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	

}
